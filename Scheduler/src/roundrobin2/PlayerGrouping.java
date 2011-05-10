package roundrobin2;

import plugin1.MyCategory;

public class PlayerGrouping extends MyCategory<Player> {

	private Turn _t;
	public PlayerGrouping(Turn t, String name) {
		super(name);
		_t = t;
	}

	@Override
	public Player getBlank() {
		Player p = new Player(_t, "");
		return p;
	}

}
