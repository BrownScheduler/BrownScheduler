package apda;

import java.util.List;

public class TeamGrouping extends MyCategory<Team>{

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
