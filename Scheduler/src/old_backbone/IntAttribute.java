package old_backbone;

public class IntAttribute extends Attribute{
	
	public Integer att;
	
	public IntAttribute(String title){
		super(title);
		this.att = null;
	}
	public IntAttribute(String title, Integer att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.INT;
	}
}
