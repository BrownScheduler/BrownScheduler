package backbone;

import java.io.Serializable;
import java.util.List;

public interface Unit extends Serializable {

	public void setAttribute(Attribute attribute);
	
	public List<Attribute> getAttributes();
	
	public String getName();
}
