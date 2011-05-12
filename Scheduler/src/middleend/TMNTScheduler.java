package middleend;

import backbone.Tournament;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is the "super-class" that runs the entire program.
 * 
 * It is capable of running multiple tournaments in separate windows
 * on different threads. This class holds mulitple MiddleEnd classes
 * which start up the tournaments and run them in their own threads.
 */
public class TMNTScheduler extends Thread {

	ArrayList<MiddleEnd> _middleEnds;
	
	/**
	 * Constructor for a TMNTScheduler.
	 * 
	 * Requires a tournament, since the program quits if no tournaments
	 * are open currently.
	 * @param Tournament
	 */
	public TMNTScheduler(Tournament t) {
		_middleEnds = new ArrayList<MiddleEnd>();
		this.addTMNT(t);
		this.start();
	}
	
	/**
	 * Creates a new window with a new MiddleEnd for the specified
	 * tournament.
	 * 
	 * @param Tournament
	 */
	public void addTMNT(Tournament t) {
		MiddleEnd me = new MiddleEnd(t, this);
		_middleEnds.add(me);
		me.start();
	}
	
	/**
	 * Removes this tournament from the TMNTScheduler's open windows.
	 * 
	 * Must be called from the MiddleEnd class when it quits.
	 * 
	 * @param Tournament
	 */
	public void removeTMNT(Tournament t) {
		Iterator<MiddleEnd> iter = _middleEnds.iterator();
		MiddleEnd toremove = null;
		while (iter.hasNext()) {
			toremove = iter.next();
			if (toremove.getTournament() == t) {
				iter.remove();
				break;
			}
		}
	}
	
	/**
	 * Runs the TMNTScheduler while there are still open tournament
	 * windows.
	 */
	public void run() {
		while (_middleEnds.size() > 0)
		{System.out.print("");}
		System.exit(0);
	}
	
}
