package plugin1;

import java.util.LinkedList;
import java.util.List;

import backbone.*;

/**
 * Just a judge. 
 * One is necessary for a proper pairing to occur
 * 
 * @author matt
 *
 */
public class Judge extends Unit{
	
	
	LinkedList<Team> conflictedTeams;
	
	public Judge(String name) {
		super(name);
	}

	@Override
	public List<Attribute> getAttributes() {
	
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		
		return null;
	}
	
}
