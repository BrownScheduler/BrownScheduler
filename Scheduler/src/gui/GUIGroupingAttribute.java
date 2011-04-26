package gui;

import java.util.Collection;

/**
 * Models a grouping attribute of a Competitive Unit
 * Example: Player of a team
 */
public interface GUIGroupingAttribute {

	/**
	 * Returns the name of the GroupingAttribute
	 * @return String
	 */
	String getName();
	
	/**
	 * Returns the attributes of this GroupingAttribute
	 * @return Collection<GUIAttribute>
	 */
	Collection<GUIAttribute> getAttributes();
	
	/**
	 * Returns the groupings this GroupingAttribute is in
	 * @return Collection<GUIGrouping>
	 */
	Collection<GUIGrouping> getGroupings();
}
