package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Tournament implements Serializable {
	
	public Round getCurrentRound() {
		return null;
	}

	public List<Category> getCategories() {
		return null;
	}
	
	public abstract Round createNextRound();
	
	public Collection<Round> getRounds() {
		return null;
	}
	
	
}
