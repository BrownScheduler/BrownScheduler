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
	
	public GroupingAttribute(String title, Grouping<T> group){
		super(title);
		this.group = group;
	}
	
	public T getBlankUnit() {
		return group.getBlank();
	}

	@Override
	public Type getType() {
		return Attribute.Type.GROUPING;
	}
	
	public List<T> getMembers() {
		return group.getMembers();
	}
	
	public Grouping<T> getGrouping(){
		return group;
	}

	public void addMember(T member) {
		group.addMember(member);
	}

	public void deleteMember(T member) {
		group.deleteMember(member);
	}
	
	@Override
	public String toString() {
		return group.toString();
	}
}
