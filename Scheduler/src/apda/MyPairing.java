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

	private Team _gov;
	private Team _opp;
	private Judge _judge;
	private Team _winner;
	
	private Debater _pm;
	private Debater _mg;
	private Debater _lo;
	private Debater _mo;
	
	private double _pmPoints;
	private double _pmRank;
	private double _mgPoints;
	private double _mgRank;
	
	private double _loPoints;
	private double _loRanks;
	private double _moPoints;
	private double _moRank;
	
	private Tourney _t;
	final int _roundNum;
	final int _pairingNum;
	
	public MyPairing(Tourney t, int roundNum, int pairingNum){
		_gov = null;
		_opp = null;
		_judge = null;
		_winner = null;
		_t = t;
		_roundNum = roundNum;
		_pairingNum = pairingNum;
	}
	
	public MyPairing(Tourney t, Team gov, Team opp, Judge judge, int roundNum, int pairingNum) {
		_gov = gov;
		_opp = opp;
		_judge = judge;
		_t = t;
		_roundNum = roundNum;
		_pairingNum = pairingNum;
	}
	
	
	public boolean isFinished(){
		return _winner != null;
	}
	
	@Override
	public String toString(){
		return "Round"+Integer.toString(_roundNum)+
			" Pairing" + Integer.toString(_pairingNum);
	}

	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new UnitAttribute<Team>("Gov", _gov, _t._teams));
		atts.add(new UnitAttribute<Team>("Opp", _opp, _t._teams));
		atts.add(new UnitAttribute<Judge>("Judge", _judge, _t._judges));
		TeamGrouping posWinners = new TeamGrouping(_t, "Winner");
		if(_gov != null && _gov.getName() != "Bye") posWinners.addMember(_gov);
		if(_opp != null && _opp.getName() != "Bye") posWinners.addMember(_opp);
		atts.add(new UnitAttribute<Team>("Winner", _winner, posWinners));
		
		DebaterGrouping posGovDebaters = new DebaterGrouping(_t, "Pos Gov Units");
		if(_gov != null && _gov.d1.att != null) posGovDebaters.addMember(_gov.d1.att);
		if(_gov != null && _gov.d2.att != null) posGovDebaters.addMember(_gov.d2.att);
		atts.add(new UnitAttribute<Debater>("PM", _pm, posGovDebaters));
		atts.add(new UnitAttribute<Debater>("MG", _mg, posGovDebaters));
		DebaterGrouping posOppDebaters = new DebaterGrouping(_t, "Pos Opp Units");
		if(_opp != null && _opp.d1.att != null) posOppDebaters.addMember(_opp.d1.att);
		if(_opp != null && _opp.d2.att != null) posOppDebaters.addMember(_opp.d2.att);
		atts.add(new UnitAttribute<Debater>("LO", _pm, posOppDebaters));
		atts.add(new UnitAttribute<Debater>("MO", _mg, posOppDebaters));
		
		atts.add(new DoubleAttribute("PM Points", 0.0));
		atts.add(new DoubleAttribute("PM Rank", 0.0));
		atts.add(new DoubleAttribute("MG Points", 0.0)); 
		atts.add(new DoubleAttribute("MG Points", 0.0));
		
		atts.add(new DoubleAttribute("LO Points", 0.0));
		atts.add(new DoubleAttribute("LO Rank", 0.0));
		atts.add(new DoubleAttribute("MO Points", 0.0)); 
		atts.add(new DoubleAttribute("MO Points", 0.0)); 

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
		return "Round"+Integer.toString(_roundNum)+
		" Pairing" + Integer.toString(_pairingNum);
	}

	@Override
	public Grouping getMemberOf() {
		return _t.getRounds().get(_roundNum);
	}

	@Override
	public void setMemberOf(Grouping g) {
		//_roundIn = g;
		
	}

	@Override
	public boolean deleteFromGrouping() {
		return _t._rounds.remove(this);
	}


	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}

}
