package roundrobin2;

import java.util.List;

import plugin1.MyCategory;

public class TeamGrouping extends MyCategory<Team> {

	private Turn _t;
	
	public TeamGrouping(Turn t, String name) {
		super(name);
		_t = t;
	}
	public TeamGrouping(Turn t, String name, List<Team> teams) {
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

}
