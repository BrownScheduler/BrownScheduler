package roundrobin;

import basic.DefaultGrouping;

public class PlayerGrouping extends DefaultGrouping<Player> {

	private Tournament _t;
	private Team _team;
	public PlayerGrouping(Tournament t, Team team, String name) {
		super(name);
		_t = t;
		_team = team;
	}

	@Override
	public Player getBlank() {
		Player p = new Player(_t, "");
		p.setTeam(_team);
		return p;
	}

}
