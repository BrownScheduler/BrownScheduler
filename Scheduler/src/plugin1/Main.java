package plugin1;

import java.util.*;

import exception.BackupException;
import exception.CSVException;
import fileio.CSVIO;
import fileio.SerialIO;

import backbone.Attribute;
import backbone.Category;
import backbone.Grouping;
import backbone.Pairing;
import backbone.UnitAttribute;

public class Main {
	
	
	
	public static void main(String args[]) throws CSVException, BackupException{
		
		Tourney t = new Tourney();
		
		Team t1 = new Team("Team 1");
		Team t2 = new Team("Team 2");
		Team t3 = new Team("Team 3");
		Team t4 = new Team("Team 4");
		Judge j1 = new Judge("Judge 1");
		Judge j2 = new Judge("Judge 2");
		
		ArrayList<Grouping> cats = t.getCategories();
		MyCategory<Team> teams = (MyCategory<Team>) cats.get(0);
		MyCategory<Judge> judges = (MyCategory<Judge>) cats.get(1);
		
		teams.addMember(t1);
		teams.addMember(t2);
		teams.addMember(t3);
		teams.addMember(t4);
		
		judges.addMember(j1);
		judges.addMember(j2);
		

		
		SerialIO.writeTournament("blah", t);
		t = t.getClass().cast(SerialIO.readTournament("blah"));
		CSVIO.writeGrouping("blah.csv", t.getCategories().get(0));
		CSVIO.writeGrouping("blah2.csv", t.getCategories().get(1));
		
		MyRound r = t.createNextRound();
		//System.out.println(t.getCurrentRound().toString());
		
<<<<<<< HEAD
		
		
		
		MyRound r1 = t.getCurrentRound();
		ArrayList<Pairing> r1Pairs = r1.getPairings();
		Pairing p11 = r1Pairs.get(0);
		System.out.print("Is the current round finished? ");
		System.out.println(r1.isFinished());
		p11.setAttribute(new UnitAttribute<Team>("Winner", 
				(Team)((UnitAttribute<Team>)p11.getAttributes().get(0)).att));
		Pairing p12 = r1Pairs.get(1);
		p12.setAttribute(new UnitAttribute<Team>("Winner", 
				(Team)((UnitAttribute<Team>)p12.getAttributes().get(1)).att));
		
		System.out.print("Is round 1 finished? ");
		System.out.println(r.isFinished());
		System.out.print("Is the current round finished? ");
		System.out.println(r1.isFinished());
		
		System.out.println(r1);
		MyRound r2 = t.createNextRound();
		System.out.println(t.getCurrentRound().toString());
	
=======
		//MyRound r1 = t.getCurrentRound();
		//ArrayList<Pairing> r1Pairs = r1.getPairings();
		//Pairing p11 = r1Pairs.get(0);
		System.out.print("Is the current round finished? ");
		//System.out.println(r1.isFinished());
		//p11.setAttribute(new UnitAttribute<Team>("Winner", 
				//(Team)((UnitAttribute<Team>)p11.getAttributes().get(0)).att));
		//Pairing p12 = r1Pairs.get(1);
		//p12.setAttribute(new UnitAttribute<Team>("Winner", 
				//(Team)((UnitAttribute<Team>)p12.getAttributes().get(1)).att));
		
		//System.out.print("Is round 1 finished? ");
		//System.out.println(r.isFinished());
		//System.out.print("Is the current round finished? ");
		//System.out.println(r1.isFinished());
		
		//System.out.println(r1);
		//MyRound r2 = t.createNextRound();
		//System.out.println(t.getCurrentRound().toString());
		
>>>>>>> b42f2d1e8360de210a2a83bea4c2b77666cb0c84
	}

}
