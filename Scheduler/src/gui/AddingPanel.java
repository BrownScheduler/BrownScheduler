package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import middleend.*;
import backbone.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

/**
 * This class is the display where units can be added,
 * viewed, and edited. It consists of several UnitPanels.
 */
public class AddingPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private int _currView;
	private Object _currViewObject;  //  @jve:decl-index=0:

	/**
	 * This is the default constructor.
	 */
	public AddingPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		_currView = 0;
		_currViewObject = null;
		initialize();
	}
	
	/**
	 * This method initializes this.
	 * 
	 * @return void
	 */
	private void initialize() {
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		JLabel initLabel = new JLabel();
		this.setMinimumSize(ADDINGPANEL_SIZE);
		initLabel.setText("Welcome to TMNTScheduler! Choose a category on the side to begin editing!");
		if (IMAGESON) {
			initLabel.setIcon(INTROIMAGE);
			initLabel.setText("");
		}
		initLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel initPanel = new JPanel();
		if (COLORSON) {
			initPanel.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		initPanel.add(Box.createHorizontalGlue());
		initPanel.add(initLabel);
		initPanel.add(Box.createHorizontalGlue());
		this.add(Box.createVerticalGlue());
		this.add(initPanel);
		this.add(Box.createVerticalGlue());
	}
	
	/**
	 * Sets the panel to view the info of one unit.
	 * 
	 * @param unit
	 */
	public void setViewPanel(Unit unit) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, unit, false));
		_currView = 1;
		_currViewObject = unit;
	}
	
	/**
	 * Sets the panel to view the info of all the units
	 * in a particular grouping.
	 * 
	 * @param grouping
	 */
	public void setViewPanel(Grouping<Unit> grouping) {
		this.removeAll();
		JPanel savepanel = new JPanel();
		savepanel.setLayout(new BoxLayout(savepanel, BoxLayout.X_AXIS));
		savepanel.add(Box.createHorizontalGlue());
		JButton savebutton = new JButton("Save all changes");
		savepanel.add(savebutton);
		savepanel.add(Box.createHorizontalGlue());
		final ArrayList<JButton> savebuttons = new ArrayList<JButton>();
		this.add(savepanel);
		for (Unit u : grouping.getMembers()) {
			UnitPanel up = new UnitPanel(_middleEnd, u, true);
			this.add(up);
			this.add(Box.createRigidArea(SMALLSPACING_SIZE));
			savebuttons.add(up.getSaveButton());
		}
		this.add(Box.createVerticalGlue());
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JButton button : savebuttons)
					button.doClick();
				_middleEnd.repaintAll();
			}
		});
		_currView = 2;
		_currViewObject = grouping;
	}
	
	/**
	 * Allows the user to add a new unit to the specified
	 * grouping.
	 * 
	 * @param grouping
	 */
	public void setAddPanel(Grouping<Unit> grouping) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, grouping.getBlank(), grouping));
		_currView = 3;
		_currViewObject = grouping;
	}
	
	/**
	 * Repaints this and all the contained components.
	 */
	public void repaintAll() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).repaint();
		}
		switch (_currView) {
		case 1:
			this.setViewPanel((Unit) _currViewObject);
			break;
		case 2:
			this.setViewPanel((Grouping<Unit>) _currViewObject);
			break;
		case 3:
			this.setAddPanel((Grouping<Unit>) _currViewObject);
			break;
		default:
		}
		this.revalidate();
		this.repaint();
	}
}
