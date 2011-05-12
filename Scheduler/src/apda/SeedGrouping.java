package apda;

import java.util.LinkedList;
import java.util.List;

import backbone.Grouping;

public class SeedGrouping implements Grouping<SeedUnit> {

	@Override
	public void addMember(SeedUnit member) {
	}

	@Override
	public void clear() {
	}

	@Override
	public boolean deleteMember(SeedUnit member) {
		return false;
	}

	@Override
	public SeedUnit getBlank() {
		return new SeedUnit("");
	}

	@Override
	public List<SeedUnit> getMembers() {
		LinkedList<SeedUnit> members = new LinkedList<SeedUnit>();
		members.add(new SeedUnit("Full"));
		members.add(new SeedUnit("Half"));
		members.add(new SeedUnit("Free"));
		return members;
	}

	@Override
	public String getName() {
		return "Possible Seeds";
	}

	@Override
	public SeedUnit hasDuplicate(SeedUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

}
