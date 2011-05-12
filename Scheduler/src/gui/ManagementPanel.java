package gui;

import backbone.*;
import middleend.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 * This is the tabbed pane that allows users to view the different rounds.
 * Each tab is a different RoundPanel.
 */
public class ManagementPanel extends JTabbedPane implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private ArrayList<RoundPanel> _roundpanels;

	/**
	 * This is the default constructor
	 */
	public ManagementPanel(MiddleEnd me) {
		super(SwingConstants.TOP);
		_middleEnd = me;
		_roundpanels = new ArrayList<RoundPanel>();
		this.setSize(DEFAULT_SIZE);
		resetPanel();
	}

	/**
	 * This method resets this panel.
	 */
	public void resetPanel() {
		this.removeAll();
		_roundpanels.clear();
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		// Add a new tab to this pane for every round.
		for (Round round : _middleEnd.getTournament().getRounds()) {
			if (round != null) {
				RoundPanel rp = new RoundPanel(_middleEnd, round);
				_roundpanels.add(rp);
				JPanel rpcontainer = new JPanel();
				if (COLORSON) {
					rpcontainer.setBackground(BACKGROUND_COLOR);
					rpcontainer.setForeground(FOREGROUND_COLOR);
				}
				rpcontainer.setLayout(new java.awt.BorderLayout());
				rpcontainer.add(rp, java.awt.BorderLayout.CENTER);
				// Add every RoundPanel to a JScrollPane
				JScrollPane rpscroller = new JScrollPane(rpcontainer);
				rpscroller.getVerticalScrollBar().setBlockIncrement(90);
				rpscroller.getVerticalScrollBar().setUnitIncrement(30);
				this.addTab(round.getName(), rpscroller);
				this.setSelectedComponent(rpscroller);
			}
		}
	}

	/**
	 * Repaints this component and all the components contained in it.
	 */
	public void repaintAll() {
		this.resetPanel();
		for (RoundPanel panel : _roundpanels)
			panel.repaintAll();
		this.repaint();
	}
	
}
