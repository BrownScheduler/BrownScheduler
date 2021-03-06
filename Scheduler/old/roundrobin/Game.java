package roundrobin;

import java.util.ArrayList;
import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.Pairing;
import backbone.Round;
import backbone.StringAttribute;
import backbone.Unit;
import backbone.UnitAttribute;

public class Game implements Pairing {

	private Tournament _t;
	private Round _r;
	private final int _roundNum;
	
	private StringAttribute _name;
	private UnitAttribute<Field> _location;
	private UnitAttribute<Team> _homeTeam;
	private UnitAttribute<Team> _awayTeam;
	private UnitAttribute<Referee> _headRef;
	private UnitAttribute<Referee> _assistantRef;
	
	public Game(Tournament t, Round r, String name, Field field){
		_r = r;
		_t = t;
		_roundNum = t.getRounds().indexOf(r);
		_name = new StringAttribute("Name", name);
		_location = new UnitAttribute<Field>("Location", field, _t.getFields());
		_homeTeam = new UnitAttribute<Team>("Home Team", null, _t.getTeams());
		_awayTeam = new UnitAttribute<Team>("Away Team", null, _t.getTeams());
		RefereeGrouping possibleRefs;
		if(_location == null) possibleRefs = new RefereeGrouping(_t, "Possibilities");
		else possibleRefs = _location.att.ref();
		_headRef = new UnitAttribute<Referee>("Head Ref", null, possibleRefs);
		_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, possibleRefs);
	}
	
	public void setHome(Team homeTeam){
		TeamGrouping canFace = new TeamGrouping(_t, "Home Can Face");
		if(_homeTeam.att != null && _awayTeam.att != null){
			if(homeTeam == _homeTeam.att) return;
			_awayTeam.att.removeFacedTeam(_homeTeam.att);
		}
		for(Team t : _t.getTeams().getMembers()){
			if(t != homeTeam && !homeTeam.hasfacedBefore(t, _roundNum)){
				canFace.addMember(t);
			}
		}
		_awayTeam.setGrouping(canFace);
		_homeTeam = new UnitAttribute<Team>("Home Team", homeTeam, new TeamGrouping(_t, "Away can Face", _homeTeam.getListOfUnits()));
		if(_awayTeam.att != null) homeTeam.addFacedTeam(_awayTeam.att, _roundNum);
	}
	
	public void setAway(Team awayTeam){
		TeamGrouping canFace = new TeamGrouping(_t, "Away Can Face");
		if(_homeTeam.att != null && _awayTeam.att != null){
			if(awayTeam == _awayTeam.att) return;
			_homeTeam.att.removeFacedTeam(_homeTeam.att);
		}
		for(Team t : _t.getTeams().getMembers()){
			if(t != awayTeam && !awayTeam.hasfacedBefore(t, _roundNum)){
				canFace.addMember(t);
			}
		}
		_homeTeam.setGrouping(canFace);
		_awayTeam = new UnitAttribute<Team>("Away Team", awayTeam, new TeamGrouping(_t, "Away can Face", _homeTeam.getListOfUnits()));
		if(_homeTeam.att != null) awayTeam.addFacedTeam(_homeTeam.att, _roundNum);
	}
	@Override
	public boolean deleteFromGrouping() {
		if(_r == null) return false;
		else return _r.deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(_name);
		atts.add(_location);
		atts.add(_homeTeam);
		atts.add(_awayTeam);
		atts.add(_headRef);
		atts.add(_assistantRef);
		return atts;
	}

	@Override
	public Grouping getMemberOf() {
		return _r;
	}

	@Override
	public String getName() {
		return _name.value;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING){
			_name = (StringAttribute)attribute;
		}
		else if(attribute.getType() == Attribute.Type.UNIT){
			UnitAttribute att = (UnitAttribute)attribute;
			String title = att.getTitle();
			if(title.equals("Location")) _location = att;
			else if(title.equals("Home Team")){
				setHome((Team)att.att);
				
			}
			else if(title.equals("Away Team")){
				setAway((Team)att.att);
			}
			else if(title.equals("Head Ref")) _headRef = att;
			else if(title.equals("Assistant Ref")) _assistantRef = att;
		}

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		this._name = new StringAttribute("Name", name);
		
	}

}
