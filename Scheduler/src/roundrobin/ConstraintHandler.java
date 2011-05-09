package roundrobin;

import backbone.Round;

public class ConstraintHandler {

	private Tournament _t;
	public ConstraintHandler(Tournament tournament) {
		_t = tournament;
	}

	public Round creatRound() {
		Round r = new Round(Integer.toString(_t.getNextRoundInt()));
		return null;
	}

}
