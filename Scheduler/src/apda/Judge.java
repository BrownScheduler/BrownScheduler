package apda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.GroupingAttribute;
import backbone.StringAttribute;
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
