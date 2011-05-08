package roundrobin;

import java.util.List;

public class RefereeGrouping extends MyCategory<Referee> {

	public RefereeGrouping(String name) {
		super(name);
	}
	public RefereeGrouping(String name, List<Referee> referees){
		super(name, referees);
	}

	@Override
	public Referee getBlank() {
		return new Referee("");
	}

}
