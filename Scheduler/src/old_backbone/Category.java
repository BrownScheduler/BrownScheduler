package old_backbone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backbone.Attribute;

public interface Category<T extends Unit> extends Serializable {

	public List<T> getMembers();
	
	public void addMember(T member);

	public List<Attribute> getAttributes();

}
