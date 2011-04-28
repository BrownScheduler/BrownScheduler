package plugin1;

import java.util.LinkedList;

import backbone.*;

/**
 * Class that holds each pairing,
 * which is where the actual individual competitions take place.
 * @author matt
 *
 */
public class Pairing implements CompetitiveGrouping{

	Team _gov;
	Team _opp;
	Judge _judge;
	Team _winner;
	
	public Pairing(Team gov, Team opp, Judge judge) {
		_gov = gov;
		_opp = opp;
		_judge = judge;
	}

	public LinkedList<Attribute> getElements(){
	
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new UnitAttribute("Gov", _gov));
		atts.add(new UnitAttribute("Opp", _opp));
		atts.add(new UnitAttribute("Judge", _judge));
		atts.add(new UnitAttribute("Winner", _winner));
		
		return atts;
	}
}
