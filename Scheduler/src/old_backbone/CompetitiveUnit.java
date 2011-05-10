package old_backbone;

import java.util.ArrayList;

public abstract class CompetitiveUnit extends Unit{
	
	public CompetitiveUnit(String name) {
		super(name);
	}

	public float getPotentialConflictMagnitude() {
		float magnitude = 0;
		for(Attribute attribute : getAttributes()) {
			magnitude += attribute.getConflictMagnitude();
		}
		return magnitude;
	}
}
