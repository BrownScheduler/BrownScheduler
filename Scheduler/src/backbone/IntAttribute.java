package backbone;

/**
 * A wrapper for an integer
 * @author pclay
 *
 */
public class IntAttribute extends Attribute{
	
	//The wrapped integer
	public Integer att;

	/**
	 * Constructs a new IntAttribute
	 * @param title the title
	 * @param att the wrapped integer
	 */
	public IntAttribute(String title, Integer att){
		super(title);
		this.att = att;
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.INT;
	}
	
	/**
	 * Gets the wrapped integer
	 * @return the wrapped integer
	 */
	public int getAttribute() {
		return att;
	}
	
	/**
	 * Sets the wrapped integer
	 * @param a the value to set it to
	 */
	public void setAttribute(int a) {
		att = a;
	}
	
	/**
	 * Returns a string version of the wrapped integer
	 */
	@Override
	public String toString() {
		return att.toString();
	}
}
