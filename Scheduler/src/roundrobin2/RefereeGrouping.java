package roundrobin2;

import basic.GroupingTemplate;

public class RefereeGrouping extends GroupingTemplate<Referee> {

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
