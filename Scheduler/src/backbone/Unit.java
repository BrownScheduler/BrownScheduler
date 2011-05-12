package backbone;

import java.io.Serializable;
import java.util.List;

/**
 * A unit is an element of a tournament which can be displayed and has attributes. It can also be
 * stored in groupings. Anything the user interacts with in input and management pane is probably a
 * grouping.
 * @author pclay
 */
public interface Unit extends Serializable {
	
	/**
	 * Returns the grouping that this Unit needs to belong to exist in the tournament. In
	 * most cases this is a category (a grouping from the tree on the left hand side).
	 * @return the grouping
	 */
	public Grouping<Unit> getMemberOf();
	
	/**
	 * Changes where this Unit belongs in the tournament
	 * @param g the grouping to which it should now belong
	 */
	public void setMemberOf(Grouping<Unit> g);

	/**
	 * Sets the attribute of the unit sharing a title with a given attribute, the value of 
	 * the given attribute
	 * @param attribute the given attribute
	 */
	public void setAttribute(Attribute attribute);
	
	/**
	 * Return a list of the attributes of the unit in the order they should be displayed in the GUI 
	 * @return the list of attributes
	 */
	public List<Attribute> getAttributes();
	
	/**
	 * Gets the name of the unit
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Sets the name of the unit
	 * @param name the name to be set to
	 */
	public void setName(String name);
	
	/**
	 * Removes the unit from the grouping it is in
	 * @return true if the removal was successful
	 */
	public boolean deleteFromGrouping();
}
