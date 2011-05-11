package apda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
	
	public MyRound createFirstRound(ArrayList<Team> teamsToPair, LinkedList<Judge> availableJudges, boolean suppress) throws InvalidRoundException{
		if(teamsToPair.size() != _teams._members.size() && !suppress)
			throw new InvalidRoundException("Not every team is available!" +
					"\nThis means not every team will be paired into Round 1!\n" +
					"To pair anyways, hit OK and create a new round.\n\n" +
					"Warning! This will suppress all other warnings as well!");
		for(School s : this._schools.getMembers()){
			if(!s.hasFreeSeed() && !suppress)
				throw new InvalidRoundException("Not every school has a free seed!\n"
						+ s.getName() + " does not have a free seed!");
		}
		//rounNum is one less than the string returned
		MyRound r = new MyRound(this, "Round 1", _rounds.size());
		LinkedList<Team> fullSeeds = new LinkedList<Team>();
		LinkedList<Team> halfSeeds = new LinkedList<Team>();
		LinkedList<Team> freeSeeds = new LinkedList<Team>();
		LinkedList<Team> nonSeeds = new LinkedList<Team>();
		LinkedList<Team> allTeams = new LinkedList<Team>();
		allTeams.addAll(fullSeeds);
		allTeams.addAll(halfSeeds);
		allTeams.addAll(freeSeeds);
		allTeams.addAll(nonSeeds);
		for(Team t : teamsToPair){
			if(t.isFullSeed()) fullSeeds.add(t);
			else if(t.isHalfSeed()) halfSeeds.add(t);
			else if(t.isFreeSeed()) freeSeeds.add(t);
			else nonSeeds.add(t);
		}
		PriorityQueue<Judge> judges = new PriorityQueue<Judge>(1, new Judge.JudgeComparator());
		judges.addAll(availableJudges);
		LinkedList<Team> allTeamsRev = new LinkedList<Team>();
		allTeams.addAll(nonSeeds);
		allTeams.addAll(freeSeeds);
		allTeams.addAll(halfSeeds);
		allTeams.addAll(fullSeeds);
		
		while(!allTeams.isEmpty()){
			Team t1 = allTeams.pop();
			allTeamsRev.remove(t1);
			Team t2 = Team.createBye();
			for(Team other : allTeamsRev){
				
			}
		}
		
		return null;
		
	}
	
	@Override
	public Round createNextRound(boolean suppress) throws InvalidRoundException {
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
		if(teamsToPair.size() == 0)
			throw new InvalidRoundException("There are no teams to pair!");
		LinkedList<Judge> availableJudges = new LinkedList<Judge>();
		for(Judge j : _judges._members){
			if(j.isAvailable(numCreating)) availableJudges.add(j);
		}
		//Not enough judges to actually pair this round!
		if(availableJudges.size() < teamsToPair.size() / 2 && !suppress)
			throw new InvalidRoundException("There aren't enough judges to judge this round!");
		//it's the first round, do special stuff
		if(_rounds.size() == 0)
			return createFirstRound(teamsToPair, availableJudges, suppress);
		
		ArrayList<ArrayList<Team>> sortedTeams = createTeamBrackets(teamsToPair);
		
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
