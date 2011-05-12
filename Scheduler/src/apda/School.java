package apda;

import java.util.List;

import backbone.Attribute;
import backbone.Grouping;
import backbone.Unit;

public class School implements Unit {

	
	public boolean hasFreeSeed(){
		return false;
	}
	
	public boolean removeTeam(Team t){
		return false;
	}
	public void addTeam(Team t){
		//TODO
		return;
	}
	@Override
	public boolean deleteFromGrouping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Attribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

}
