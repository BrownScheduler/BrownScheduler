package scheduler;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The App class, used to actually run the program, extends JFrame.
 */
@SuppressWarnings("serial")
public class App extends JFrame {

	public App() {
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Insert any methods that need to be called on exit
				System.exit(0);
			}
		});
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new App();
	}

}

