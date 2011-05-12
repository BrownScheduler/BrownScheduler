package backbone;

import java.io.Serializable;
import java.util.List;

/**
 * This interface provides a way to define a Grouping of a Unit.
 * 
 * Every Unit MUST have a grouping corresponding to it
 * (and, in order to get types working correctly, each Unit
 * needs its own Grouping class that extends this interface)
 * @author pclay
 *
 * @param <T> must extend Unit
 */

public interface Grouping<T extends Unit> extends Serializable {
	
	/**
	 * Returns a blank Unit of type T (typically one with a name of "", but NOT null)
	 * This is used so that the GUI can create new units.
	 * 
	 * @return a new, empty Unit of type T
	 */
	public T getBlank();
	
	/**
	 * Returns the name of the grouping. This is what appears on the
	 * "edit ...." button in the Input Panel.
	 * 
	 * @return the name of the Grouping
	 */
	public String getName();

	/**
	 * Returns a java.util.List of all the members.
	 * This list should remain in the same order,
	 * (So, if it should be displayed in a sorted manner,
	 * then the lsit needs to maintain a sorted invariant)
	 * 
	 * @return List<T> a list of the Grouping's members
	 */
	public List<T> getMembers();
	
	/**
	 * Adds a member of type T to the grouping.
	 * Unless duplicates are allowed,
	 * some sort of duplicate guarding must be done
	 * 
	 * @param member a Unit of type T
	 */
	public void addMember(T member);

	/**
	 * Removes a member of type T from the grouping.
	 * Any references member hasmust to this should also
	 * be updated at this time.
	 * 
	 * @param member a Unit of type T
	 * @return whether or not the deletion was successful
	 */
	public boolean deleteMember(T member);
	
	/**
	 * Clears out all members from this grouping.
	 * This allows the file i/o to keep duplicates
	 * from being created
	 */
	public void clear();
	
	public T getDuplicate(T unit);
}
