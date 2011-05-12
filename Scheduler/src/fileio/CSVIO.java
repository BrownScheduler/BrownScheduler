package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.DoubleAttribute;
import backbone.Grouping;
import backbone.GroupingAttribute;
import backbone.IntAttribute;
import backbone.StringAttribute;
import backbone.Tournament;
import backbone.Unit;
import backbone.UnitAttribute;
import exception.CSVException;

/**
 * Allows importing and exporting groupings to and from CSV files
 */
public class CSVIO {

	/**
	 * Writes a grouping to a CSV file
	 * @param file the file
	 * @param category the grouping to be written
	 * @throws exception.CSVException thrown if the file cannot be written to
	 */
	public static void writeGrouping(File file, Grouping<? extends Unit> category) throws exception.CSVException {
		try {
			//prepares the file
			BufferedWriter out = new BufferedWriter(new FileWriter(file));

			//prints first line
			out.write(category.getName() + "\n");

			//prints titles
			for(Attribute att : category.getBlank().getAttributes())
				if(att.getType() != Attribute.Type.GROUPING)
					out.write(att.getTitle() + ",");
			out.write("\n");

			//prints units
			for(Unit member : category.getMembers()) {
				String groups = "";

				//prints non-group attributes
				for(Attribute att : member.getAttributes()) {
					if(att.getType() == Attribute.Type.GROUPING) {
						GroupingAttribute<Unit> gatt = (GroupingAttribute<Unit>) att;
						String group = "," + gatt.getTitle();
						for(Unit unit: gatt.getMembers())
							group += "," + unit.getName();
						groups += group + "\n";
						break;
					}
					else
						out.write(att.toString() + ",");
				}

				//prints group attributes
				out.write("\n");
				out.write(groups);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new exception.CSVException(e);
		}
	}

	/**
	 * Edits a tournament based on the contents of a grouping in a CSV file
	 * @param file the file
	 * @param t the tournament
	 * @throws exception.CSVException if the tournament is malformed or a file can't be opened
	 */
	public static void loadGrouping(File file, Tournament t) throws exception.CSVException {
		try {
			//finds the grouping from the first line of the file
			LinkedList<Grouping<Unit>> groups = new LinkedList<Grouping<Unit>>();
			groups.addAll((Collection<? extends Grouping<Unit>>) t.getCategories());
			groups.addAll((Collection<? extends Grouping<Unit>>) t.getRounds());
			BufferedReader in = new BufferedReader(new FileReader(file));
			String name = in.readLine().split(",")[0];

			in.readLine(); //skips over titles

			Grouping group = null;
			for(Grouping g : groups) {
				if(g.getName().equals(name)) {
					group = g;
					break;
				}
			}

			if(group == null)
				throw new CSVException("Unknown grouping: " + name);

			//establishes lists to refer to for additions deletions and unit attribute settings
			LinkedList<Unit> toDelete = new LinkedList<Unit>(group.getMembers());
			LinkedList<Unit> toAdd = new LinkedList<Unit>();
			ArrayList<Integer> nongroupAtts = new ArrayList<Integer>();
			ArrayList<Integer> groupAtts = new ArrayList<Integer>();
			HashMap<Unit, HashMap<UnitAttribute, String>> unitsToAdd = new HashMap<Unit, HashMap<UnitAttribute, String>>();
			HashMap<Unit, HashMap<GroupingAttribute, LinkedList<String>>> groupsToSet = new HashMap<Unit, HashMap<GroupingAttribute, LinkedList<String>>>();
			List<Attribute> atts = group.getBlank().getAttributes();

			for(int i = 0; i < atts.size(); i++) {
				Attribute att = atts.get(i);
				if(att.getType() == Attribute.Type.GROUPING)
					groupAtts.add(i);
				else
					nongroupAtts.add(i);
			}

			//reads in units
			String line = in.readLine();
			while(line != null) {

				//reads in string attributes to determine name
				String[] cells = line.split(",");
				Unit temp = group.getBlank();
				for(int i = 0; i < nongroupAtts.size(); i++) {
					Attribute att = temp.getAttributes().get(nongroupAtts.get(i));
					if(att.getType() == Attribute.Type.STRING)
						temp.setAttribute(new StringAttribute(att.getTitle(), cells[i]));
				}
				String unitName = temp.getName();
				if(unitName == null || unitName == "")
					throw new CSVException();
				Unit unit = null;

				//finds if unit is currently in the grouping
				for(Unit u : toDelete)
					if(u.getName().equals(unitName)) {
						unit = u;
						break;
					}
				if(unit == null) {
					unit = group.getBlank();
					toAdd.add(unit);
				}
				else {
					toDelete.remove(unit);
				}

				//sets all non-grouping attributes
				for(int i = 0; i < nongroupAtts.size(); i++) {
					Attribute att = unit.getAttributes().get(nongroupAtts.get(i));
					switch (att.getType()) {

					case BOOLEAN:
						if(cells.length > i && !cells[i].equals(""))
							unit.setAttribute(new BooleanAttribute(att.getTitle(),
									Boolean.parseBoolean(cells[i].toLowerCase())));
						else 
							unit.setAttribute(new BooleanAttribute(att.getTitle(),
									((BooleanAttribute) temp.getAttributes().get(nongroupAtts.get(i))).getAttribute()));
						break;

					case DOUBLE:
						if(cells.length > i && !cells[i].equals(""))
							unit.setAttribute(new DoubleAttribute(att.getTitle(),
									Double.parseDouble(cells[i])));
						else 
							unit.setAttribute(new DoubleAttribute(att.getTitle(),
									((DoubleAttribute) temp.getAttributes().get(nongroupAtts.get(i))).getAttribute()));
						break;

					case INT:
						if(cells.length > i && !cells[i].equals(""))
							unit.setAttribute(new IntAttribute(att.getTitle(),
									Integer.parseInt(cells[i])));
						else 
							unit.setAttribute(new IntAttribute(att.getTitle(),
									((IntAttribute) temp.getAttributes().get(nongroupAtts.get(i))).getAttribute()));

						break;

					case STRING:
						unit.setAttribute(new StringAttribute(att.getTitle(),cells[i]));
						break;

						//Unit attributes are not set immediately in case the unit in question will appear later
					case UNIT:
						if(cells.length > i && !cells[i].equals("")) {
							HashMap<UnitAttribute, String> unitAttsToAdd = new HashMap<UnitAttribute, String>();
							unitAttsToAdd.put((UnitAttribute) att, cells[i]);
							unitsToAdd.put(unit, unitAttsToAdd);
						} else
							unit.setAttribute(new UnitAttribute(att.getTitle(), ((UnitAttribute) att).getMemberOf()));
						break;
					default:
						throw new CSVException();
					}
				}

				//Loads this tables grouping attributes into a hash map
				HashMap<GroupingAttribute, LinkedList<String>> groupAttsToAdd = new HashMap<GroupingAttribute, LinkedList<String>>();
				for(int i = 0; i < groupAtts.size(); i++) {
					line = in.readLine();
					if(line == null)
						throw new CSVException();
					cells = line.split(",");
					if(cells.length < 2)
						throw new CSVException();
					LinkedList<String> members = new LinkedList<String>();
					for(int j = 2; j < cells.length; j++) {
						members.add(cells[j]);
					}
					groupAttsToAdd.put((GroupingAttribute) unit.getAttributes().get(groupAtts.get(i)), members);
				}
				groupsToSet.put(unit, groupAttsToAdd);
				line = in.readLine();
			}

			//Adds new Units
			for(Unit unit : toAdd) {
				if(group.getDuplicate(unit) == null)
					group.addMember(unit);
			}

			//Sets Unit Attributes
			for(Unit unit : unitsToAdd.keySet()) {
				for(Entry<UnitAttribute, String> entry : unitsToAdd.get(unit).entrySet()) {
					UnitAttribute uAtt = entry.getKey();
					uAtt.setAttribute(null);
					for(Object obj : uAtt.getMemberOf().getMembers()) {
						Unit u = (Unit) obj;
						if(u.getName().equals(entry.getValue())) {
							uAtt.setAttribute(u);
							break;
						}
					}
					if(uAtt.getAttribute() == null) {
						uAtt.setAttribute(uAtt.getMemberOf().getBlank());
						uAtt.getMemberOf().addMember(uAtt.getAttribute());
						uAtt.getAttribute().setName(entry.getValue());
					}
					unit.setAttribute(uAtt);
				}
			}

			//Sets group attributes
			for(Unit uni: groupsToSet.keySet()) {
				for(GroupingAttribute  gAtt : groupsToSet.get(uni).keySet()) {

					for(String unitName : groupsToSet.get(uni).get(gAtt)) {
						Unit unit = null;
						for(Unit u : (gAtt.getGrouping().getBlank()).getMemberOf().getMembers()) {
							if(u.getName().equals(unitName)) {
								unit = u;
								break;
							}
						}
						if(unit == null) {
							unit = gAtt.getGrouping().getBlank().getMemberOf().getBlank();
							unit.setName(unitName);
							gAtt.getGrouping().getBlank().getMemberOf().addMember(unit);
							gAtt.addMember(unit);
						} else if(gAtt.getGrouping().getDuplicate(unit) == null &&
								!gAtt.getGrouping().getMembers().contains(unit))
							gAtt.addMember(unit);
					}
					uni.setAttribute(gAtt);
				}

			}

			//Deletes removed units
			for(Unit unit : toDelete) {
				group.deleteMember(unit);
				for(Grouping<Unit> g : groups) 
					for(Unit u : g.getMembers())
						for(Attribute att : u.getAttributes())
							switch(att.getType()) {
							case UNIT:
								if(((UnitAttribute) att).getAttribute().getName().equals(unit.getName()))
									((UnitAttribute) att).setAttribute(null);
								break;
							case GROUPING:
								Unit un = null;
								for(Unit uni : ((GroupingAttribute<Unit>) att).getMembers())
									if(uni.getName().equals(unit.getName())) {
										un = uni;
										break;
									}
								if(un != null)
									((GroupingAttribute) att).deleteMember(un);
							}
			}

		} catch (NumberFormatException e) {
			throw new exception.CSVException();
		} catch (IOException e) {
			throw new exception.CSVException();
		} 
	}
}
