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
		_associatedRefs = new GroupingAttribute<Referee>("Referees", new RefereeGrouping(_t, "Associated Refs"));
		_t = t;
	}
	
	public Field(Tournament t, String name){
		_name = new StringAttribute("Name", name);
		_associatedRefs = new GroupingAttribute<Referee>("Referees", new RefereeGrouping(_t, "Associated Refs"));
		_t = t;
	}
	
	public RefereeGrouping ref(){
		return (RefereeGrouping)_associatedRefs.getGrouping();
	}
	
	public void addRef(Referee ref){
		
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
		return _name.value;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING)
			_name = (StringAttribute)attribute;
		else if(attribute.getType() == Attribute.Type.GROUPING){
			_associatedRefs = (GroupingAttribute<Referee>)attribute;
		}

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		//Doesn't do anything, as will always be a member of the
		//tournament's FieldGrouping
	}

	@Override
	public void setName(String name) {
		this._name = new StringAttribute("Name", name);
		
	}

}
