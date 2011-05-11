package backbone;

import java.util.List;

/**
 * Provides a way for Units to have Attributes containing
 * an indeterminate number of Units
 * 
 * @author pclay
 *
 * @param <T> must be a Unit
 */
public class GroupingAttribute<T extends Unit> extends Attribute{

	private Grouping<T> group;
	
	/**
	 * Constructs a GroupingAttribute by setting this's Grouping
	 * 
	 * @param titlenWhat to clal the Grouping Attribute (this will appear in the GUI
	 * as a label)
	 * @param group The group that the attribute contains
	 */
	public GroupingAttribute(String title, Grouping<T> group){
		super(title);
		this.group = group;
	}
	
	/**
	 * Returns a new Unit of type T with a getName() of ""
	 * @return a new blank Unit
	 */
	public T getBlankUnit() {
		return group.getBlank();
	}

	/**
	 * Returns Attribute.Type.Grouping
	 */
	@Override
	public Type getType() {
		return Attribute.Type.GROUPING;
	}
	
	/**
	 * 
	 * @return a list of the members of the Grouping this Attribute contains
	 */
	public List<T> getMembers() {
		return group.getMembers();
	}
	
	/**
	 * 
	 * @return the actual Grouping this attribute contains
	 */
	public Grouping<T> getGrouping(){
		return group;
	}

	/**
	 * 
	 * @param member adds member to this grouping
	 * (as defined by the grouping)
	 */
	public void addMember(T member) {
		group.addMember(member);
	}

	/**
	 * Removes this member from the grouping
	 * (as defined by the grouping)
	 * @param member element to attempt to remove
	 */
	public void deleteMember(T member) {
		group.deleteMember(member);
	}
	
	/**
	 * Returns the string of the grouping
	 */
	@Override
	public String toString() {
		return group.toString();
	}
}
