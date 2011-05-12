package backbone;

import java.util.List;

/**
 * A wrapping of a unit to be held by another unit. Acts as a pointer internally and a 
 * means of unit selection in the GUI
 * 
 * @author pclay
 *
 * @param <T> The type of unit held by the attribute
 */
public class UnitAttribute<T extends Unit> extends Attribute {
	
	private T att;
	private Grouping<T> memberOf;
	
	public UnitAttribute(String title, Grouping<T> grouping){
		super(title);
		this.setAttribute(null);
		this.memberOf = grouping;
	}
	public UnitAttribute(String title, T att, Grouping<T> grouping){
		super(title);
		this.setAttribute(att);
		this.memberOf = grouping;
	}
	
	public Grouping<T> getMemberOf() {
		return this.memberOf;
	}
	
	public List<T> getListOfUnits() {
		return this.memberOf.getMembers();
	}
	
	@Override
	public Type getType() {
		return Attribute.Type.UNIT;
	}
	
	public void setGrouping(Grouping<T> group){
		this.memberOf = group;
	}

	@Override
	public String toString() {
		if(getAttribute() == null)
			return "";
		return getAttribute().getName();
	}
	public void setAttribute(T att) {
		this.att = att;
	}
	public T getAttribute() {
		return att;
	}
}
