package middleend;

import exception.BackupException;
import exception.CSVException;
import fileio.CSVIO;
import fileio.SerialIO;
import gui.*;
import backbone.*;
import java.io.File;

public class MiddleEnd extends Thread {
	
	TMNTScheduler _scheduler;
	Tournament _tmnt;
	App _app;
	
	public MiddleEnd(Tournament t, TMNTScheduler s) {
		_tmnt = t;
		_scheduler = s;
		_app = new App(this);
	}
	
	public void repaintAll() {
		_app.repaintAll();
	}
	
	public void openNewMiddleEnd() {
		//TODO: _scheduler.addTMNT(new Tournament());
	}
	
	public void openNewMiddleEnd(Tournament t) {
		_scheduler.addTMNT(t);
	}
	
	public Tournament getTournament() {
		return _tmnt;
	}
	
	public void setTournament(Tournament t) {
		_tmnt = t;
	}
	
	public boolean openTournament(File file) {
		try {
			_tmnt = SerialIO.readTournament(file);
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
	public boolean saveTournament(File file) {
		try {
			SerialIO.writeTournament(file, _tmnt);
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
	public boolean importCategory(File file) {
		try {
			CSVIO.loadGrouping(file, _tmnt);
			return true;
		} catch (CSVException e) {
			return false;
		}
	}
	
	public boolean exportCategory(Grouping g, File file) {
		try {
			CSVIO.writeGrouping(file, (Grouping<? extends Unit>) g);
			return true;
		} catch (CSVException e) {
			return false;
		}
	}
//	
//	public void run() {
//		while(true) {}
//	}
}
