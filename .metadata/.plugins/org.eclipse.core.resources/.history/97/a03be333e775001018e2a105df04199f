package plugin1;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.GroupingAttribute;
import backbone.StringAttribute;
import backbone.Unit;


/**
 * Just a judge. 
 * One is necessary for a proper pairing to occur
 * 
 * @author matt
 *
 */
public class Judge implements Unit{
	
	
	LinkedList<Team> _conflictedTeams;
	private String _name;
	
	public Judge(String name) {
		this._name = name;
		_conflictedTeams = new LinkedList<Team>();
	}

	@Override
	public List<Attribute> getAttributes() {
	
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		StringAttribute name = new StringAttribute("Name", this._name);
		GroupingAttribute<Team> conflicts = new GroupingAttribute<Team>("Conflicted Teams", 
				new TeamGrouping("Conflicted Teams", _conflictedTeams));
		atts.add(name);
		atts.add(conflicts);
		return atts;
	}
	
	public String getName(){
		return this._name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING){
			this._name = ((StringAttribute)attribute).getAttribute();
		}
		else if(attribute.getType() == Attribute.Type.GROUPING){
			this._conflictedTeams = new LinkedList<Team>(((GroupingAttribute<Team>)attribute).getMembers());
		}
		
	}
	
}
