package backbone;

public class BooleanAttribute extends Attribute {

	private boolean att;
	
	public BooleanAttribute(String title) {
		super(title);
		this.att = false;
	}
	
	public BooleanAttribute(String title, boolean att) {
		super(title);
		this.att = att;
	}
	
	public Type getType() {
		return Attribute.Type.BOOLEAN;
	}

	public boolean getAttribute() {
		return att;
	}
	
	public void setAttribute(boolean a) {
		att = a;
	}
}
