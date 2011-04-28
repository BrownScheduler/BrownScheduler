package backbone;

public class Round extends Grouping<Pairing> {

	Round(int i) {
		super("Round", Integer.toString(i));
	}

	public boolean isFilled() {
		return false;
	}

	public void addPairing(Pairing pairing) {
		
	}

}
