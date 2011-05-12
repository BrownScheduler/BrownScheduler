package roundrobin2;

import java.util.List;

import basic.GroupingTemplate;


public class TeamGrouping extends GroupingTemplate<Team> {

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
