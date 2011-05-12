package plugin1;

import java.util.List;

import basic.DefaultGrouping;

public class TeamGrouping extends DefaultGrouping<Team>{

	Tourney _t;
	public TeamGrouping(Tourney t, String name) {
		super(name);
		_t = t;
	}
	
	public TeamGrouping(Tourney t, String name, List<Team> teams){
		super(name, teams);
		_t = t;
	}
	
	@Override
	public Team getBlank() {
		return new Team(_t, "");
	}
}
