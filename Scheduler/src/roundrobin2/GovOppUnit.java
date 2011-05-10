package roundrobin2;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class GovOppUnit implements Unit{

	private String _name;
	public boolean isGov;
	public boolean isOpp;
	private Grouping<Unit> memOf;
	
	public GovOppUnit(){
		isGov = false;
		isOpp = false;
	}
	public GovOppUnit(String name, Boolean isGov){
		this.isGov = isGov;
		this.isOpp = !isGov;
		_name = name;
	}
	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> att = new LinkedList<Attribute>();
		StringAttribute gov = new StringAttribute("Winner ", _name);
		att.add(gov);
		return att;
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		// TODO Auto-generated method stub
		return memOf;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getTitle() == "Is gov?") isGov = ((BooleanAttribute)attribute).getAttribute();
		else isOpp = ((BooleanAttribute)attribute).getAttribute();
		
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		this.memOf = g;
		
	}
	
	@Override
	public String toString(){
		String r = "Gov? ";
		r += isGov;
		r += "Opp? ";
		r += isOpp;
		return r;
	}
	@Override
	public boolean deleteFromGrouping() {
		return memOf.deleteMember(this);
		
	}
	@Override
	public void setName(String name) {
		_name = name;
	}

}
