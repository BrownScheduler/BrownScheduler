package roundrobin2;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;
import backbone.UnitAttribute;

public class Player implements Unit {

	private StringAttribute _name;
	private Turn _t;
	
	public Player(Turn t, String name){
		_t = t;
		_name = new StringAttribute("Name", name);
		
	}
	
	@Override
	public boolean deleteFromGrouping() {
		return _t.getPlayers().deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(_name);
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		return _t.getPlayers();
	}

	@Override
	public String getName() {
		return _name.getAttribute();
	}

	/**
	 * Sets the attributes.
	 */
	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING)
			_name = (StringAttribute)attribute;
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		this._name = new StringAttribute("Name", name);
		
	}

}
