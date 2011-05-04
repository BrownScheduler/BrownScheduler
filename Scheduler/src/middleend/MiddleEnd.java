package middleend;

import gui.*;
import backbone.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiddleEnd {
	
	Tournament _tmnt;
	
	public MiddleEnd(Tournament t) {
		_tmnt = t;
		
		App application = new App(this);
		application.getJFrame().setVisible(true);
	}
	
	public Tournament getTournament() {
		return _tmnt;
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
