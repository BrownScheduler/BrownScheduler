package apda;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class SeedUnit implements Unit {

	private String _name;
	
	public SeedUnit(String name){
		_name = name;
	}
	
	public static SeedGrouping getOnlyGrouping(){
		SeedGrouping group = new SeedGrouping();
		group.addMember(new SeedUnit("Full"));
		group.addMember(new SeedUnit("Half"));
		group.addMember(new SeedUnit("Free"));
		return group;
	}
	public boolean isFull(){
		return _name.equals("Full");
	}
	public boolean isHalf(){
		return _name.equals("Half");
	}
	public boolean isFree(){
		return _name.equals("Free");
	}
	public boolean isNotSeeded(){
		return _name.equals("");
	}
	@Override
	public boolean deleteFromGrouping() {
		// Can't do it
		return false;
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(new StringAttribute("Name", _name));
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		SeedGrouping group = new SeedGrouping();
		group.addMember(this);
		return group;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		_name = ((StringAttribute)attribute).value;

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		//Does nothing
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

}
