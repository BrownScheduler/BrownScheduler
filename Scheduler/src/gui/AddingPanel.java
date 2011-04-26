package gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class AddingPanel extends JPanel {

	public static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public AddingPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(400, 400); //TODO: make constants
		this.setLayout(new GridBagLayout());
	}

}
