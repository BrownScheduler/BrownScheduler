package backbone;

public class DoubleAttribute extends Attribute {

	private Double att;
	
	public DoubleAttribute(String title){
		super(title);
		this.att = null;
	}
	
	public DoubleAttribute(String title, Double att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.DOUBLE;
	}

}
