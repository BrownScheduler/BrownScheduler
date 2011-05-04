package backbone;

import java.util.List;


public class GroupingAttribute<T extends Unit> extends Attribute{

	private Grouping<T> group;

	public GroupingAttribute(String title) {
		super(title);
		group = null;
	}
	
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

	public void addMember(T member) {
		group.addMember(member);
	}

	public void deleteMember(T member) {
		group.deleteMember(member);
	}
}
