package gui;

import middleend.*;
import backbone.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class AddingPanel extends JPanel implements GUIConstants {

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
	private void initialize() {
		JLabel initLabel = new JLabel();
		initLabel.setText("Choose a category on the side to begin editing!");
		initLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setSize(ADDINGPANEL_SIZE);
		this.setLayout(new BorderLayout());
		this.add(initLabel, BorderLayout.CENTER);
	}
	
	public void setViewPanel(Unit unit) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, unit));
	}
	
	public void setViewPanel(Grouping<Unit> grouping) {
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (Unit u : grouping.getMembers()) {
			this.add(new UnitPanel(_middleEnd, u));
			//this.add(Box.createRigidArea(new Dimension(10, 20)));
		}
		this.add(Box.createVerticalGlue());
	}
	
	public void setAddPanel(Grouping<Unit> grouping) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, grouping.getBlank(), grouping));
	}
	
	public void repaintAll() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).repaint();
		}
		this.repaint();
		this.setSize(new Dimension(this.getWidth() + 1, this.getHeight() + 1));
		this.setSize(new Dimension(this.getWidth() - 1, this.getHeight() - 1));	}
}
