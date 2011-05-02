package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Tournament implements Serializable {

	public Collection<CompetitiveUnit> getCompetitors() {
		return null;
	}
	
	public Round getCurrentRound() {
		return null;
	}

	public Collection<Category> getCategories() {
		return null;
	}
	
	public abstract Round createNextRound();
	
	public Collection<Round> getRounds() {
		return null;
	}
	
	
}
