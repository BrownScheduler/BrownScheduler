package roundrobin2;

import basic.GroupingTemplate;

public class PlayerGrouping extends GroupingTemplate<Player> {

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
