package roundrobin2;

import backbone.Pairing;

public class Round extends backbone.Round {

	private Turn _t;
	private int _roundNum;
	public Round(Turn t, String name, int roundNum) {
		super(name);
		_t = t;
		_roundNum = roundNum;
	}
	
	public void addGame(Game g){
		this.pairings.add(g);
	}
	
	@Override
	public Game getBlank(){
		return new Game(_t, this, "Round " + Integer.toString(_roundNum) + " Game " + Integer.toString(this.pairings.size()));
	}
	
	@Override
	public String getName(){
		return "Round " + Integer.toString(_roundNum);
	}
	
	@Override
	public boolean deleteMember(Pairing member){
		Game g = (Game)member;
		Team home = g.getHomeTeam();
		Team away = g.getAwayTeam();
		if(home != null){
			home.removeFacedTeam(away);
		}
		if(away != null){
			away.removeFacedTeam(home);
		}
		return this.pairings.remove(member);
		
	}

}
