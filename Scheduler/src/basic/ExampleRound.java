package basic;

import backbone.Pairing;
import backbone.Round;
import backbone.Tournament;

public class ExampleRound extends Round {

	private Tournament _t;

	public ExampleRound(int i, Tournament t) {
		super("Round " + Integer.toString(i));
		_t = t;
	}

	@Override
	public Pairing getBlank() {
		return new ExamplePairing(0, this, null, null, _t);
	}
	
	

}
