package apda;

import java.util.List;

import backbone.Grouping;
import backbone.Unit;

public class TeamGrouping extends MyCategory<Team>{

	Tourney _t;
	School _s;
	public TeamGrouping(Tourney t, String name) {
		super(name);
		_t = t;
	}
	public TeamGrouping(Tourney t, String name, School s) {
		super(name);
		_t = t;
		_s = s;
	}
	
	public TeamGrouping(Tourney t, String name, List<Team> teams){
		super(name, teams);
		_t = t;
	}
	
	@Override
	public Team getBlank() {
		if(_s == null)
			return new Team(_t, "");
		return new Team(_t, "", _s);
	}

	@Override
	public Team getDuplicate(Team unit) {
		for(Team t : this.getMembers()){
			if(t.getName().equals(unit.getName()) && t != unit){
				return t;
			}
		}
		return null;
	}
	
	public void addMember(Team member) {
		if(!this._members.contains(member) && getDuplicate(member) == null && !member.getName().equals("")) 
			_members.add(member);		
	}
}
