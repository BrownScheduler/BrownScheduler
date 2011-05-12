package apda;

import java.util.List;

import backbone.Grouping;
import backbone.Unit;

public class SchoolGrouping extends MyCategory<School> {

	Tourney _t;
	public SchoolGrouping(Tourney tourney, String string) {
		super(string);
		_t = tourney;
	}


	@Override
	public boolean deleteMember(School member) {
		
		return member.deleteFromGrouping();
	}

	@Override
	public School getBlank() {
		return new School(_t, "");
	}

	@Override
	public List<School> getMembers() {
		return this._members;
	}

	@Override
	public String getName() {
		return this._name;
	}


	@Override
	public School getDuplicate(School unit) {
		for(School t : this.getMembers()){
			if(t.getName().equals(unit.getName()) && t != unit){
				return t;
			}
		}
		return null;
	}
	
	@Override
	public void addMember(School member) {
		if(!this._members.contains(member) && getDuplicate(member) == null && !member.getName().equals("")) 
			_members.add(member);
		
	}

}
