package roundrobin;

import basic.DefaultGrouping;

public class RefereeGrouping extends DefaultGrouping<Referee> {

	private Tournament _t;
	public RefereeGrouping(Tournament t, String name) {		
		super(name);
		_t = t;
	}

	@Override
	public Referee getBlank() {
		return new Referee(_t, "");
	}

}
