package apda;
import java.util.LinkedList;
import java.util.List;

import backbone.*;


public class Team implements Unit {
	
	private String _name;
	public int wins;
	public int losses;
	public School memberOf;
	public boolean stillInTournament;
	public int numGovs;
	public int numOpps;
	public Seed s;
	public Debater d1;
	public Debater d2;
	private Tourney _t;
	
	public enum Seed{
		FULL, HALF, FREE
	}
	public Team(Tourney t, String name){
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
