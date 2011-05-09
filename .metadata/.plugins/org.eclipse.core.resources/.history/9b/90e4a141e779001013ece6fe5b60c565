package roundrobin;

import java.util.List;

import plugin1.MyCategory;

public class TeamGrouping extends MyCategory<Team> {

	private Tournament _t;
	public TeamGrouping(Tournament t, String name) {
		super(name);
		_t = t;
	}

	@Override
	public Team getBlank() {
		// TODO Auto-generated method stub
		return new Team(_t, "");
	}

}
