package backbone;

import java.io.Serializable;
import java.util.Collection;

public class Round implements Serializable {

	Collection<Pairing> pairings;
	
	Round(int i) {
	}

	public boolean isFilled() {
		return false;
	}

	public void addPairing(Pairing pairing) {
		
	}

}
