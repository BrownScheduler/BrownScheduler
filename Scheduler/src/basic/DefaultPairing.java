package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import backbone.*;

/**
 * Class that holds each pairing,
 * which is where the actual individual competitions take place.
 * @author matt
 *
 */
public class DefaultPairing implements backbone.Pairing{

	ArrayList<UnitAttribute> units;
	ArrayList<Attribute> atts;
	
	public DefaultPairing(Team gov, Team opp, Judge judge) {
		_gov = gov;
		_opp = opp;
		_judge = judge;
	}
	
	public void setWinner(Team t){
		_winner = t;
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
		atts.add(new UnitAttribute<Team>("Gov", _gov));
		atts.add(new UnitAttribute<Team>("Opp", _opp));
		atts.add(new UnitAttribute<Judge>("Judge", _judge));
		atts.add(new UnitAttribute<Team>("Winner", _winner));
		return atts;
	}

	private void setUnitAttribute(UnitAttribute<Unit> att){
		String title = att.getTitle();
		if(title.equals("Gov")) _gov = (Team)att.att;
		else if(title.equals("Opp")) _opp = (Team)att.att;
		else if(title.equals("Judge")) _judge = (Judge)att.att;
		else if(title.equals("Winner")) _winner = (Team)att.att;
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

}
