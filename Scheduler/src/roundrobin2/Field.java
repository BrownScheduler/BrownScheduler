package roundrobin2;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class Field implements Unit {

	private StringAttribute _name;
	private Turn _t;
	private ArrayList<Referee> _associatedRefs;
	
	public Field(Turn t, StringAttribute name){
		_t = t;
		_name = name;
	}
	
	public Field(Turn t, String name){
		_t = t;
		_name = new StringAttribute("Name", name);
		_associatedRefs = new ArrayList<Referee>();
		
	}

	public ArrayList<Referee> possibleRefs(){
		return _associatedRefs;
	}
	@Override
	public boolean deleteFromGrouping() {
		return _t.getFields().deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(_name);
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
