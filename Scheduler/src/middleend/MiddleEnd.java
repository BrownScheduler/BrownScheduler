package middleend;

import exception.BackupException;
import exception.CSVException;
import fileio.CSVIO;
import fileio.SerialIO;
import gui.*;
import backbone.*;
import java.io.File;

/**
 * This class runs a tournament, and interfaces between the GUI
 * and the other classes (the tournament, the TMNTScheduler, and
 * the File IO classes).
 */
public class MiddleEnd extends Thread {
	
	private TMNTScheduler _scheduler;
	private Tournament _tmnt;
	private App _app;
	private boolean _continue;
	
	/**
	 * Constructor. Each MiddleEnd is based on a tournament, and requires
	 * a reference up to the TMNTScheduler "super-class" that is running
	 * everything.
	 * 
	 * @param Tournament
	 * @param TMNTScheduler
	 */
	public MiddleEnd(Tournament t, TMNTScheduler s) {
		_tmnt = t;
		_scheduler = s;
		_continue = true;
		_app = new App(this);
	}
	
	/**
	 * Refreshes everything in the GUI. 
	 */
	public void repaintAll() {
		_app.repaintAll();
	}
	
	/**
	 * Opens a new MiddleEnd based on a new blank tournament.
	 */
	public void openNewMiddleEnd() {
		_scheduler.addTMNT(_tmnt.getNew());
	}
	
	/**
	 * Opens a new MiddleEnd based on the given tournament.
	 * @param tournament
	 */
	public void openNewMiddleEnd(Tournament t) {
		_scheduler.addTMNT(t);
	}
	
	/**
	 * Closes this MiddleEnd, closing the window and quitting
	 * the application if this is the last window.
	 */
	public void closeThisMiddleEnd() {
		_scheduler.removeTMNT(_tmnt);
		_continue = false;
	}
	
	/**
	 * Returns the tournament associated with this MiddleEnd.
	 * 
	 * @return Tournament
	 */
	public Tournament getTournament() {
		return _tmnt;
	}
	
	/**
	 * Opens a tournament from the given file, returning true if
	 * an error occurred when opening the file and false if it
	 * occurred successfully.
	 * 
	 * @param file
	 * @return boolean
	 */
	public boolean openTournament(File file) {
		try {
			_scheduler.addTMNT(SerialIO.readTournament(file));
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
	/**
	 * Saves a tournament to the given file, returning true if
	 * an error occurred when saving the file and false if it
	 * occurred successfully.
	 * 
	 * @param file
	 * @return boolean
	 */
	public boolean saveTournament(File file) {
		try {
			SerialIO.writeTournament(file, _tmnt);
			return true;
		} catch (BackupException e) {
			return false;
		}
	}
	
	/**
	 * Imports a category from the given file, returning true if
	 * an error occurred when importing the file and false if it
	 * occurred successfully.
	 * 
	 * @param file
	 * @return boolean
	 */
	public boolean importCategory(File file) {
		try {
			CSVIO.loadGrouping(file, _tmnt);
			return true;
		} catch (CSVException e) {
			return false;
		}
	}
	
	/**
	 * Exports a category to the given file, returning true if
	 * an error occurred when exporting to file and false if it
	 * occurred successfully.
	 * 
	 * @param file
	 * @return boolean
	 */
	public boolean exportCategory(Grouping g, File file) {
		try {
			CSVIO.writeGrouping(file, g);
			return true;
		} catch (CSVException e) {
			return false;
		}
	}
	
	/**
	 * Runs the program until the user closes it.
	 */
	public void run() {
		while (_continue)
		{}
	}
}
