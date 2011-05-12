package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import backbone.Grouping;
import backbone.Round;

public abstract class TournamentTemplate implements backbone.Tournament{
	
	private ArrayList<Round> _rounds;
	private ArrayList<Grouping> _categories;
	private int _currentRound;
	private int _totalRounds;
	
	public TournamentTemplate(int totalRounds){
		_currentRound = 0;
		_totalRounds = totalRounds;
		_categories = new ArrayList<Grouping>();
		_rounds = new ArrayList<Round>();
	}

	@Override
	public ArrayList<Grouping> getCategories() {
		ArrayList<Grouping> cats = new ArrayList<Grouping>();
		cats.addAll(_categories);
		return cats;
	}
	
	@Override
	public List<backbone.Round> getRounds() {
		LinkedList<backbone.Round> rs = new LinkedList<backbone.Round>();
		rs.addAll(_rounds);
		return rs;
	}
	
	protected void addGrouping(Grouping g) {
		_categories.add(g);
	}
	
	protected void addRound(Round r) {
		_rounds.add(r);
	}
}
