package basic;

import backbone.*;

/**
 * 
 *
 */
public class PairingTemplate extends UnitTemplate implements backbone.Pairing {

	private Round _round;

	public PairingTemplate(int num, Round round) {
		super("Pairing " + Integer.toString(num));
		_round = round;
	}

	@Override
	public boolean deleteFromGrouping() {
		return _round.deleteMember(this);
	}

	/**
	 * Not applicable. Should never be called
	 */
	@Override
	public Grouping<Unit> getMemberOf() {
		return null;
	}

	/**
	 * Not applicable. Should never be called
	 */
	@Override
	public void setMemberOf(Grouping<Unit> g) {}

	@Override
	public double getConflictScore() {
		return 1.0;
	}
	
	@Override
	public String getConflictMessage() {
		return "";
	}

}
