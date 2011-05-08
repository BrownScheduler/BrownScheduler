package plugin1;

public class GovOppGrouping extends MyCategory<GovOppUnit>{

	public GovOppGrouping(String name) {
		super(name);
	}

	@Override
	public GovOppUnit getBlank() {
		// TODO Auto-generated method stub
		return new GovOppUnit(null);
	}

}
