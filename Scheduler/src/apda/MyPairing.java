package apda;

import java.util.HashMap;
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
	private double _loRank;
	private double _moPoints;
	private double _moRank;
	
	private Tourney _t;
	final int _roundNum;
	final int _pairingNum;
	private HashMap<Team, Double> _oppSpeaks;
	
	public String conflictMessage = "Everything's all right!";
	
	public MyPairing(Tourney t, int roundNum, int pairingNum){
		_gov = null;
		_opp = null;
		_judge = null;
		_winner = null;
		_t = t;
		_roundNum = roundNum;
		_pairingNum = pairingNum;
	}
	
	public MyPairing(Tourney t, Team gov, Team opp, Judge judge, 
			int roundNum, int pairingNum, HashMap<Team, Double> oppSpeaks) {
		_gov = gov;
		_opp = opp;
		_judge = judge;
		_t = t;
		_roundNum = roundNum;
		_pairingNum = pairingNum;
		_oppSpeaks = oppSpeaks;
	}
	
	
	public boolean isFinished(){
		return _winner != null;
	}
	
	/**
	 * Returns a conflict gradient between -1 and 1 
	 * (1 being not conflicted, -1 being most conflicted)
	 * @return the conflict score, between -1
	 */
	public double getConflictScore(){
		this.conflictMessage = "";
		if(_winner != null){
			this.conflictMessage = "This pairing is complete!";
			return 1.0;
		}
		if(_gov == null || _opp == null){
			this.conflictMessage = "One of these teams is non-existant!\n\n";
			return 0.0;
		}
		double cScore = 1.0;
		if(this._roundNum == 0){
			if(_gov.isFullSeed() && _opp.isFullSeed()){
				this.conflictMessage = "The Gov and Opp are both Fully Seeded!\n\n";
				cScore = -0.8;
			}
			if((_gov.isFullSeed() && _opp.isHalfSeed()) || (_opp.isFullSeed() && _gov.isHalfSeed())){
				this.conflictMessage = "One team is fully seeded,\n" +
						"and the other is a half seed!\n\n";
				cScore = -0.6;
			}
			if((_gov.isFullSeed() && _opp.isFreeSeed()) || (_opp.isFullSeed() && _gov.isFreeSeed())){
				this.conflictMessage = "One team is fully seeded,\n" +
					"and the other is a free seed!\n\n";
				cScore = -0.4;
			}
			if(_gov.isHalfSeed() && _opp.isHalfSeed()){
				this.conflictMessage = "Both teams are Half Seeds!\n\n";
				cScore = -0.2;
			}
			if((_gov.isHalfSeed() && _opp.isFreeSeed()) || (_opp.isHalfSeed() && _gov.isFreeSeed())){
				this.conflictMessage = "One team is a Half seed,\n " +
						"and the other is a Free seed!\n\n";
				cScore = 0.1;
			}
			if(_gov.isFreeSeed() && _opp.isFreeSeed()){
				this.conflictMessage = "Both teams are Free Seeds!\n\n";
				cScore = 0.4;
			}
		}
		
		if(_gov.facedBefore.contains(_opp)){
			this.conflictMessage += "These Teams have faced each other before!\n\n";
			cScore = Math.min(-.9, cScore);
		}
		if(_gov.school == _opp.school){
			this.conflictMessage += "These Teams are from the same school!\n\n";
			cScore = Math.min(-.95, cScore);
		}
		if(_gov == _opp){
			this.conflictMessage += "ACK! Both gov AND opp are the SAME TEAM!\n\n";
			cScore = Math.min(-1.0, cScore);
		}
		if(_gov.numGovs > Constants.MAXGOVS && _opp.numGovs > Constants.MAXGOVS){
			this.conflictMessage += "These teams have both maxed out their govs!\n\n";
			cScore = Math.min(-0.6, cScore);
		}
		if(_gov.numOpps > Constants.MAXOPPS && _opp.numOpps > Constants.MAXOPPS){
			this.conflictMessage += "These teams have both maxed out their opps!\n\n";
			cScore = Math.min(-0.6, cScore);
		}
		//double origOppSpeaks = _oppSpeaks.get(_gov);
		
		if(_judge == null){
			this.conflictMessage = "There is no judge!";
			cScore = -1.0;
		}
		else if(_judge != null && !_judge.canJudge(_gov)){
			this.conflictMessage = "The judge " + _judge.getName() + "can't judge the Gov team!";
			cScore = Math.min(cScore, -0.8);
		}
		else if(_judge != null && !_judge.canJudge(_opp)){
			this.conflictMessage = "The judge " + _judge.getName() + "can't judge the Opp team!";
			cScore = Math.min(cScore, -0.8);
		}
		return cScore;
	}
	
	@Override
	public String getConflictMessage(){
		//run the conflict score to get the message
		getConflictScore();
		return this.conflictMessage;
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
		atts.add(new UnitAttribute<Debater>("LO", _lo, posOppDebaters));
		atts.add(new UnitAttribute<Debater>("MO", _mo, posOppDebaters));
		
		atts.add(new DoubleAttribute("PM Points", _pmPoints));
		atts.add(new DoubleAttribute("MG Points", _mgPoints));
		atts.add(new DoubleAttribute("LO Points", _loPoints));
		atts.add(new DoubleAttribute("MO Points", _moPoints));
		atts.add(new DoubleAttribute("PM Rank", _pmRank));
		atts.add(new DoubleAttribute("LO Rank", _loRank));
		atts.add(new DoubleAttribute("MG Rank", _mgRank)); 
		atts.add(new DoubleAttribute("MO Rank", _moRank)); 

		return atts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setAttribute(Attribute att) {
		if(att.getTitle().equals("PM")){
			_pm = ((UnitAttribute<Debater>)att).att;
		}else if(att.getTitle().equals("MG")){
			_mg = ((UnitAttribute<Debater>)att).att;
		}else if(att.getTitle().equals("LO")){
			_lo = ((UnitAttribute<Debater>)att).att;
		}else if(att.getTitle().equals("MO")){
			_mo = ((UnitAttribute<Debater>)att).att;
		}else if(att.getTitle().equals("Gov")){
			_gov = ((UnitAttribute<Team>)att).att;
		}else if(att.getTitle().equals("Opp")){
			_opp = ((UnitAttribute<Team>)att).att;
		}else if(att.getTitle().equals("Judge")){
			_judge = ((UnitAttribute<Judge>)att).att;
		}else if(att.getTitle().equals("Winner")){
			setWinner(((UnitAttribute<Team>)att).att);	
		}else if(att.getTitle().equals("PM Points")){
			_pmPoints = ((DoubleAttribute)att).getAttribute();
			if(_pm != null) _pm.addSpeaks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("MG Points")){
			_mgPoints = ((DoubleAttribute)att).getAttribute();
			if(_mg != null) _mg.addSpeaks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("LO Points")){
			_loPoints = ((DoubleAttribute)att).getAttribute();
			if(_lo != null) _lo.addSpeaks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("MO Points")){
			_moPoints = ((DoubleAttribute)att).getAttribute();
			if(_mo != null) _mo.addSpeaks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("PM Rank")){
			_pmRank = ((DoubleAttribute)att).getAttribute();
			if(_pm != null) _pm.addRanks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("MG Rank")){
			_mgRank = ((DoubleAttribute)att).getAttribute();
			if(_mg != null) _mg.addRanks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("LO Rank")){
			_loRank = ((DoubleAttribute)att).getAttribute();
			if(_lo != null) _lo.addRanks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}else if(att.getTitle().equals("MO Rank")){
			_moRank = ((DoubleAttribute)att).getAttribute();
			if(_mo != null) _mo.addRanks(((DoubleAttribute)att).getAttribute(), _roundNum);
		}
		
	}

	private void setWinner(Team team) {
		if(_winner == null && team != null){
			_winner = team;
			_winner.wins++;
			if(_winner == _gov && _opp != null){
				_opp.losses++;
			}
			else if(_winner == _opp && _gov != null) _gov.losses++;
		}
		if(_winner != null && team == null){
			_winner.wins--;
			if(_winner == _gov) _opp.losses--;
			else if(_winner == _opp) _gov.losses--;
			_winner = team;
		}else if(_winner != null && team != null){
			_winner.wins--;
			if(_winner == _gov) _opp.losses--;
			else if(_winner == _opp) _gov.losses--;
			_winner = team;
			_winner.wins++;
			if(_winner == _gov) _opp.losses++;
			else if(_winner == _opp) _gov.losses++;
		}
		if(_gov != null && _opp != null){
			_opp.facedBefore.add(_gov);
			_gov.facedBefore.add(_opp);
			if(_judge != null){
				_judge.addCantJudge(_opp);
				_judge.addCantJudge(_gov);
			}
		}
		if(_gov != null){
			if(_lo != null){
				_lo.addSpeaks(_loPoints, _roundNum);
				_lo.addRanks(_loRank, _roundNum);
			}
			if(_mo != null){
				_mo.addSpeaks(_moPoints, _roundNum);
				_mo.addRanks(_moRank, _roundNum);
			}
			if(_pm != null){
				_pm.addSpeaks(_pmPoints, _roundNum);
				_pm.addRanks(_pmRank, _roundNum);
			}
			if(_mg != null){
				_mg.addSpeaks(_mgPoints, _roundNum);
				_mg.addRanks(_mgRank, _roundNum);
			}
		}
		
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
		if(_gov != null){
			if(_opp != null){
				_gov.facedBefore.remove(_opp);
				_opp.facedBefore.remove(_gov);
			}
		}
		return _t._rounds.get(_roundNum).deleteMember(this);
	}


	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}

}
