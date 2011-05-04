package backbone;

import java.util.List;

public interface Unit {
	
	public Unit getBlank();

	public void setAttribute(Attribute attribute);
	
	public List<Attribute> getAttributes();
	
	public String getName();
}
