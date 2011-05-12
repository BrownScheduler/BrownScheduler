package basic;

import backbone.Round;
import backbone.StringAttribute;
import backbone.Tournament;
import backbone.UnitAttribute;

public class ExamplePairing extends PairingTemplate {

	public ExamplePairing(int num, Round round, ExampleUnit exampleUnit,
			ExampleUnit exampleUnit2, Tournament t) {
		super(num, round);
		addAttribute(new UnitAttribute(exampleUnit, t.getCategories().get(0)));

	}

}
