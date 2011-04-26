package gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import middleend.MiddleEnd;

public class AddingPanel extends JPanel {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;

	/**
	 * This is the default constructor
	 */
	public AddingPanel(MiddleEnd m) {
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
		this.setSize(400, 400); //TODO: make constants
		this.setLayout(new GridBagLayout());
	}
	
	public void setPanel(GUIAttribute attribute) {
		
	}
	
	public void setPanel(GUICompetitiveUnit competitiveunit) {
		
	}
	
	public void setPanel(GUIGroupingAttribute groupingattribute) {
		
	}
	
	public void setPanel(GUIGrouping grouping) {
		
	}

}
