package roundrobin;

import java.util.ArrayList;
import java.util.List;

import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public abstract class MyGrouping<T extends Unit> implements Grouping<T> {

	
	protected StringAttribute _name;
	protected List<T> _members;
	
	public MyGrouping(String name){
		_name = new StringAttribute("Name", name);
		_members = new ArrayList<T>();
	}
	
	public MyGrouping(StringAttribute name){
		_name = name;
		_members = new ArrayList<T>();
	}
	@Override
	public void addMember(T member) {
		_members.add(member);		
	}

	@Override
	public void clear() {
		_members.clear();
	}

	@Override
	public boolean deleteMember(T member) {
		return _members.remove(member);
	}

	@Override
	public List<T> getMembers() {
		return _members;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return _name.getAttribute();
	}
	
	public void setListOfUnits(List<T> units){
		this._members = units;
	}

}
