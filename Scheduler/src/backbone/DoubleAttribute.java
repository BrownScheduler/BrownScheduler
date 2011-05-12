package backbone;

/**
 * The Attribute which wraps a double
 * Default value is 0.0
 * @author pclay
 *
 */

public class DoubleAttribute extends Attribute {

	/**
	 * The wrapped double
	 */
	private Double att;
	
	/**
	 * Sets the title and attribute
	 * 
	 * @param title title of the Attribute
	 * @param att the wrapped double
	 */
	public DoubleAttribute(String title, Double att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.DOUBLE;
	}
	
	/**
	 * Gets the contents (att)
	 * @return the wrapped double
	 */
	public double getAttribute() {
		return att;
	}
	
	/**
	 * Sets the wrapped double
	 * @param a the value to set it to
	 */
	public void setAttribute(double a) {
		att = a;
	}
	
	/**
	 * Returns a string version of the wrapped double
	 */
	@Override
	public String toString() {
		return att.toString();
	}

}
