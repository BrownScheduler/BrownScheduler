package apda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import backbone.Attribute;
import backbone.DoubleAttribute;
import backbone.Grouping;
import backbone.Unit;


/**
 * Just a judge. 
 * One is necessary for a proper pairing to occur
 * 
 * @author matt
 *
 */
public class Judge implements Unit{
	
	
	private String _name;
	private DoubleAttribute _judgeScore;
	private ArrayList<Team> _conflictedTeams;
	private ArrayList<School> _conflictedSchools;
	private boolean[] _roundsAvailable;
	private Tourney _t;
	
	public Judge(Tourney t, String name){
		_name = name;
		_t = t;
		_conflictedTeams = new ArrayList<Team>();
		_conflictedSchools = new ArrayList<School>();
		_roundsAvailable = new boolean[_t._totalRounds];
		for(boolean b : _roundsAvailable)
			b = true;
		
	}
	
	//returns -1 if j1 is higher ranked than j2
	public static class JudgeComparator implements Serializable, Comparator {
		
		@Override
		public int compare(Object arg0, Object arg1) {
			Judge j1 = (Judge)arg0;
			Judge j2 = (Judge)arg1;
			int toreturn = 1;
			if(j1.judgeScore() > j2.judgeScore()) return -1;
			else if(j1.judgeScore() == j2.judgeScore()) return 0;
			return toreturn;
		}
		
	}
	
	public double judgeScore(){
		return this._judgeScore.getAttribute();
	}
	public boolean canJudge(Team t){
		return false;
	}
	
	public boolean isAvailable(int i){
		if(i > _roundsAvailable.length)
			return false;
		return _roundsAvailable[i];
	}

	@Override
	public boolean deleteFromGrouping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Attribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	
}
