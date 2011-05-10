package roundrobin;

import plugin1.MyCategory;

public class RefereeGrouping extends MyCategory<Referee> {

	private Tournament _t;
	public RefereeGrouping(Tournament t, String name) {		
		super(name);
		System.out.print("REf grouping" );
		System.out.println(t);
		_t = t;
	}

	@Override
	public Referee getBlank() {
		return new Referee(_t, "");
	}

}
