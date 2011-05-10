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
<<<<<<< HEAD
		try {
			_scheduler.addTMNT(SerialIO.readTournament(file));
			return true;
		} catch (BackupException e) {
			return false;
		}
=======
		return false;
>>>>>>> 55df286c4cdb3d6cc03cc13bd49f3ca598666edf
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
