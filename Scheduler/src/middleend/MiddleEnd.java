package middleend;

import exception.BackupException;
import exception.CSVException;
import fileio.SerialIO;
import gui.*;
import backbone.*;
import java.io.File;

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
	
	public boolean openPlugin(File file) {
		return false;
	}
	
	public boolean openTournament(File file) {
		try {
			_tmnt = SerialIO.readTournament(file);
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
	public boolean saveFile(File file) {
		try {
			SerialIO.writeTournament(file, _tmnt);
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
}
