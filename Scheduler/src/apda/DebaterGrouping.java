package apda;

import java.util.List;

import backbone.Grouping;

public class DebaterGrouping extends MyCategory<Debater> {

	private Tourney _t;
	private School _s;
	
	public DebaterGrouping(Tourney tourney, String name) {
		super(name);
		_t = tourney;
		_name = name;
		//_s = s;
	}

	@Override
	public Debater getBlank() {
		return new Debater(_t, "");
	}

	@Override
	public Debater getDuplicate(Debater unit) {
		for(Debater d : this._members){
			if(d.getName().equals(unit.getName()) && unit != d)
				return d;
		}
		return null;
	}

}
