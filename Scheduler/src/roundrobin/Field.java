package roundrobin;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.GroupingAttribute;
import backbone.StringAttribute;
import backbone.Unit;

public class Field implements Unit {

	private StringAttribute _name;
	private GroupingAttribute<Referee> _associatedRefs;
	private Tournament _t;
	
	public Field(Tournament t, StringAttribute name){
		_name = name;
		_associatedRefs = new GroupingAttribute<Referee>("Referees");
		_t = t;
	}
	@Override
	public boolean deleteFromGrouping() {
		return _t.getFields().deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(_name);
		atts.add(_associatedRefs);
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		return _t.getFields();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		// TODO Auto-generated method stub

	}

}
