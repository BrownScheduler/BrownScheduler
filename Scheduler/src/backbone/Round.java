package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import roundrobin2.Game;

public abstract class Round implements Serializable, Grouping<Pairing> {

	protected ArrayList<Pairing> pairings;
	protected String name;
	
	public Round(String name) {
		pairings = new ArrayList<Pairing>();
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	public void addPairing(Pairing pairing) {
		pairings.add(pairing);
	}

	public void removePairing(Pairing pairing) {
		pairings.remove(pairing);
	}
	
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
