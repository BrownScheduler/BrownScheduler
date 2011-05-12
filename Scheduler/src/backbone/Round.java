package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Round implements Serializable, Grouping<Pairing> {

	/**
	 * The pairings the Round contains
	 */
	protected ArrayList<Pairing> pairings;
	
	/**
	 * The name of the round
	 */
	protected String name;
	
	/**
	 * A constructor for a new Round
	 * @param name the name of the round
	 */
	public Round(String name) {
		pairings = new ArrayList<Pairing>();
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Adds a pairing to the Round
	 * @param pairing the pairing to be added
	 */
	public void addPairing(Pairing pairing) {
		pairings.add(pairing);
	}

	/**
	 * Removes a pairing from a round
	 * @param pairing the pairing to be removed
	 */
	public void removePairing(Pairing pairing) {
		pairings.remove(pairing);
	}
	
	/**
	 * Gets the pairings of the round
	 * @return the pairings
	 */
	public ArrayList<Pairing> getPairings(){
		return pairings;
	}

	@Override
	public void addMember(Pairing member) {
		pairings.add(member);
		
	}

	@Override
	public boolean deleteMember(Pairing member) {
		return pairings.remove(member);
	}

	@Override
	public List<Pairing> getMembers() {
		return pairings;
	}

	@Override
	public void clear() {
		pairings.clear();
	}

	/**
	 * Not applicable should never be called/
	 */
	@Override
	public Pairing getDuplicate(Pairing unit) {
		return null;
	}

}
