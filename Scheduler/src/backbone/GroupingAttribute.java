package backbone;

import backbone.Attribute.Type;

public class GroupingAttribute extends Attribute{

	
	public GroupingAttribute(String title) {
		super(title);
	}

	@Override
	public Type getType() {
		return Attribute.Type.GROUPING;
	}
}
