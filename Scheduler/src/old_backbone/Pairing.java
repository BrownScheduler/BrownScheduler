package old_backbone;    

import java.util.ArrayList;

public class Pairing extends Grouping{

	//private int id;

	public Pairing(CompetitiveUnit mostConflicted, ArrayList<CompetitiveUnit> competitors) {
		super("Pairing");
	}


	public Pairing(CompetitiveUnit competitor, CompetitiveUnit comp) {
		super("Pairing");
	}


	public boolean satisfactory() {
		return getConflictValue() < SchedulerConstants.MAX_CONFLICT;
	}


	private double getConflictValue() {
		return 0;
	}


}
