package basic;

import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class ExampleUnit extends UnitTemplate {

	private Grouping<Unit> _grouping;

	public ExampleUnit(String name, Grouping grouping) {
		super(name);
		this.addAttribute(new StringAttribute("Name", name));
		_grouping = grouping;
	}

	@Override
	public void setName(String name){
		super.setName(name);
		setAttribute(new StringAttribute("Name", name));
	}
	
	@Override
	public boolean deleteFromGrouping() {
		return _grouping.deleteMember(this);
	}

	@Override
	public Grouping<Unit> getMemberOf() {
		return _grouping;
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
		_grouping = g;
	}


}
