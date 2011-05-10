package constraint;
import java.util.ArrayList;
import java.util.Collection;

import old_backbone.*;


public class ConstraintHandler {

	public void randomized(Tournament tournament) {
		ArrayList<CompetitiveUnit> competitors = new ArrayList<CompetitiveUnit>();
		competitors.addAll(tournament.getCompetitors());
		Round round = tournament.getCurrentRound();
		while(!competitors.isEmpty()) {
			CompetitiveUnit mostConflicted = null;
			float conflict = - Float.MAX_VALUE;
			for(CompetitiveUnit competitor : competitors) {
				if(competitor.getPotentialConflictMagnitude() > conflict) {
					conflict = competitor.getPotentialConflictMagnitude();
					mostConflicted = competitor;
				}
			}
			round.addPairing(new Pairing(mostConflicted, competitors));
		}
	}

	public Round exhaustive(ArrayList<CompetitiveUnit> competitors, Round round) {
		for(CompetitiveUnit competitor : competitors) {
			for(CompetitiveUnit comp : competitors) {
				if(!competitor.equals(comp)
						&& !round.isPaired(competitor)
						&& !round.isPaired(comp)) {
					Pairing pairing = new Pairing(competitor, comp);
					if(pairing.satisfactory()) {
						ArrayList<CompetitiveUnit> remaining = new ArrayList<CompetitiveUnit>();
						remaining.addAll(competitors);
						remaining.remove(competitor);
						remaining.remove(comp);
						Round newRound = round.copy();
						newRound.addPairing(pairing);
						newRound = exhaustive(remaining, newRound);
						if(newRound != null)
							return newRound;
					}
				}
			}
		}
		return null;
	}

}
