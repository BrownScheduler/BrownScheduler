package plugin1;

import java.util.*;

import backbone.Category;
import backbone.Grouping;

public class Main {
	
	
	
	public static void main(String args[]){
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
		
		MyRound r = t.createNextRound();
		System.out.println(t.getCurrentRound().toString());
	}

}
