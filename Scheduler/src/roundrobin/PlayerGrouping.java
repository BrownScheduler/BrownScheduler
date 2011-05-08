package roundrobin;

import plugin1.MyCategory;

public class PlayerGrouping extends MyCategory<Player> {

	public PlayerGrouping(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getBlank() {
		// TODO Auto-generated method stub
		return null;
	}

}
