package plugin1;

import java.util.ArrayList;
import java.util.List;

import backbone.Grouping;
import backbone.Unit;

public abstract class MyCategory<T extends Unit> implements Grouping<T> {

	private String _name;
	private List<T> _members;
	
	public MyCategory(String name){
		this._name = name;
		_members = new ArrayList<T>();
	}
	
	public MyCategory(String name, List<T> members){
		this._name = name;
		this._members = members;
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

}
