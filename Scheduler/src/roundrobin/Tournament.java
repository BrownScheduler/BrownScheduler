package roundrobin;

import java.util.ArrayList;
import java.util.List;

import backbone.Grouping;
import backbone.Round;

public class Tournament implements backbone.Tournament {

	private ArrayList<Round> _rounds;
	private RefereeGrouping _allRefs;
	private TeamGrouping _allTeams;
	private PlayerGrouping _allPlayers;
	private FieldGrouping _allFields;
	
	public Tournament(){
		_rounds = new ArrayList<Round>();
		_allRefs = new RefereeGrouping(this, "Referees");
		_allTeams = new TeamGrouping(this, "Teams");
		_allPlayers = new PlayerGrouping(this, "Players");
		_allFields = new FieldGrouping(this, "Fields");
	}
	
	public int getNextRoundInt(){
		return _rounds.size();
	}
	@Override
	public Round createNextRound() {
		ConstraintHandler c = new ConstraintHandler(this);
		return c.creatRound();
	}

	@Override
	public List<Grouping> getCategories() {
		ArrayList<Grouping> groups = new ArrayList<Grouping>();
		groups.add(_allRefs);
		groups.add(_allTeams);
		groups.add(_allPlayers);
		groups.add(_allFields);
		return groups;
	}


	@Override
	public List<Round> getRounds() {
		return _rounds;
	}
	
	public RefereeGrouping getRefs(){
		return _allRefs;
	}
	
	public TeamGrouping getTeams(){
		return _allTeams;
	}
	
	public PlayerGrouping getPlayers(){
		return _allPlayers;
	}
	
	public FieldGrouping getFields(){
		return _allFields;
	}

}