package roundrobin2;

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

	private Turn _t;
	private Round _r;
	private final int _roundNum;
	
	private StringAttribute _name;
	private UnitAttribute<Field> _location;
	private UnitAttribute<Team> _homeTeam;
	private UnitAttribute<Team> _awayTeam;
	private UnitAttribute<Referee> _headRef;
	private UnitAttribute<Referee> _assistantRef;
	private Team _winner;
	private Team _loser;
	
	public Game(Turn t, Round r, String name){
		_r = r;
		_t = t;
		_roundNum = t.getRounds().indexOf(r);
		_name = new StringAttribute("Name", name);
		_location = new UnitAttribute<Field>("Location", null, _t.getFields());
		_homeTeam = new UnitAttribute<Team>("Home Team", null, _t.getTeams());
		_awayTeam = new UnitAttribute<Team>("Away Team", null, _t.getTeams());
		_headRef = new UnitAttribute<Referee>("Head Ref", null, _t.getRefs());
		_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, _t.getRefs());
	}
	
	public TeamGrouping getPossibleOpposition(Team t){
		if(t == null) return _t.getTeams();
		TeamGrouping possibles = new TeamGrouping(_t, "Possible Teams");
		for(Team other : _t.getTeams().getMembers()){
			if(!other.hasfacedBefore(t, _roundNum))
				possibles.addMember(other);
		}
		return possibles;
	}
	public void setHome(Team homeTeam){
		
		if(homeTeam == null){
			if(_homeTeam.getAttribute() != null && _awayTeam.getAttribute() != null){
				_awayTeam.getAttribute().removeFacedTeam(_homeTeam.getAttribute());
				_awayTeam = new UnitAttribute<Team>("Away Team", _awayTeam.getAttribute(), getPossibleOpposition(homeTeam));
			}
			_homeTeam = new UnitAttribute<Team>("Home Team", homeTeam, getPossibleOpposition(_awayTeam.getAttribute()));
		}else{
			if(_homeTeam.getAttribute() != homeTeam){
				if(_awayTeam.getAttribute() != null){
					_awayTeam.getAttribute().removeFacedTeam(_homeTeam.getAttribute());
					if(_homeTeam.getAttribute() != null)
						_homeTeam.getAttribute().removeFacedTeam(_awayTeam.getAttribute());
					_awayTeam = new UnitAttribute<Team>("Away Team", _awayTeam.getAttribute(), getPossibleOpposition(homeTeam));
				}
				_homeTeam = new UnitAttribute<Team>("Home Team", homeTeam, getPossibleOpposition(_awayTeam.getAttribute()));
			}
		}
	}
	
	public void setAway(Team awayTeam){
		if(awayTeam == null){
			if(_awayTeam.getAttribute() != null && _homeTeam.getAttribute() != null){
				_homeTeam.getAttribute().removeFacedTeam(_awayTeam.getAttribute());
				_homeTeam = new UnitAttribute<Team>("Home Team", _homeTeam.getAttribute(), getPossibleOpposition(awayTeam));
			}
			_awayTeam = new UnitAttribute<Team>("Away Team", awayTeam, getPossibleOpposition(_homeTeam.getAttribute()));
		}else{
			if(_awayTeam.getAttribute() != awayTeam){
				if(_homeTeam.getAttribute() != null){
					_homeTeam.getAttribute().removeFacedTeam(_awayTeam.getAttribute());
					if(_awayTeam.getAttribute() != null)
						_awayTeam.getAttribute().removeFacedTeam(_homeTeam.getAttribute());
					_homeTeam = new UnitAttribute<Team>("Home Team", _homeTeam.getAttribute(), getPossibleOpposition(awayTeam));
				}
				_awayTeam = new UnitAttribute<Team>("Away Team", awayTeam, getPossibleOpposition(_homeTeam.getAttribute()));
			}
		}
	}
	
	public void setField(Field f){
		UnitAttribute<Field> attr = new UnitAttribute<Field>("Location", f, _t.getFields());
		if(attr == null && _location.getAttribute() != null){
			_headRef = new UnitAttribute<Referee>("Head Ref", null, _t.getRefs());
			_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, _t.getRefs());
		}else{
			if(_headRef.getAttribute() == null || _headRef.getAttribute().getField() != attr.getAttribute())
				_headRef = new UnitAttribute<Referee>("Head Ref", null, getPossibleRefs(attr.getAttribute()));
			if(_assistantRef.getAttribute() == null || _assistantRef.getAttribute().getField() != attr.getAttribute())
				_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, getPossibleRefs(attr.getAttribute()));
		}
		_location = attr;
	}
	
	
	
	public void setHeadReferee(Referee ref){
		if(_location.getAttribute() == null && ref != null){
			_location = new UnitAttribute<Field>("Location", ref.getField(), _t.getFields());
		}
		if(_assistantRef.getAttribute() == null){
			_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, getPossibleRefs(_location.getAttribute()));
		}
		_headRef = new UnitAttribute<Referee>("Head Ref", ref, getPossibleRefs(_location.getAttribute()));
	}
	
	public void setAssistantReferee(Referee ref){
		if(_location.getAttribute() == null){
			_location = new UnitAttribute<Field>("Location", ref.getField(), _t.getFields());
		}
		if(_headRef.getAttribute() == null){
			_headRef = new UnitAttribute<Referee>("Head Ref", null, getPossibleRefs(_location.getAttribute()));
		}
		_assistantRef = new UnitAttribute<Referee>("Assistant Ref", ref, getPossibleRefs(_location.getAttribute()));
	}
	@Override
	public boolean deleteFromGrouping() {
		if(_r == null) return false;
		if(_winner != null) _winner.setGamesWon(_winner.getGamesWon() - 1);
		if(_loser != null) _loser.setGamesLost(_loser.getGamesLost() - 1);
		if(_homeTeam.getAttribute() != null) _homeTeam.getAttribute().removeFacedTeam(_awayTeam.getAttribute());
		if(_awayTeam.getAttribute() != null) _awayTeam.getAttribute().removeFacedTeam(_homeTeam.getAttribute());
		return _r.deleteMember(this);
	}

	@Override
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		//atts.add(_name);
		atts.add(_location);
		atts.add(_homeTeam);
		atts.add(_awayTeam);
		atts.add(_headRef);
		atts.add(_assistantRef);
		boolean isHome = _homeTeam.getAttribute() == _winner && _winner != null;
		boolean isAway = _awayTeam.getAttribute() == _winner && _winner != null;
		GovOppUnit toAdd = null;
		GovOppGrouping possibles = new GovOppGrouping("Possible");
		GovOppUnit home = new GovOppUnit("Home", true);
		GovOppUnit away = new GovOppUnit("Away", false);
		if(_homeTeam.getAttribute() != null) home.setName(_homeTeam.getAttribute().getName());
		if(_awayTeam.getAttribute() != null) away.setName(_awayTeam.getAttribute().getName());
		if(isHome) toAdd = home;
		else if(isAway) toAdd = away;
		possibles.addMember(home);
		possibles.addMember(away);
		atts.add(new UnitAttribute<GovOppUnit>("Winner", toAdd, possibles));
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

	public RefereeGrouping getPossibleRefs(Field f){
		RefereeGrouping g = new RefereeGrouping(_t, "Possibilities");
		if(f == null){
			for(Referee r : _t.getRefs().getMembers())
				g.addMember(r);
			return g;
		}
		for(Referee r : _t.getRefs().getMembers()){
			if(r.getField() == f || r.getField() == null) g.addMember(r);
		}
		return g;
	}
	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getType() == Attribute.Type.STRING){
			_name = (StringAttribute)attribute;
		}
		else if(attribute.getType() == Attribute.Type.UNIT){
			UnitAttribute att = (UnitAttribute)attribute;
			String title = att.getTitle();
			if(title.equals("Location")){
				UnitAttribute<Field> attr = att;
				if(attr == null && _location.getAttribute() != null){
					_headRef = new UnitAttribute<Referee>("Head Ref", null, _t.getRefs());
					_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, _t.getRefs());
				}else{
					if(_headRef.getAttribute() == null || _headRef.getAttribute().getField() != attr.getAttribute())
						_headRef = new UnitAttribute<Referee>("Head Ref", null, getPossibleRefs(attr.getAttribute()));
					if(_assistantRef.getAttribute() == null || _assistantRef.getAttribute().getField() != attr.getAttribute())
						_assistantRef = new UnitAttribute<Referee>("Assistant Ref", null, getPossibleRefs(attr.getAttribute()));
				}
				_location = attr;
			}
			else if(title.equals("Home Team")){
				setHome((Team)att.getAttribute());
				
			}
			else if(title.equals("Away Team")){
				setAway((Team)att.getAttribute());
			}
			else if(title.equals("Head Ref")){
				Referee ref = (Referee)att.getAttribute();
				setHeadReferee(ref);
			}
			else if(title.equals("Assistant Ref")){
				Referee ref = (Referee)att.getAttribute();
				setAssistantReferee(ref);
			}
			else{
				GovOppUnit newWinner = (GovOppUnit)att.getAttribute();
				if(newWinner == null){
					if(_winner != null)
						_winner.setGamesWon(_winner.getGamesWon() - 1);
					if(_loser != null)
						_loser.setGamesLost(_loser.getGamesLost() - 1);
					_winner = null;
					_loser = null;
				}
				else if(newWinner.isGov){
					if(_winner != _homeTeam.getAttribute()){
						if(_winner != null) _winner.setGamesWon(_winner.getGamesWon() - 1);
						_winner = _homeTeam.getAttribute();
						_winner.setGamesWon(_winner.getGamesWon() + 1);
					}
					if(_loser != _awayTeam.getAttribute() ){
						if(_loser != null) _loser.setGamesLost(_loser.getGamesLost() - 1);
						_loser = _awayTeam.getAttribute();
						if(_winner != null) _loser.setGamesLost(_loser.getGamesLost() + 1);
					}
				}
				else if(newWinner.isOpp){
					if(_winner != _awayTeam.getAttribute()){
						if(_winner != null) _winner.setGamesWon(_winner.getGamesWon() - 1);
						_winner = _awayTeam.getAttribute();
						_winner.setGamesWon(_winner.getGamesWon() + 1);
					}
					if(_loser != _homeTeam.getAttribute()){
						if(_loser != null) _loser.setGamesLost(_loser.getGamesLost() - 1);
						_loser = _homeTeam.getAttribute();
						if(_winner != null) _loser.setGamesLost(_loser.getGamesLost() + 1);
					}
				}
			}
		}

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		this._name = new StringAttribute("Name", name);
		
	}
	
	public Team getHomeTeam(){
		return this._homeTeam.getAttribute();
	}
	
	public Team getAwayTeam(){
		return this._awayTeam.getAttribute();
	}
	
	@Override
	public double getConflictScore() {
		return 0.0;
	}
	
	public String getConflictMessage(){
		return "";
	}

}
