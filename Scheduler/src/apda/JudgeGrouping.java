package apda;

import java.util.List;

public class JudgeGrouping extends MyCategory<Judge> {

	private Tourney _t;
	public JudgeGrouping(Tourney t, String name) {
		super(name);
		_t = t;
	}
	public JudgeGrouping(Tourney t, String name, List<Judge> judges){
		super(name, judges);
		_t = t;
	}

	@Override
	public Judge getBlank() {
		return new Judge(_t, "");
	}
	@Override
	public Judge getDuplicate(Judge unit) {
		for(Judge j : this.getMembers()){
			if(j.getName().equals(unit.getName()) && j != unit){
				return j;
			}
		}
		return null;
	}
	
	@Override
	public void addMember(Judge member){
		if(!this._members.contains(member) && getDuplicate(member) == null && !member.getName().equals("")) 
			_members.add(member);
	}

}
