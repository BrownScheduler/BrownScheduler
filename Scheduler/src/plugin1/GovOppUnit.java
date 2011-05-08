package plugin1;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.Grouping;
import backbone.Unit;

public class GovOppUnit implements Unit{

	
	public boolean isGov;
	public boolean isOpp;
	private Grouping<Unit> memOf;
	
	public GovOppUnit(){
		isGov = false;
		isOpp = false;
	}
	public GovOppUnit(Boolean isGov){
		this.isGov = isGov;
		this.isOpp = !isGov;
	}
	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> att = new LinkedList<Attribute>();
		BooleanAttribute gov = new BooleanAttribute("Is gov?", isGov);
		BooleanAttribute opp = new BooleanAttribute("Is opp?", isOpp);
		att.add(gov);
		att.add(opp);
		return att;
	}

	@Override
	public Unit getBlank() {
		return new GovOppUnit(null);
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		// TODO Auto-generated method stub
		return memOf;
	}

	@Override
	public String getName() {
		if(!isGov && !isOpp) return "";
		String s = "Opp";
		if(isGov) s = "Gov";
		return s;
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
	
	public String toString(){
		String r = "Gov? ";
		r += isGov;
		r += "Opp? ";
		r += isOpp;
		return r;
	}

}
