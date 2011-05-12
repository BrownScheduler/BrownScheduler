package apda;

public class GovOppGrouping extends MyCategory<GovOppUnit>{

	public GovOppGrouping(String name) {
		super(name);
	}

	@Override
	public GovOppUnit getBlank() {
		// TODO Auto-generated method stub
		return new GovOppUnit();
	}

	@Override
	public void clear() {
		
	}

	@Override
	public GovOppUnit getDuplicate(GovOppUnit unit) {
		return null;
	}

}
