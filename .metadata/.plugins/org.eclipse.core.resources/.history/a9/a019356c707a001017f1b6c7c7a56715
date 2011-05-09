package plugin1;

import java.util.List;

import backbone.Grouping;

public class TeamGrouping extends MyCategory<Team>{

	Tourney _t;
	public TeamGrouping(Tourney t, String name) {
		super(name);
		_t = t;
	}
	
	public TeamGrouping(String name, List<Team> teams){
		super(name, teams);
	}
	
	@Override
	public Team getBlank() {
		return new Team(_t, "");
	}
}
