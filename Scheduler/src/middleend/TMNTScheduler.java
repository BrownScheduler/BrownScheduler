package middleend;

import backbone.Tournament;
import java.util.ArrayList;
import java.util.Iterator;

public class TMNTScheduler {

	ArrayList<MiddleEnd> _middleEnds;
	
	public TMNTScheduler() {
		_middleEnds = new ArrayList<MiddleEnd>();
	}
	
	public TMNTScheduler(Tournament t) {
		_middleEnds = new ArrayList<MiddleEnd>();
		this.addTMNT(t);
	}
	
	public void addTMNT(Tournament t) {
		MiddleEnd me = new MiddleEnd(t, this);
		_middleEnds.add(me);
		me.start();
	}
	
	public void removeTMNT(Tournament t) {
		Iterator<MiddleEnd> iter = _middleEnds.iterator();
		while (iter.hasNext()) {
			if (iter.next().getTournament() == t)
				iter.remove();
		}
	}
	
}
