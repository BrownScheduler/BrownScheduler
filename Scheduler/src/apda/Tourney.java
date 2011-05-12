package apda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import middleend.TMNTScheduler;
import roundrobin2.Turn;

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
		//_debaters = new DebaterGrouping(this, "Debaters");
		_rounds = new ArrayList<MyRound>();
	}
	
	public ArrayList<ArrayList<Team>> createTeamBrackets(ArrayList<Team> teams) {
		ArrayList<ArrayList<Team>> brackets = new ArrayList<ArrayList<Team>>();
		boolean needPullUp = false;
		for(int i = 0; i < _rounds.size() + 1; i++){
			ArrayList<Team> toAdd = new ArrayList<Team>();
			for(Team t : _teams._members){
				if(t.wins == i){
					toAdd.add(t);
				}
			}
			if(_rounds.size() == 0) Collections.shuffle(toAdd);
			else{
				Collections.sort(toAdd, new Team.TeamComparator());
			}
			if(needPullUp){
				int middle = toAdd.size() / 2;
				for(int o = middle; o < toAdd.size(); o++){
					if(toAdd.get(o).pullUpRound == -1){
						Team toPullUp = toAdd.remove(o);
						toPullUp.pullUpRound = _rounds.size();
						brackets.get(i - 1).add(toPullUp);
						Collections.sort(brackets.get(i - 1), new Team.TeamComparator());
						needPullUp = false;
					}
				}
				//override the fact that the team's been pulled up already
				if(needPullUp){
					if(toAdd.size() != 0){
						Team toPullUp = toAdd.remove(middle);
						toPullUp.pullUpRound = _rounds.size();
						brackets.get(i - 1).add(toPullUp);
						Collections.sort(brackets.get(i - 1), new Team.TeamComparator());
						needPullUp = false;
					}
					
				}
			}
			if(toAdd.size() %2 == 1) needPullUp = true;
			brackets.add(0, toAdd);
		}
		if(needPullUp){
			brackets.get(brackets.size() - 1).add(new Bye(this));
		}
		return brackets;
	}
	
	@Override
	public Round createNextRound(boolean suppress) throws InvalidRoundException {
		//Check to make sure every team has a Free Seed, if it's the first round
		if(_rounds.isEmpty() && !suppress){
			for(School s : _schools.getMembers()){
				if(s._freeSeed == null){
					throw new InvalidRoundException("Not all teams have a free seed!\n"+
							s.getName() + " does not have a Free Seed!\n\n" +
					"Do you want to continue pairing anyways?");
				}
			}
		}
		//the tournament is over, no more rounds should be created
		if(_rounds.size() >= _totalRounds)
			throw new InvalidRoundException("The tournament has already had all it's rounds");
		int numCreating = _rounds.size();
		//the previous round wasn't completely paired
		if(_rounds.size() > 0 && !_rounds.get(_rounds.size() - 1).isFinished()){
			String toAdd = "";
			if(suppress) toAdd = "You must finish tabbing the previous round before\n" +
					"moving on to the next!";
			throw new InvalidRoundException("The previous round is not over yet!\n\n" +
					toAdd);
		}
			
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
			throw new InvalidRoundException("There aren't enough judges to judge this round!" +
					"\nWould you like to continue pairing anyways?\n\n" +
					"No to add more judges, Yes to continue pairing");
		//it's the first round, do special stuff
		MyRound round = new MyRound(this, _rounds.size());
		//sorted within brackets by high speaks to low speaks, with the BYE in the correct bracket
		ArrayList<ArrayList<Team>> sortedTeams = createTeamBrackets(teamsToPair);
		//do the pairings for each bracket
		for(ArrayList<Team> teamsList : sortedTeams){
			HashMap<Team, Double> oppSpeaks = new HashMap<Team, Double>();
			ArrayList<Team> almostPairings = createBracketPairings(teamsList, oppSpeaks);
			ArrayList<MyPairing> pairings = addJudges(almostPairings, availableJudges, oppSpeaks);
			for(MyPairing p : pairings){
				round.addPairing(p);
			}
		}
		_rounds.add(round);
		return round;
	}
	
	private ArrayList<MyPairing> addJudges(ArrayList<Team> almostPairings,
			LinkedList<Judge> judges, HashMap<Team, Double> oppSpeaks) {
		ArrayList<MyPairing> pairings = new ArrayList<MyPairing>();
		ArrayList<Team> unPaired1 = new ArrayList<Team>();
		ArrayList<Team> unPaired2 = new ArrayList<Team>();
		int next = 0;
		for(int i = 0; i < almostPairings.size() /2; i++){
			Team t1 = almostPairings.get(i);
			Team t2 = almostPairings.get(almostPairings.size() - 1 - i);
			boolean wasPaired = false;
			for(Judge j : judges){
				if(j.canJudge(t1) && j.canJudge(t2)){
					if(t1.numGovs > t2.numGovs)
						pairings.add(new MyPairing(this, t2, t1, j, _rounds.size(), next++, oppSpeaks));
					else if(t1.numOpps > t2.numOpps)
						pairings.add(new MyPairing(this, t1, t2, j, _rounds.size(), next++, oppSpeaks));
					else{
						Random r = new Random();
						if(r.nextBoolean())
							pairings.add(new MyPairing(this, t1, t2, j, _rounds.size(), next++, oppSpeaks));
						else pairings.add(new MyPairing(this, t2, t1, j, _rounds.size(), next++, oppSpeaks));
					}
					judges.remove(j);
					wasPaired = true;
					break;
				}
			}
			if(!wasPaired){
				unPaired1.add(t1);
				unPaired2.add(0, t2);
			}
		}
		unPaired1.addAll(unPaired2);
		for(int i = 0; i < unPaired1.size() / 2; i++){
			Team t1 = almostPairings.get(i);
			Team t2 = almostPairings.get(almostPairings.size() - 1 - i);
			Judge j = null;
			if(!judges.isEmpty()) j = judges.pop();
			if(t1.numGovs > t2.numGovs)
				pairings.add(new MyPairing(this, t2, t1, j, _rounds.size(), next++, oppSpeaks));
			else if(t1.numOpps > t2.numOpps)
				pairings.add(new MyPairing(this, t1, t2, j, _rounds.size(), next++, oppSpeaks));
			else{
				Random r = new Random();
				if(r.nextBoolean())
					pairings.add(new MyPairing(this, t1, t2, j, _rounds.size(), next++, oppSpeaks));
				else pairings.add(new MyPairing(this, t2, t1, j, _rounds.size(), next++, oppSpeaks));
			}
		}
		return pairings;
	}

	public double calculateConflictScore(Team t1, Team t2, HashMap<Team, Double> opposingSpeaks){
		double score = 0.0;
		if(t1.getName().equals("Bye") || t2.getName().equals("Bye")){
			if(t1.byeRound == -1 && t2.byeRound == -1)
				return 0.0;
			else return 200;
		}
		if(this._rounds.size() == 0){
			if(t1.isFullSeed() && t2.isFullSeed()) score += 500;
			if((t1.isFullSeed() && t2.isHalfSeed()) || (t2.isFullSeed() && t1.isHalfSeed()))
					score += 450;
			if((t1.isFullSeed() && t2.isFreeSeed()) || (t2.isFullSeed() && t1.isFreeSeed()))
				score += 400;
			if(t1.isHalfSeed() && t2.isHalfSeed()) score += 300;
			if((t1.isHalfSeed() && t2.isFreeSeed()) || (t2.isHalfSeed() && t1.isFreeSeed()))
					score += 250;
			if(t1.isFreeSeed() && t2.isFreeSeed()) score += 30;
		}
		if(t1.facedBefore.contains(t2)) score += 200;
		if(t1.school == t2.school) score += 2000;
		if(t1 == t2) score += 10000000000000.0;
		if(t1.numGovs > Constants.MAXGOVS && t2.numGovs > Constants.MAXGOVS)
			score += 100;
		if(t1.numOpps > Constants.MAXOPPS && t2.numOpps > Constants.MAXOPPS)
			score += 100;
		double origOppSpeaks = opposingSpeaks.get(t1);
		score += (t2.getSpeaks() - origOppSpeaks) * 20;
		return score;
	}
	private ArrayList<Team> createBracketPairings(ArrayList<Team> teamsList, HashMap<Team, Double> oppSpeaks) {
		ArrayList<Team> reverseList = new ArrayList<Team>();
		final int teamsInBracket = teamsList.size();
		//guarunteed to be even in size
		for(int i = teamsInBracket - 1; i >= 0; i--){
			reverseList.add(teamsList.get(i));
		}
		
		for(int i = 0; i < teamsInBracket; i++){
			Team top = teamsList.get(i);
			Team other = reverseList.get(i);
			oppSpeaks.put(top, other.getSpeaks());
		}
		
		//ArrayList<Team> newList = new ArrayList<Team>(teamsInBracket);
		for(int i = 0; i < teamsInBracket; i++){
			Team t1 = teamsList.get(i);
			for(int j = i + 1; j < teamsInBracket; j++){
				Team t1facing = teamsList.get(teamsInBracket - 1 - i);
				Team check = teamsList.get(j);
				Team checkFacing = teamsList.get(teamsInBracket - 1 - j);
				double origConflict = calculateConflictScore(t1, check, oppSpeaks) + 
					calculateConflictScore(t1facing, checkFacing, oppSpeaks);
				double newConflict = calculateConflictScore(t1, t1facing, oppSpeaks) +
					calculateConflictScore(check, checkFacing, oppSpeaks);
				if(origConflict < newConflict){
					teamsList.set(teamsInBracket - 1 - i, check);
					teamsList.set(j, t1facing);
				}
			}
		}
		return teamsList;
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
	
	public static void main(String args[]){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		Tourney t = new Tourney(5);
		new TMNTScheduler(t);
	}

}
