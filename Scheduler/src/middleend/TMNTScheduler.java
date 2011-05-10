package middleend;

import backbone.Tournament;
import java.util.ArrayList;
import java.util.Iterator;

public class TMNTScheduler extends Thread {

	ArrayList<MiddleEnd> _middleEnds;
	
	public TMNTScheduler(Tournament t) {
		_middleEnds = new ArrayList<MiddleEnd>();
		this.addTMNT(t);
		this.start();
	}
	
	public void addTMNT(Tournament t) {
		MiddleEnd me = new MiddleEnd(t, this);
		_middleEnds.add(me);
		me.start();
	}
	
	public void removeTMNT(Tournament t) {
		Iterator<MiddleEnd> iter = _middleEnds.iterator();
		MiddleEnd toremove = null;
		boolean contained = false;
		while (iter.hasNext()) {
			toremove = iter.next();
			if (toremove.getTournament() == t) {
				iter.remove();
				contained = true;
				break;
			}
		}
		if (contained)
			toremove.quit();
	}
	
	public void run() {
		while (_middleEnds.size() > 0)
		{System.out.print("");}
		System.exit(0);
	}
	
}
