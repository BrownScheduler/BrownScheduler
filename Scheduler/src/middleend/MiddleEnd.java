package middleend;

import gui.*;
import backbone.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiddleEnd {
	
	public Tournament getTournament() {
		return null;
	}
	
	public Unit getBlankUnit(Grouping<Unit> grouping) {
		return null;
	}
	
	public List<Grouping> getAllGroupings() {
		return new ArrayList<Grouping>();
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
