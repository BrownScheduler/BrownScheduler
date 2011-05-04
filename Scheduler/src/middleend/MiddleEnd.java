package middleend;

import gui.*;
import backbone.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiddleEnd {
	
	public List<Attribute> getAttrsOfUnitsInGroupingAttr(GroupingAttribute<Unit> g) {
		return new ArrayList<Attribute>();
	}
	
	public Tournament getTournament() {
		return null;
	}
	
	public boolean openPlugin(File file) {
		return false;
	}
	
	public boolean openTournament(File file) {
		return false;
	}
	
	public boolean saveFile(File file) {
		return false;
	}
}
