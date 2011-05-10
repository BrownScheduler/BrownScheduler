package backbone;

import java.io.Serializable;
import java.util.List;

public interface Tournament extends Serializable {

	public List<Grouping> getCategories();
	
	public Round createNextRound();
	
	public List<Round> getRounds();

	public Tournament getNew();
	
	public Tournament getNew();
	
}
