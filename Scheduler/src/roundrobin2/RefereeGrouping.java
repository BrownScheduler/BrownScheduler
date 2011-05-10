package roundrobin2;

import plugin1.MyCategory;

public class RefereeGrouping extends MyCategory<Referee> {

	private Turn _t;
	public RefereeGrouping(Turn t, String name) {		
		super(name);
		_t = t;
	}

	@Override
	public Referee getBlank() {
		return new Referee(_t, "");
	}

}
