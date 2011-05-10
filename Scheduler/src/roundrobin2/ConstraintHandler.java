package roundrobin2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ConstraintHandler {

	private Turn _t;
	private int _roundNum;
	private Round _r;
	public ConstraintHandler(Turn tournament, int roundNum) {
		_t = tournament;
		_roundNum = roundNum;
	}

	public Round createRound() {
		int fieldNum = 0;
		_r = new Round(_t, "Round " + Integer.toString(_roundNum), _roundNum);
		LinkedList<Team> unpaired = new LinkedList<Team>(_t.getTeams().getMembers());
		Collections.shuffle(unpaired);
		int i = 1;
		while(!unpaired.isEmpty()){
			Field f = null;
			if(_t.getFields().getMembers().size() > 0){
				f = _t.getFields().getMembers().get(fieldNum);
				fieldNum = (fieldNum + 1) % _t.getFields().getMembers().size(); 
			}
			_r.addGame(getNextGame(unpaired, i++, f));
		}
		return _r;
	}
	
	public Game getNextGame(LinkedList<Team> unpaired, int number, Field field){
		Team t1 = unpaired.pop();
		Team t2 = null;
		Game g = new Game(_t, this._r, "Round " + Integer.toString(_roundNum) + " Game " + number);
		for(Team t : unpaired){
			if(!t1.hasfacedBefore(t, _roundNum)){
				t2 = t;
				t1.addFacedTeam(t2, _roundNum);
				unpaired.remove(t);
				break;
			}
		}
		g.setHome(t1);
		g.setAway(t2);
		g.setField(field);
		RefereeGrouping possibleRefs = g.getPossibleRefs(field);
		LinkedList<Referee> shuffled = new LinkedList<Referee>(possibleRefs.getMembers());
		Collections.shuffle(shuffled);
		if(!shuffled.isEmpty())
			g.setHeadReferee(shuffled.pop());
		if(!shuffled.isEmpty())
			g.setAssistantReferee(shuffled.pop());
		return g;
	}

}
