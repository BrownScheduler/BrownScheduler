package constraint;
import java.util.ArrayList;

import backbone.*;

public class ConstraintHandler {

	public enum Player {
		A, B, C, D, E, F, G, H
	}
	
	
	public static void main(String[] args) {
		ArrayList<Player> team1 =  new ArrayList<Player>();
		team1.add(Player.A);
		team1.add(Player.B);
		ArrayList<Player> team2 =  new ArrayList<Player>();
		team2.add(Player.D);
		team2.add(Player.C);
		ArrayList<Player> team3 =  new ArrayList<Player>();
		team3.add(Player.E);
		team3.add(Player.F);
		team3.add(Player.G);
		team3.add(Player.H);
		ArrayList<ArrayList<Player>> teams =  new ArrayList<ArrayList<Player>>();
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		ArrayList<Player> players =  new ArrayList<Player>();
		players.add(Player.A);
		players.add(Player.B);
		players.add(Player.C);
		players.add(Player.D);
		players.add(Player.E);
		players.add(Player.F);
		players.add(Player.G);
		players.add(Player.H);
	}

}
