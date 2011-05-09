package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Round implements Serializable {

	protected ArrayList<Pairing> pairings;
	
	public Round(int i) {
		pairings = new ArrayList<Pairing>();
	}
	
	public boolean isFilled() {
		return false;
	}

	public void addPairing(Pairing pairing) {
		pairings.remove(pairing);
	}

	public boolean isPaired(CompetitiveUnit comp) {
		return false;
	}

	public void removePairing(Pairing pairing) {
		pairings.remove(pairing);
	}
	
	public ArrayList<Pairing> getPairings(){
		return pairings;
	}

}
