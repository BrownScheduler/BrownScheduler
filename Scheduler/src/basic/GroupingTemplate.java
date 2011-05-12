package basic;

import java.util.ArrayList;
import java.util.List;

import backbone.Grouping;
import backbone.Unit;

public abstract class GroupingTemplate<T extends Unit> implements Grouping<T> {

	protected String _name;
	protected List<T> _members;
	
	public GroupingTemplate(String name){
		this._name = name;
		_members = new ArrayList<T>();
	}
	
	@Override
	public void addMember(T member) {
		_members.add(member);
		member.setMemberOf((Grouping<Unit>) this);
		
	}
	
	public void addMemberDown(T member){
		_members.add(member);
	}

	@Override
	public List<T> getMembers() {
		return _members;
	}

	@Override
	public String getName() {
		
		return _name;
	}

	@Override
	public boolean deleteMember(T member) {
		return _members.remove(member);
		
	}
	
	@Override
	public void clear(){
		this._members.clear();
	}
	
	@Override
	public T getDuplicate(T unit){
		for(T u : _members)
			if(u != unit && u.getName().equals(unit.getName()))
				return u;
		return null;
	}

}
