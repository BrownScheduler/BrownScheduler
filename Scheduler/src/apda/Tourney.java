package apda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exception.InvalidRoundException;

import backbone.Grouping;
import backbone.Round;
import backbone.Tournament;

public class Tourney implements Tournament{

	final int _totalRounds;
	public TeamGrouping _teams;
	public JudgeGrouping _judges;
	public SchoolGrouping _schools;
	public List<MyRound> _rounds;
	
	public Tourney(int numRounds){
		_totalRounds = numRounds;
		_teams = new TeamGrouping(this, "Teams");
		_judges = new JudgeGrouping(this, "Judges");
		_schools = new SchoolGrouping(this, "Schools");
		_rounds = new ArrayList<MyRound>();
	}
	
	public ArrayList<ArrayList<Team>> createTeamBrackets(ArrayList<Team> teams) {
		
		return null;
	}
	
	@Override
	public Round createNextRound() throws InvalidRoundException {
		//the tournament is over, no more rounds should be created
		if(_rounds.size() > _totalRounds)
			throw new InvalidRoundException("The tournament has already had all it's rounds");
		int numCreating = _rounds.size();
		//the previous round wasn't completely paired
		if(_rounds.size() > 0 && _rounds.get(_rounds.size() - 1).isFinished())
			throw new InvalidRoundException("The previous round is not over yet!");
		ArrayList<Team> teamsToPair = new ArrayList<Team>();
		for(Team t : this._teams._members){
			if(t.stillInTournament)
				teamsToPair.add(t);
		}
		ArrayList<ArrayList<Team>> sortedTeams = createTeamBrackets(teamsToPair);
		LinkedList<Judge> availableJudges = new LinkedList<Judge>();
		for(Judge j : _judges._members){
			if(j.isAvailable(numCreating)) availableJudges.add(j);
		}
		//Not enough judges to actually pair this round!
		if()
		return null;
	}

	@Override
	public List<Grouping> getCategories() {
		LinkedList<Grouping> cats = new LinkedList<Grouping>();
		cats.add(_schools);
		cats.add(_judges);
		cats.add(_teams);
		return cats;
	}

	@Override
	public Tournament getNew() {
		return new Tourney(_totalRounds);
	}

	@Override
	public List<Round> getRounds() {
		return new ArrayList<Round>(_rounds);
	}

}
