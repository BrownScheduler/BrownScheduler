package middleend;

import gui.*;
import backbone.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiddleEnd {
	
	Tournament _tmnt;
	App _app;
	
	public MiddleEnd(Tournament t) {
		_tmnt = t;
		
		_app = new App(this);
	}
	
	public void repaintAll() {
		_app.repaintAll();
	}
	
	public Tournament getTournament() {
		return _tmnt;
	}
	
	public void setTournament(Tournament t) {
		_tmnt = t;
	}
	
	public boolean openTournament(File file) {
		return false;
	}
	
	public boolean saveTournament(File file) {
		return false;
	}
	
	public boolean importCategory(File file) {
		return false;
	}
	
	public boolean exportCategory(File file) {
		return false;
	}
}
