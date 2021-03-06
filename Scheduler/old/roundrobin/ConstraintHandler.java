package roundrobin;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import backbone.Round;

public class ConstraintHandler {

	private Tournament _t;
	private int _roundNum;
	public ConstraintHandler(Tournament tournament, int roundNum) {
		_t = tournament;
		_roundNum = roundNum;
	}

	public Round createRound(List<Field> members) {
		Round r = new Round("Round" + Integer.toString(_roundNum));
		Random rand = new Random();
		if(members.size() == 0) return null;
		int offset = 0;
		HashSet<Team> paired = new HashSet<Team>();
		for(Team t : _t.getTeams().getMembers()){
			for(Team other : _t.getTeams().getMembers()){
				if(other != t && t.hasfacedBefore(other, _roundNum) && !paired.contains(other)){
					paired.add(t);
					paired.add(other);
					Field f = members.get(offset);
					offset = offset + 1 % members.size();
					Game g = new Game(_t, r, "", f);
					if(rand.nextBoolean()){
						g.setHome(t);
						g.setAway(other);
					}else{
						g.setHome(t);
						g.setAway(other);
					}
					r.addPairing(g);
				}
			}
		}
		
		for(Team t : _t.getTeams().getMembers()){
			if(!paired.contains(t)){
				Field f = members.get(offset);
				offset = offset + 1 % members.size();
				Game g = new Game(_t, r, "", f);
				paired.add(t);
				boolean ishome = rand.nextBoolean();
				if(ishome) g.setHome(t);
				else g.setAway(t);
				for(Team other : _t.getTeams().getMembers()){
					if(!paired.contains(other)){
						paired.add(other);
						if(ishome) g.setAway(other);
						else g.setHome(other);
					}
				}
			}
		}
		return r;
	}

}
