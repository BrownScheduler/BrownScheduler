package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Tournament extends Serializable {

	public List<Grouping> getCategories();
	
	public Round createNextRound() throws exception.ImpossibleTournamentException, exception.InvalidStateException;
	
	public List<Round> getRounds();
	
}
