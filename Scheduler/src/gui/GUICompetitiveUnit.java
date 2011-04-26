package gui;

import java.util.Collection;

/**
 * Models a competitive unit
 */
public interface GUICompetitiveUnit extends GUIGroupingAttribute {

	/**
	 * Returns GroupingAttributes that are a part of this Competitive Unit.
	 * @return Collection<GUIGroupingAttribute>
	 */
	Collection<GUIGroupingAttribute> getGroupingAttributes();
	
}
