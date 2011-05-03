package plugin1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import backbone.Category;
import backbone.CompetitiveUnit;


public class Tourney extends backbone.Tournament{
	
	private ArrayList<plugin1.MyRound> rounds;
	private Category<Team> competitors;
	private Category<Judge> judges;
	private int totalRounds;
	
	public Tourney(){
		rounds = new ArrayList<MyRound>();
		competitors = new Category<Team>("Competitors");
		judges = new Category<Judge>("Judges");
		totalRounds = 2;
	}

	public Collection<Team> getCompetitors() {
		return competitors.getMembers();
	}
	
	@Override
	public MyRound getCurrentRound() {
		if(rounds == null){
			rounds = new ArrayList<MyRound>();
			rounds.add(new MyRound(1));
		}
		int r = rounds.size() - 1;
		plugin1.MyRound currRound = rounds.get(r);
		if(currRound != null){
			if(!currRound.isFinished() || currRound.getRoundNum() >= totalRounds){
				return currRound;
			}
			else{
				MyRound nextRound = new MyRound(r + 1);
				rounds.add(nextRound);
				return nextRound;
			}
		}
		return null;
	}
	
	@Override
	public MyRound createNextRound(){
		int num = rounds.size();
		LinkedList<Team> teams = new LinkedList<Team>();
		teams.addAll(competitors.getMembers());
		Collections.shuffle(teams);
		LinkedList<Judge> judges = new LinkedList<Judge>();
		judges.addAll(this.judges.getMembers());
		Collections.shuffle(judges);
		MyRound round = new MyRound(num);
		
		while(!teams.isEmpty()){
			Team gov = teams.remove();
			Team opp = null;
			if(!teams.isEmpty()) opp = teams.remove();
			Judge j = null;
			if(!judges.isEmpty()) j = judges.remove();
			MyPairing pair = new MyPairing(gov, opp, j);
			round.addPairing(pair);
		}
		this.rounds.add(round);
		return round;
	}

	
	public ArrayList<backbone.Category> getCategories() {
		ArrayList<Category> cats = new ArrayList<Category>();
		cats.add(this.competitors);
		cats.add(this.judges);
		return cats;
	}
	
	public Collection<backbone.Round> getRounds() {
		LinkedList<backbone.Round> rs = new LinkedList<backbone.Round>();
		rs.addAll(rounds);
		return rs;
	}
}
