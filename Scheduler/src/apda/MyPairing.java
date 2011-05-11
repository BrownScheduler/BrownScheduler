package apda;

import java.util.LinkedList;
import java.util.List;

import backbone.*;

/**
 * Class that holds each pairing,
 * which is where the actual individual competitions take place.
 * @author matt
 *
 */
public class MyPairing implements backbone.Pairing{

	Team _gov;
	Team _opp;
	Judge _judge;
	Team _winner;
	Tourney _t;
	MyRound _roundIn;
	
	public MyPairing(Tourney t){
		_gov = null;
		_opp = null;
		_judge = null;
		_winner = null;
		_t = t;
	}
	
	public MyPairing(Tourney t, Team gov, Team opp, Judge judge) {
		_gov = gov;
		_opp = opp;
		_judge = judge;
		_t = t;
	}
	
	public void setWinner(Team t){
		_winner = t;
		_winner.setWins(_winner.getWins() + 1);
	}
	
	public boolean isFinished(){
		return _winner != null;
	}
	
	@Override
	public String toString(){
		String r = "";
		r += "Gov: ";
		if(_gov != null){
			r += _gov.getName();
		}
		r += "\nOpp: ";
		if(_opp != null){
			r += _opp.getName();
		}
		r+= "\nJudge: ";
		if(_judge != null){
			r += _judge.getName();
		}
		r += "\nWinner: ";
		if(_winner != null){
			r += _winner.getName();
		}
		return r +"\n";
	}

	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		return atts;
	}

	private void setUnitAttribute(UnitAttribute<Unit> att){
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.UNIT) 
			setUnitAttribute((UnitAttribute<Unit>)attribute);
		
	}

	@Override
	public String getName() {
		return "Pairing";
	}

	@Override
	public Grouping getMemberOf() {
		return _roundIn;
	}

	@Override
	public void setMemberOf(Grouping g) {
		_roundIn = g;
		
	}

	@Override
	public boolean deleteFromGrouping() {
		if(_roundIn == null) return false;
		return _roundIn.deleteMember(this);
		
	}


	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}

}
