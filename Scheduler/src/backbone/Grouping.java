package backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface Grouping<T extends Unit> extends Serializable {
	
	public T getBlank();
	
	public String getName();

	public List<T> getMembers();
	
	public void addMember(T member);

	public boolean deleteMember(T member);
	
	public void clear();
}
