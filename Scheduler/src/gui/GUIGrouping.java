package gui;

import java.util.Collection;

/**
 * Models a grouping.
 */
public interface GUIGrouping {

	/**
	 * Returns the name of the Grouping
	 * @return String
	 */
	String getName();
	
	/**
	 * Returns the value of the Grouping (the actual group in the grouping)
	 * @return String
	 */
	GUIGroupingGroup getGroup();
	
	/**
	 * Returns the group attributes of this Grouping
	 * @return Collection<GUIGruttribute>
	 */
	Collection<GUIAttribute> getAttibrutes();
}
