package basic;

public class ExampleGrouping extends GroupingTemplate<ExampleUnit> {

	public ExampleGrouping(String name) {
		super(name);
	}

	@Override
	public ExampleUnit getBlank() {
		return new ExampleUnit("", this);
	}

}
