package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tournament implements Serializable {

	public List<CompetitiveUnit> getCompetitors() {
		return null;
	}
	
	public Round getCurrentRound() {
		return null;
	}

	public List<RootGrouping> getRootGroupings() {
		return null;
	}
	
	public List<Round> getRounds() {
		return null;
	}

	public List<Attribute> getProperties() {
		return null;
	}
	
	
}
