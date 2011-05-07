package gui;

import backbone.*;
import middleend.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagementPanel extends JTabbedPane implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;

	/**
	 * This is the default constructor
	 */
	public ManagementPanel(MiddleEnd m) {
		super(JTabbedPane.TOP);
		_middleEnd = m;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(DEFAULT_SIZE);
		for (Round round : _middleEnd.getTournament().getRounds()) {
			
		}
	}

	public void repaintAll() {
		this.repaint();
	}
	
}
