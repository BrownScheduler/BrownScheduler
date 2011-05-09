package plugin1;

import java.util.*;

import exception.BackupException;
import exception.CSVException;
import fileio.CSVIO;
import backbone.Grouping;
import backbone.IntAttribute;
import backbone.Round;

public class Main {
	
	
	
	public static void main(String args[]) throws CSVException, BackupException{
		
		Tourney t = new Tourney();
		
		Team t1 = new Team(t, "Team 1");
		Team t2 = new Team(t, "Team 2");
		Team t3 = new Team(t, "Team 3");
		Team t4 = new Team(t, "Team 4");
		Judge j1 = new Judge(t, "Judge 1");
		Judge j2 = new Judge(t, "Judge 2");
		
		ArrayList<Grouping> cats = t.getCategories();
		MyCategory<Team> teams = (MyCategory<Team>) cats.get(0);
		MyCategory<Judge> judges = (MyCategory<Judge>) cats.get(1);
		
		teams.addMember(t1);
		teams.addMember(t2);
		teams.addMember(t3);
		teams.addMember(t4);
		
		judges.addMember(j1);
		judges.addMember(j2);

		t1.setAttribute(new IntAttribute("Wins", 1));
		t4.setAttribute(new IntAttribute("Wins", 5));
		j1.addConflictedTeam(t3);
		
		Round r = t.createNextRound();
		
		CSVIO.loadGrouping("blah.csv", t);
		CSVIO.writeGrouping("blah2.csv", r);
		
	}

}
