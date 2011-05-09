package roundrobin;

import plugin1.MyCategory;

public class FieldGrouping extends MyGrouping<Field> {

	Tournament _t;
	
	public FieldGrouping(Tournament t, String name) {
		super(name);
		_t = t;
	}

	@Override
	public Field getBlank() {
		return new Field(_t, "");
	}

}
