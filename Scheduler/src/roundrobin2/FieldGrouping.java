package roundrobin2;

public class FieldGrouping extends MyGrouping<Field> {

	Turn _t;
	
	public FieldGrouping(Turn t, String name) {
		super(name);
		_t = t;
	}

	@Override
	public Field getBlank() {
		return new Field(_t, "");
	}

}
