package backbone;

import java.io.Serializable;
import java.util.Collection;

public class Pairing extends Grouping {
	
	private int id;

	public Pairing(CompetitiveUnit mostConflicted, CompetitiveUnit opponent, int id) {
		super("Pairing", Integer.toString(id));
	}

	@Override
	public Collection<Attribute> getAttributes() {
		return null;
	}
	
	

}
