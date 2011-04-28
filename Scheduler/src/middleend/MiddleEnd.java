package middleend;

import gui.*;

import java.io.File;
import java.util.Collection;

public class MiddleEnd {

	public Collection<GUIGrouping> getAllGroupings() {
		return null;
	}
	
	public Collection<GUICompetitiveUnit> getAllCompetitiveUnits() {
		return null;
	}
	
	public Collection<GUIAttribute> getAllAttributes() {
		return null;
	}
	
	public Collection<GUIGroupingAttribute> getAllGroupingAttributes() {
		return null;
	}
	
	public Collection<GUICompetitiveUnit> getCompunitsInGrouping(GUIGrouping g) {
		return null;
	}
	
	public int getNumAttributeCategories() {
		return 0;
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
