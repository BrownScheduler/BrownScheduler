package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import middleend.MiddleEnd;

public class CompetitiveUnitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;

	/**
	 * This is the default constructor
	 */
	public CompetitiveUnitPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 400); //TODO: make constants
		this.setLayout(new GridLayout());
	}

}
