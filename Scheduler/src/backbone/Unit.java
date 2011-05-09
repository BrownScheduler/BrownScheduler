package backbone;

import java.io.Serializable;
import java.util.List;


public interface Unit extends Serializable {
	
	public Grouping<Unit> getMemberOf();
	
	public void setMemberOf(Grouping<Unit> g);

	public void setAttribute(Attribute attribute);
	
	public List<Attribute> getAttributes();
	
	public String getName();
	
	public void setName(String name);
	
	public boolean deleteFromGrouping();
}
