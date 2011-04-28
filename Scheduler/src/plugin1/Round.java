package plugin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import backbone.Grouping;
import backbone.Pairing;

/**
 * This class is a Grouping of pairings.
 * 
 * @author matt
 *
 */
public class Round extends backbone.Round{
	
	private int _roundNum;
	
	public Round(int i) {
		super(i);
		_roundNum = i;
	}
	

	public void addPairing(Pairing pairing) {
		pairings.add(pairing);
	}
	


}
