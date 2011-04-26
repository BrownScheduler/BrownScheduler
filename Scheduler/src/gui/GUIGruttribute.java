package gui;

import java.util.Collection;

/**
 * Models an attribute of a Grouping
 */
public interface GUIGruttribute {

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
	
}
