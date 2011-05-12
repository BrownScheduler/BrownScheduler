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
		for(int i = 0; i < (competitors.size() / 2) - 1; i++) {
			round.addMember(new ExamplePairing(competitors.get(i), competitors.get(i + 1)));
		}
		return round;
	}

	@Override
	public Tournament getNew() {
		// TODO Auto-generated method stub
		return null;
	}

}