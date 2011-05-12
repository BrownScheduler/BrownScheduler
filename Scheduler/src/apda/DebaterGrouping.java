package apda;

import java.util.List;

import backbone.Grouping;

public class DebaterGrouping extends MyCategory<Debater> {

	private Tourney _t;
	
	public DebaterGrouping(Tourney tourney, String name) {
		super(name);
		_t = tourney;
		_name = name;
	}

	@Override
	public Debater getBlank() {
		return new Debater(_t, "");
	}

}
