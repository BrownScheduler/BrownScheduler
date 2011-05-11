package roundrobin2;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class Position implements Unit{

	private String _name;
	public Pos _position;
	private Grouping<Unit> memOf;
	
	public enum Pos {
		MIDFIELD, GOALIE, FORWARD, DEFENDER
	}
	public Position(){
		_name = "";
		_position = null;
	}
	public Position(String name, String pos){
		_name = name;
		_position = determinePosFromString(pos);
		
	}
	
	public Position(String name, Pos pos){
		_name = name;
		_position = pos;
		
	}
	
	public static Pos determinePosFromString(String pos){
		if(pos.toLowerCase().equals("midfield"))
			return Pos.MIDFIELD;
		else if(pos.toLowerCase().equals("goalie"))
			return Pos.GOALIE;
		else if(pos.toLowerCase().equals("forward"))
			return Pos.FORWARD;
		else if(pos.toLowerCase().equals("defender"))
			return Pos.DEFENDER;
		return null;
	}
	
	public String determineStringFromPos(Pos p){
		if(p == Pos.MIDFIELD)
			return "Midfield";
		else if(p == Pos.FORWARD)
			return "Forward";
		else if(p == Pos.DEFENDER)
			return "Defender";
		else if(p == Pos.GOALIE)
			return "Goalie";
		return "";
	}
	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> att = new LinkedList<Attribute>();
		StringAttribute gov = new StringAttribute("Position ", determineStringFromPos(_position));
		att.add(gov);
		return att;
	}

	@Override
	public Grouping getMemberOf() {
		return new PositionGrouping("Positions");
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getTitle() == "Position "){
			_position = determinePosFromString(((StringAttribute)attribute).value);
		}
		
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		this.memOf = g;
		
	}
	
	@Override
	public String toString(){
		return "";
	}
	@Override
	public boolean deleteFromGrouping() {
		return false;
	}
	@Override
	public void setName(String name) {
		_name = name;
	}
}
