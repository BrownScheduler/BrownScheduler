package plugin1;
import java.util.LinkedList;

import backbone.*;


public class Team implements CompetitiveUnit {
	
	private Grouping<Unit> _category;
	private String _name;
	private int _wins;
	private Tourney _t;
	
	public Team(Tourney t, String name){
		_t = t;
		this._name = name;
		_wins = 0;
		_category = t.getCategories().get(0);
	}
	
	public void addWin(){
		_wins++;
	}
	
	public int getWins(){
		return _wins;
	}
	
	public void setWins(int i){
		_wins = i;
	}
	
	@Override
	public String getName(){
		return this._name;
	}
	
	@Override
	public LinkedList<Attribute> getAttributes(){
		
		StringAttribute name = new StringAttribute("Name", this._name);
		IntAttribute wins = new IntAttribute("Wins", _wins);
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(name);
		atts.add(wins);
		
		return atts;
	}

	private void setStringAttribute(StringAttribute att){
		if(att.getTitle().equals("Name")){
			this._name = att.getAttribute();
		}
	}
	
	private void setIntAttribute(IntAttribute att){
		if(att.getTitle().endsWith("Wins")){
			this._wins = att.getAttribute();
		}
	}
	@Override
	public void setAttribute(Attribute attribute) {
		Attribute.Type t = attribute.getType();
		if(t == Attribute.Type.INT)
			setIntAttribute((IntAttribute)attribute);
		else if(t == Attribute.Type.STRING)
			setStringAttribute((StringAttribute)attribute);
	}
	
	@Override
	public String toString(){
		String r = "Name: " + this._name;
		r += " Wins: " + String.valueOf(this._wins);
		return r;
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		return _t.getCategories().get(0);
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {		
	}

	@Override
	public boolean deleteFromGrouping() {
		return _category.deleteMember(this);
	}

	@Override
	public void setName(String name) {
		this._name = name;
		
	}


}
