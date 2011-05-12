package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exception.InvalidRoundException;

import backbone.Grouping;
import backbone.Round;
import backbone.Tournament;

public abstract class TournamentTemplate implements backbone.Tournament{
	
	private ArrayList<Round> rounds;
	private ArrayList<Grouping> categories;
	private int currentRound;
	private int totalRounds;
	
	public TournamentTemplate(int totalRounds){
		this.currentRound = 0;
		this.totalRounds = totalRounds;
	}

	@Override
	public ArrayList<Grouping> getCategories() {
		ArrayList<Grouping> cats = new ArrayList<Grouping>();
		cats.addAll(categories);
		return cats;
	}
	
	@Override
	public List<backbone.Round> getRounds() {
		LinkedList<backbone.Round> rs = new LinkedList<backbone.Round>();
		rs.addAll(rounds);
		return rs;
	}
	
	protected void addGrouping(Grouping g) {
		categories.add(g);
	}
}
