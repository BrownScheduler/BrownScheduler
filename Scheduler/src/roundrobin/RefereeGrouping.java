package roundrobin;

import plugin1.MyCategory;

public class RefereeGrouping extends MyCategory<Referee> {

	public RefereeGrouping(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Referee getBlank() {
		// TODO Auto-generated method stub
		return null;
	}

}
