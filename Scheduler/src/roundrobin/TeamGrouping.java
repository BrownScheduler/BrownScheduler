package roundrobin;

import java.util.List;

import backbone.Grouping;
import backbone.Unit;

import plugin1.MyCategory;

public class TeamGrouping extends MyCategory<Team> {

	private Tournament _t;
	public TeamGrouping(Tournament t, String name) {
		super(name);
		_t = t;
	}
	public TeamGrouping(Tournament t, String name, List<Team> teams) {
		super(name);
		_t = t;
		for(Team te : teams){
			this.addMember(te);
		}
	}

	@Override
	public Team getBlank() {
		return new Team(_t, "");
	}


	@Override
	public void addMember(Team member) {
		super.addMember(member);
		
		System.out.println(_t.toString());
		System.out.println("0" + this.toString());
		
	}
}
