package backbone;

import java.util.ArrayList;

public abstract class CompetitiveUnit extends Unit{

	public CompetitiveUnit(String name) {
		super(name);
	}

	public float getConflictMagnitude() {
		return 0;
	}

	public CompetitiveUnit selectOpponent(ArrayList<CompetitiveUnit> competitors) {
		// TODO Auto-generated method stub
		return null;
	}
}
