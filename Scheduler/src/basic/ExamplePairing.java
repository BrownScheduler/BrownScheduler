package basic;

import backbone.Round;
import backbone.StringAttribute;
import backbone.Tournament;
import backbone.UnitAttribute;

public class ExamplePairing extends PairingTemplate {

	public ExamplePairing(int num, Round round, ExampleUnit exampleUnit,
			ExampleUnit exampleUnit2, Tournament t) {
		super(num, round);
		addAttribute(new UnitAttribute<ExampleUnit>("Competitor 1", exampleUnit, t.getCategories().get(0)));
		addAttribute(new UnitAttribute<ExampleUnit>("Competitor 2", exampleUnit2, t.getCategories().get(0)));
	}

}
