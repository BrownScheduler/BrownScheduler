package plugin1;

import basic.DefaultGrouping;

public class GovOppGrouping extends DefaultGrouping<GovOppUnit>{

	public GovOppGrouping(String name) {
		super(name);
	}

	@Override
	public GovOppUnit getBlank() {
		// TODO Auto-generated method stub
		return new GovOppUnit(null);
	}

	@Override
	public void clear() {
		
	}

}
