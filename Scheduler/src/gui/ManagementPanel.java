package gui;

import backbone.*;
import middleend.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ManagementPanel extends JTabbedPane implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private ArrayList<RoundPanel> _roundpanels;

	/**
	 * This is the default constructor
	 */
	public ManagementPanel(MiddleEnd m) {
		super(JTabbedPane.TOP);
		_middleEnd = m;
		_roundpanels = new ArrayList<RoundPanel>();
		this.setSize(DEFAULT_SIZE);
		resetPanel();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void resetPanel() {
		this.removeAll();
		_roundpanels.clear();
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		for (Round round : _middleEnd.getTournament().getRounds()) {
			RoundPanel rp = new RoundPanel(round);
			_roundpanels.add(rp);
			JPanel rpcontainer = new JPanel();
			rpcontainer.setLayout(new java.awt.BorderLayout());
			rpcontainer.add(rp, java.awt.BorderLayout.CENTER);
			JScrollPane rpscroller = new JScrollPane(rpcontainer);
			this.addTab(round.getName(), rpscroller);
			this.setSelectedComponent(rpscroller);
		}
	}

	public void repaintAll() {
		this.resetPanel();
		for (RoundPanel panel : _roundpanels)
			panel.repaintAll();
		this.repaint();
	}
	
}
