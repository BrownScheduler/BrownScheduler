package roundrobin2;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;
import backbone.UnitAttribute;

public class Player implements Unit {

	private StringAttribute _name;
	private Turn _t;
	private UnitAttribute<Position> _pos;
	private BooleanAttribute _hasYellowCard;
	
	public Player(Turn t, String name){
		_t = t;
		_name = new StringAttribute("Name", name);
		_pos = new UnitAttribute<Position>("Position", null, new PositionGrouping("Positions"));
		_hasYellowCard = new BooleanAttribute("YellowCard?", false);
	}
	
	@Override
	public boolean deleteFromGrouping() {
		return _t.getPlayers().deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(_name);
		atts.add(_pos);
		atts.add(this._hasYellowCard);
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
		if(attribute.getType() == Attribute.Type.STRING && attribute.getTitle().equals("Name"))
			_name = (StringAttribute)attribute;
		else if(attribute.getType() == Attribute.Type.STRING && attribute.getTitle().equals("Position")){
			Position.Pos p = Position.determinePosFromString(((StringAttribute)attribute).getAttribute());
			if(p != null) _pos = new UnitAttribute<Position>("Position", null, new PositionGrouping("Positions"));
			else _pos = new UnitAttribute<Position>("Position", 
					new Position("Position", p), 
					new PositionGrouping("Positions"));
		}
		else if(attribute.getType() == Attribute.Type.BOOLEAN){
			_hasYellowCard = (BooleanAttribute)attribute;
		}
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		this._name = new StringAttribute("Name", name);
		
	}

}
