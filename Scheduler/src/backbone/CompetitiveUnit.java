package backbone;

import java.util.ArrayList;

public abstract class CompetitiveUnit extends Unit{

	public CompetitiveUnit(String name) {
		super(name);
	}

	public float getPotentialConflictMagnitude() {
		float magnitude = 0;
		for(Attribute attribute : getAttributes()) {
			if(attribute.isConflict()) {
				
			}
		}
		return magnitude;
	}

	public CompetitiveUnit selectOpponent(ArrayList<CompetitiveUnit> competitors) {
		// TODO Auto-generated method stub
		return null;
	}
}
