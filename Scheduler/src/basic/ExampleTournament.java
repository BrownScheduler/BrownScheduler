package basic;

import java.util.ArrayList;

import backbone.Round;
import backbone.Tournament;
import exception.InvalidRoundException;

public class ExampleTournament extends TournamentTemplate {

	public ExampleTournament() {
		super(5);
		ExampleGrouping group = new ExampleGrouping("Example Units");
		addGrouping(group);
	}

	@Override
	public Round createNextRound(boolean suppressWarnings)
			throws InvalidRoundException {
		ArrayList<ExampleUnit> competitors = new ArrayList(getCategories().get(0).getMembers());
		Round round = new Round("Round " + Integer.toString(getRounds().size()));
		for(int i = 0; i < competitors.size() / 2; i++) {
			round.addMember(new ExamplePairing(i, round, competitors.get(2 * i), competitors.get((2 * i) + 1)));
		}
		addRound(round);
		return round;
	}

	@Override
	public Tournament getNew() {
		return new ExampleTournament();
	}

}