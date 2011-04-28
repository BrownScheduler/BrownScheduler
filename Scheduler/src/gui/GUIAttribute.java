package gui;

/**
 * Models an attribute
 */
public interface GUIAttribute {

	public enum AttributeType {STRING, INTEGER, FLOAT, BOOLEAN};
	
	/**
	 * Returns the type of this Attribute
	 */
	AttributeType getType();
	
	/**
	 * Returns the name of this Attribute
	 */
	String getName();
	
	/**
	 * Returns the value of this Attribute, if it is a String
	 */
	String getStringValue();
	
	/**
	 * Sets the value of this Attribute, if it is a String
	 */
	void setStringValue(String str);
	
	/**
	 * Returns the value of this Attribute, if it is an Integer
	 */
	int getIntegerValue();
	
	/**
	 * Sets the value of this Attribute, if it is an Integer
	 */
	int setIntegerValue();
	
	/**
	 * Returns the value of this Attribute, if it is a Float
	 */
	float getFloatValue();
	
	/**
	 * Sets the value of this Attribute, if it is a Float
	 */
	float setFloatValue();
	
	/**
	 * Returns the value of this Attribute, if it is a Boolean
	 */
	boolean getBooleanValue();
	
	/**
	 * Sets the value of this Attribute, if it is a Boolean
	 */
	boolean setBooleanValue();
}
