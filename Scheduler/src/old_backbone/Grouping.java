package old_backbone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class Grouping<T extends Unit> extends Grunit{
	

	//name is what the individual thingummy is called
	public String name;
	protected Collection<T> members;
	
	public Grouping(String name) {
		this.name = name;
		members = new ArrayList<T>();
	}

	public Collection<T> getMembers() {
		return members;
	}
	
	public void addMember(T member) {
		members.add(member);
	}
	
	@Override
	public Collection<Attribute> getAttributes(){
		StringAttribute name = new StringAttribute("Name", this.name);
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(name);
		return atts;
	}

	public int size() {
		return members.size();
	}
}
