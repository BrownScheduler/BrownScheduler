package gui;

import middleend.*;
import backbone.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class AddingPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private int _currView;
	private Object _currViewObject;

	/**
	 * This is the default constructor
	 */
	public AddingPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		_currView = 0;
		_currViewObject = null;
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		JLabel initLabel = new JLabel();
		this.setMinimumSize(ADDINGPANEL_SIZE);
		initLabel.setText("Choose a category on the side to begin editing!");
		initLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayout(new GridLayout(0, 1));
		this.add(initLabel);
	}
	
	public void setViewPanel(Unit unit) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, unit));
		_currView = 1;
		_currViewObject = unit;
	}
	
	public void setViewPanel(Grouping<Unit> grouping) {
		this.removeAll();
		for (Unit u : grouping.getMembers()) {
			this.add(new UnitPanel(_middleEnd, u));
			this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		}
		this.add(Box.createVerticalGlue());
		_currView = 2;
		_currViewObject = grouping;
	}
	
	public void setAddPanel(Grouping<Unit> grouping) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, grouping.getBlank(), grouping));
		_currView = 3;
		_currViewObject = grouping;
	}
	
	public void repaintAll() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).repaint();
		}
		switch (_currView) {
		case 1:
//			this.setViewPanel((Unit) _currViewObject);
			break;
		case 2:
			this.setViewPanel((Grouping<Unit>) _currViewObject);
			break;
		case 3:
			this.setAddPanel((Grouping<Unit>) _currViewObject);
			break;
		default:
		}
		this.repaint();
		this.setSize(new Dimension(this.getWidth() + 1, this.getHeight() + 1));
		this.setSize(new Dimension(this.getWidth() - 1, this.getHeight() - 1));	}
}
