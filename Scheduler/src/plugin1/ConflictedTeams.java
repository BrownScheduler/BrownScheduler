package plugin1;

import java.util.Collection;

import backbone.Attribute;
import backbone.Grouping;

public class ConflictedTeams extends Grouping<Team> {

	public ConflictedTeams(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public ConflictedTeams(String name, Collection<Team> teams){
		super(name);
		for(Team t : teams){
			this.addMember(t);
		}		
	}


}
