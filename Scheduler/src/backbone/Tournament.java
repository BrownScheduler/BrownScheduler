package backbone;

import java.io.Serializable;
import java.util.List;

import exception.InvalidRoundException;

/**
 * The class that holds the totality of the information of a tournament
 * @author pclay
 */
public interface Tournament extends Serializable {

	/**
	 * Returns the list of groupings to be displayed in the tree on the left side of the GUI
	 * @return the list of groupings
	 */
	public List<Grouping> getCategories();
	
	/**
	 * Gets the rounds of the tournament to be displayed on the top of the management pane.
	 * @return rounds
	 */
	public List<Round> getRounds();

	/**
	 * Returns a new blank version of the current tournament. This is used to open new windows.
	 * @return the new Tournament
	 */
	public Tournament getNew();

	/**
	 * Creates a new round of the tournament as specified by an internal set of rules. 
	 * If a perfect round cannot be created a warning that can be overridden may be thrown.
	 * If a warning that cannot be overridden is then thrown after that, it will not be possible
	 * be created the 
	 * @param suppressWarnings whether or not suppressable warnings should be displayed
	 * @return the created round
	 * @throws InvalidRoundException If a round cannot be generated or if there is a supressable problem
	 * with round generation
	 */
	public Round createNextRound(boolean suppressWarnings) throws InvalidRoundException;
	
}
