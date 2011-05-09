package roundrobin;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
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
public class Referee implements Unit{
	
	
	HashSet<Team> _conflictedTeams;
	private Grouping<Unit> _category;
	private String _name;
	
	public Referee(String name) {
		this._name = name;
		_conflictedTeams = new HashSet<Team>();
	}

	@Override
	public List<Attribute> getAttributes() {
	
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		StringAttribute name = new StringAttribute("Name", this._name);
		GroupingAttribute<Team> conflicts = new GroupingAttribute<Team>("Conflicted Teams", 
				new TeamGrouping("Conflicted Teams", new LinkedList<Team>(_conflictedTeams)));
		atts.add(name);
		atts.add(conflicts);
		return atts;
	}
	
	public String getName(){
		return this._name;
	}
	
	public boolean hasConflict(Team t){
		return _conflictedTeams.contains(t);
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING){
			StringAttribute att = (StringAttribute)attribute;
			this._name = att.getAttribute();
		}
		else if(attribute.getType() == Attribute.Type.GROUPING){
			this._conflictedTeams = new HashSet<Team>(((GroupingAttribute<Team>)attribute).getMembers());
		}
		
	}
	
	public void addConflictedTeam(Team t){
		this._conflictedTeams.add(t);
	}

	public String toString(){
		String r = "Name: " + this._name;
		r += "\nConflicts: ";
		for(Team t : this._conflictedTeams){
			r += t.getName() + "; ";
		}
		
		return r;
	}
	@Override
	public Unit getBlank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		// TODO Auto-generated method stub
		return this._category;
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		this._category = g;
		
	}
	
}
