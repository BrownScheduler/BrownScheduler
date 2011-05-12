package apda;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.StringAttribute;

public class Bye extends Team {

	private Tourney _t;
	public Bye(Tourney t) {
		super(t, "Bye");
		_t = t;
	}
	
	public boolean isFullSeed(){
		return false;
	}
	public boolean isHalfSeed(){
		return false;
	}
	public boolean isFreeSeed(){
		return false;
	}
	
	@Override
	public Grouping getMemberOf() {
		return new TeamGrouping(_t, "Bye Grouping");
	}
	
	@Override
	public boolean deleteFromGrouping() {
		return false;
	}
	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new StringAttribute("Name", "BYE"));
		return atts;
	}
	
	@Override
	public void setAttribute(Attribute attribute) {		
	}

}
