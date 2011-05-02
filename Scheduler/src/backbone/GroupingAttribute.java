package backbone;


public class GroupingAttribute extends Attribute{

	private Grouping group;

	public GroupingAttribute(String title) {
		super(title);
		group = null;
	}
	
	public GroupingAttribute(String title, Grouping group){
		super(title);
		this.group = group;
	}

	@Override
	public Type getType() {
		return Attribute.Type.GROUPING;
	}
	
	public Grouping getGrouping() {
		return group;
	}
	
	public void setGrouping(Grouping g) {
		group = g;
	}
	
	@Override
	public float getConflictMagnitude() {
		return conflictMagnitude * getGrouping().size();
	}
}
