package old_backbone;

import java.io.Serializable;
import java.util.Collection;

public abstract class Tournament implements Serializable {
	
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
