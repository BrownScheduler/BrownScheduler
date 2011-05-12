package apda;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.DoubleAttribute;
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
	private DoubleAttribute _judgeScore;
	private TeamGrouping _conflictedTeams;
	private SchoolGrouping _conflictedSchools;
	private boolean[] _roundsAvailable;
	private Tourney _t;
	
	public Judge(Tourney t, String name){
		_name = name;
		_t = t;
		_conflictedTeams = new TeamGrouping(_t, "Conflicted Teams");
		_conflictedSchools = new SchoolGrouping(_t, "Conflicted Schools");
		_roundsAvailable = new boolean[_t._totalRounds];
		for(int i = 0; i < _t._totalRounds; i++){
			_roundsAvailable[i] = true;
		}
			
		
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
	
	public void addCantJudge(Team t){
		this._conflictedTeams.addMember(t);
	}
	public double judgeScore(){
		return this._judgeScore.getAttribute();
	}
	public boolean canJudge(Team t){
		return (!_conflictedTeams.getMembers().contains(t) && 
				!_conflictedSchools.getMembers().contains(t.school));
	}
	
	public boolean isAvailable(int i){
		if(i > _roundsAvailable.length)
			return false;
		return _roundsAvailable[i];
	}

	@Override
	public boolean deleteFromGrouping() {
		return _t._judges.deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new StringAttribute("Name", _name));		
		atts.add(new GroupingAttribute<Team>("Conflicted Teams", this._conflictedTeams));
		atts.add(new GroupingAttribute<School>("Conflicted Schools", this._conflictedSchools));
//		for(int i = 0; i < _roundsAvailable.length; i++){
//			atts.add(new BooleanAttribute("R" + Integer.toString(i + 1), _roundsAvailable[i]));
//		}
		
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		return _t._judges;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getTitle().equals("Name")){
			_name = ((StringAttribute)attribute).value;
		}else if(attribute.getTitle().equals("Conflicted Teams")){
			_conflictedTeams = (TeamGrouping) ((GroupingAttribute<Team>)attribute).getGrouping();
		}else if(attribute.getTitle().equals("Conflicted Schools")){
			_conflictedSchools = (SchoolGrouping) ((GroupingAttribute<School>)attribute).getGrouping();
		}else if(attribute.getTitle().startsWith("R")){
			String tit = ((BooleanAttribute)attribute).getTitle();
			boolean b = ((BooleanAttribute)attribute).getAttribute();
			tit = tit.substring(1);
			if(tit.equals("")) return;
			int titInt = Integer.parseInt(tit);
			if(titInt <= _roundsAvailable.length && titInt > 0) _roundsAvailable[titInt - 1] = b;
		}
		
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {		
	}

	@Override
	public void setName(String name) {
		_name = name;
	}
	
}
