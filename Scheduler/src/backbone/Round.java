package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Round implements Serializable {

	protected ArrayList<Pairing> pairings;
	protected String name;
	
	public Round(String name) {
		pairings = new ArrayList<Pairing>();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isFilled() {
		return false;
	}

	public void addPairing(Pairing pairing) {
		pairings.remove(pairing);
	}

	public boolean isPaired(CompetitiveUnit comp) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removePairing(Pairing pairing) {
		pairings.remove(pairing);
	}
	
	public ArrayList<Pairing> getPairings(){
		return pairings;
	}

}
