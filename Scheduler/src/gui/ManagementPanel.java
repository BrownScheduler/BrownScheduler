package gui;

import javax.swing.JPanel;
import middleend.MiddleEnd;

public class ManagementPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;

	/**
	 * This is the default constructor
	 */
	public ManagementPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(DEFAULT_SIZE); //TODO: make constants
		//this.setLayout(new SpringLayout());
	}

}
