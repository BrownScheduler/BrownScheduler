package backbone;

import backbone.Attribute.Type;

public class UnitAttribute<T extends Unit> extends Attribute {
	
	public T att;
	
	public UnitAttribute(String title){
		super(title);
		this.att = null;
	}
	public UnitAttribute(String title, T att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.UNIT;
	}

	@Override
	public String toString() {
		return att.getName();
	}
}
