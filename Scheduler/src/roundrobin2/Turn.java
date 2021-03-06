package roundrobin2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import middleend.TMNTScheduler;

import backbone.Grouping;
import backbone.Round;
import backbone.Tournament;

public class Turn implements backbone.Tournament {

	private ArrayList<Round> _rounds;
	private RefereeGrouping _allRefs;
	private TeamGrouping _allTeams;
	private PlayerGrouping _allPlayers;
	private FieldGrouping _allFields;
	
	public Turn(){
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

	public Round createNextRound(boolean b) {
		ConstraintHandler c = new ConstraintHandler(this, getNextRoundInt());
		Round r = c.createRound();
		_rounds.add(r);
		return r;
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
	
	public static void main(String args[]){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		Turn t = new Turn();
		new TMNTScheduler(t);
	}

	@Override
	public Tournament getNew() {
		return new Turn();
	}

}
