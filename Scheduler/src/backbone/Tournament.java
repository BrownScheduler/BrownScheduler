package backbone;

import java.io.Serializable;
import java.util.List;

import exception.InvalidRoundException;

public interface Tournament extends Serializable {

	public List<Grouping> getCategories();
	
	public Round createNextRound() throws InvalidRoundException;
	
	public List<Round> getRounds();

	public Tournament getNew();
	
}
