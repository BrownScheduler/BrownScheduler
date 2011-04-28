package backbone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Grouping<T extends Unit> extends Grunit{
	
	public String type;
	public String name;
	protected Collection<T> members;
	
	Grouping(String type, String name) {
		this.name = name;
		this.type = type;
		members = new ArrayList<T>();
	}

	public Collection<T> getMembers() {
		return members;
	}
	
	public void addMember(T member) {
		members.add(member);
	}
}
