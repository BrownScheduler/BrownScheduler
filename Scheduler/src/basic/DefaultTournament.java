package basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import backbone.CompetitiveUnit;
import backbone.Grouping;
import backbone.Round;


public abstract class DefaultTournament implements backbone.Tournament{
	
	private ArrayList<Round> rounds;
	private Grouping<CompetitiveUnit> competitors;
	private ArrayList<Grouping> categories;
	private int currentRound;
	private int totalRounds;
	
	public DefaultTournament(int totalRounds){
		this.currentRound = 0;
		this.totalRounds = totalRounds;
	}
	
	@Override
	public Round getCurrentRound() {
		return rounds.get(currentRound);
	}

	public ArrayList<Grouping> getCategories() {
		ArrayList<Grouping> cats = new ArrayList<Grouping>();
		cats.addAll(categories);
		return cats;
	}
	
	public List<backbone.Round> getRounds() {
		LinkedList<backbone.Round> rs = new LinkedList<backbone.Round>();
		rs.addAll(rounds);
		return rs;
	}
}
