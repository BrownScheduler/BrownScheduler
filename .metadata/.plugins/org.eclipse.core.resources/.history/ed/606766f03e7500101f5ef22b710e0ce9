package plugin1;
import java.util.LinkedList;

import old_backbone.*;


public class Team extends CompetitiveUnit {
	
	private int _wins;
	public Team(String name){
		super(name);
		_wins = 0;
	}
	
	public void addWin(){
		_wins++;
	}
	
	public void setWins(int i){
		_wins = i;
	}
	
	public String getName(){
		return this._name;
	}
	
	public LinkedList<Attribute> getAttributes(){
		
		StringAttribute name = new StringAttribute("Name", this._name);
		IntAttribute wins = new IntAttribute("Wins", _wins);
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(name);
		atts.add(wins);
		
		return atts;
	}

}
