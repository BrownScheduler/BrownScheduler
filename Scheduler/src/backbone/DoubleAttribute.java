package backbone;

/**
 * The Attribute which wraps a double
 * Default value is 0.0
 * @author pclay
 *
 */

public class DoubleAttribute extends Attribute {

	private Double att;
	
	public DoubleAttribute(String title){
		super(title);
		this.att = 0.0;
	}
	
	public DoubleAttribute(String title, Double att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.DOUBLE;
	}
	
	public double getAttribute() {
		return att;
	}
	
	public void setAttribute(double a) {
		att = a;
	}
	
	@Override
	public String toString() {
		return att.toString();
	}

}
