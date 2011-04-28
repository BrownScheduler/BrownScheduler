package backbone;

import java.io.Serializable;
import java.util.Collection;

public class Round implements Serializable, Cloneable {

	Collection<Pairing> pairings;
	
	Round(int i) {
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
	
	public Round copy() {
		try {
			return (Round) clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
