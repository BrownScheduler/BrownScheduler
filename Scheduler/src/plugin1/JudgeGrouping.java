package plugin1;

import java.util.List;

public class JudgeGrouping extends MyCategory<Judge> {

	private Tourney _t;
	public JudgeGrouping(Tourney t, String name) {
		super(name);
		_t = t;
	}
	public JudgeGrouping(Tourney t, String name, List<Judge> judges){
		super(name, judges);
		_t = t;
	}

	@Override
	public Judge getBlank() {
		return new Judge(_t, "");
	}

}
