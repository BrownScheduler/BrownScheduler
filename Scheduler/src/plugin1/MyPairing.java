package plugin1;

import java.util.LinkedList;

import backbone.*;

/**
 * Class that holds each pairing,
 * which is where the actual individual competitions take place.
 * @author matt
 *
 */
public class MyPairing extends backbone.Pairing{

	Team _gov;
	Team _opp;
	Judge _judge;
	Team _winner;
	
	public MyPairing(Team gov, Team opp, Judge judge) {
		super(gov, opp);
		_gov = gov;
		_opp = opp;
		_judge = judge;
		members.add(_gov);
		members.add(_opp);
		members.add(_judge);
		members.add(_winner);
	}
	
	public void setWinner(Team t){
		_winner = t;
	}
	
	public boolean isFinished(){
		return _winner != null;
	}
	
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

}
