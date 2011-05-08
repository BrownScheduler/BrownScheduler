package plugin1;

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
	Grouping _roundIn;
	
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
		atts.add(new UnitAttribute<Team>("Gov", _gov, _t.getCategories().get(0)));
		atts.add(new UnitAttribute<Team>("Opp", _opp, _t.getCategories().get(0)));
		atts.add(new UnitAttribute<Judge>("Judge", _judge, _t.getCategories().get(1)));
		GovOppGrouping teamGroup = new GovOppGrouping("Teams");
		GovOppUnit n = new GovOppUnit();
		GovOppUnit g = new GovOppUnit(true);
		GovOppUnit o = new GovOppUnit(false);
		teamGroup.addMember(n);
		teamGroup.addMember(g);
		teamGroup.addMember(o);
		GovOppUnit winner = n;
		if(_winner == _gov) winner = g;
		else if(_winner == _opp) winner = o;
		atts.add(new UnitAttribute<GovOppUnit>("Winner", winner, teamGroup));
		return atts;
	}

	private void setUnitAttribute(UnitAttribute<Unit> att){
		String title = att.getTitle();
		if(title.equals("Gov")) _gov = (Team)att.att;
		else if(title.equals("Opp")) _opp = (Team)att.att;
		else if(title.equals("Judge")) _judge = (Judge)att.att;
		else if(title.equals("Winner")){
			GovOppUnit newWinner = (GovOppUnit)att.att;
			if(newWinner == null){
				if(_winner != null){
					_winner.setWins(_winner.getWins() - 1);
					_winner = null;
					if(_judge != null) {
						_judge._conflictedTeams.remove(_gov);
						_judge._conflictedTeams.remove(_opp);
					}
					
				}
			}
			else if(!newWinner.isGov && !newWinner.isOpp){
				if(_winner != null){
					_winner.setWins(_winner.getWins() - 1);
					_winner = null;
					if(_judge != null) {
						_judge._conflictedTeams.remove(_gov);
						_judge._conflictedTeams.remove(_opp);
					}
					
				}
			}
			else if(newWinner.isGov){
				if(_winner == null){
					_winner = _gov;
					if(_winner != null) _winner.setWins(_winner.getWins() + 1);
				}else if(_winner == _opp){
					if(_winner != null) _winner.setWins(_winner.getWins() - 1);
					_winner = _gov;
					if(_winner != null) _winner.setWins(_winner.getWins() + 1);
				}
				
			}else if(newWinner.isOpp){
				if(_winner == null){
					_winner = _opp;
					if(_winner != null) _winner.setWins(_winner.getWins() + 1);
				}else if(_winner == _gov){
					if(_winner != null) _winner.setWins(_winner.getWins() - 1);
					_winner = _opp;
					if(_winner != null) _winner.setWins(_winner.getWins() + 1);
				}
			}			
		}
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
	public Unit getBlank() {
		// TODO Auto-generated method stub
		return new MyPairing(_t);
	}

	@Override
	public Grouping getMemberOf() {
		// TODO Auto-generated method stub
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

}
