package plugin1;

import java.util.List;

import backbone.Grouping;

public class TeamGrouping extends MyCategory<Team>{

	public TeamGrouping(String name) {
		super(name);
	}
	
	public TeamGrouping(String name, List<Team> teams){
		super(name, teams);
	}

	@Override
	public Team getBlank() {
		return new Team(null);
	}
}
