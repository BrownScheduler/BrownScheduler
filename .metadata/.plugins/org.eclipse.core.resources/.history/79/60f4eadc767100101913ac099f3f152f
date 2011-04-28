package plugin1;
import java.util.LinkedList;

import backbone.*;

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
	
	public LinkedList<Attribute> getAttributes(){
		
		StringAttribute name = new StringAttribute("Name", this._name);
		IntAttribute wins = new IntAttribute("Wins", _wins);
		
		return null;
	}

}
