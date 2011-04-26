package gui;

/**
 * Models an attribute
 */
public interface GUIAttribute {

	public enum AttributeType {STRING, INTEGER, FLOAT, BOOLEAN};
	
	/**
	 * Returns the type of this Attribute
	 * @return AttributeType
	 */
	AttributeType getType();
	
	/**
	 * Returns the name of this Attribute
	 * @return String
	 */
	String getName();
	
	/**
	 * Returns the value of this Attribute, if it is a String
	 * @return String
	 */
	String getStringValue();
	
	/**
	 * Returns the value of this Attribute, if it is an Integer
	 * @return int
	 */
	int getIntegerValue();
	
	/**
	 * Returns the value of this Attribute, if it is a Float
	 * @return float
	 */
	float getFloatValue();
	
	/**
	 * Returns the value of this Attribute, if it is a Boolean
	 * @return float
	 */
	boolean getBooleanValue();
}
