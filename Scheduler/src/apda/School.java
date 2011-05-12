package apda;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.Grouping;
import backbone.GroupingAttribute;
import backbone.StringAttribute;
import backbone.Unit;
import backbone.UnitAttribute;

public class School implements Unit {

	private String _name;
	private Tourney _t;
	public Team _freeSeed;
	public TeamGrouping _teams;
	
	public School(Tourney t, String name){
		_name = name;
		_t = t;
		_teams = new TeamGrouping(_t, "Teams", this);
	}
	public boolean hasFreeSeed(){
		return _freeSeed != null;
	}
	
	public boolean removeTeam(Team t){
		t.school = null;
		return _teams.deleteMember(t);
	}
	public void addTeam(Team t){
		if(!_teams._members.contains(t)) _teams.addMember(t);
		t.school = this;
	}
	
	
	@Override
	public boolean deleteFromGrouping() {
		return _t._schools.deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new StringAttribute("Name", _name));
		for(Team t : this._teams.getMembers()){
			t.school = this;
		}
		atts.add(new GroupingAttribute<Team>("Teams", this._teams));
		TeamGrouping possibleSeeds = new TeamGrouping(_t, "Possible Seeds");
		for(Team t : _teams.getMembers()){
			possibleSeeds.addMember(t);
		}
		atts.add(new UnitAttribute<Team>("Free Seed", _freeSeed, possibleSeeds));
		
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		return _t._schools;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute att) {
		String title = att.getTitle();
		if(title.equals("Name")){
			_name = ((StringAttribute)att).value;
		}else if(title.equals("Teams")){
			_teams = (TeamGrouping) ((GroupingAttribute<Team>)att).getGrouping();
			for(Team t : _teams.getMembers()){
				t.school = this;
			}
		}else if(title.equals("Free Seed")){
			Team toSet = ((UnitAttribute<Team>)att).att;
			if(toSet != _freeSeed){
				toSet.setSeed("Free");
			}
		}

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

}
