package roundrobin2;

import plugin1.MyCategory;

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
