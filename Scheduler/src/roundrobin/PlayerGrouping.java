package roundrobin;

import plugin1.MyCategory;

public class PlayerGrouping extends MyCategory<Player> {

	private Tournament _t;
	public PlayerGrouping(Tournament t, String name) {
		super(name);
		_t = t;
	}

	@Override
	public Player getBlank() {
		// TODO Auto-generated method stub
		return new Player(_t, _name);
	}

}
