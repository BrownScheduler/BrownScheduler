package backbone;

import java.util.List;
import backbone.Attribute.Type;

public class UnitAttribute<T extends Unit> extends Attribute {
	
	public T att; //TODO: getter
	private Grouping<T> memberOf;
	
	public UnitAttribute(String title, Grouping<T> grouping){
		super(title);
		this.att = null;
		this.memberOf = grouping;
	}
	public UnitAttribute(String title, T att, Grouping<T> grouping){
		super(title);
		this.att = att;
		this.memberOf = grouping;
	}
	public UnitAttribute(String title, T att){
		super(title);
		this.att = att;
		this.memberOf = (Grouping<T>) att.getMemberOf();
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
	
	public String toString(){
		String s = "Att: ";
		if(att == null) s += "null";
		else s += att.toString();
		s += "memberOf: ";
		if(memberOf == null) s += null;
		else s += memberOf.getName();
		return s;
	}

}
