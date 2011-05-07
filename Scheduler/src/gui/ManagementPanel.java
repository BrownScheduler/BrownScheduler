package gui;

import java.util.ArrayList;
import backbone.*;
import middleend.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagementPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private JTabbedPane _tabpanel;
	private ArrayList<RoundPanel> _roundpanels;

	/**
	 * This is the default constructor
	 */
	public ManagementPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		_tabpanel = new JTabbedPane(JTabbedPane.TOP);
		_roundpanels = new ArrayList<RoundPanel>();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(DEFAULT_SIZE);
		_tabpanel.setSize(DEFAULT_SIZE);
		for (Round round : _middleEnd.getTournament().getRounds()) {
			RoundPanel rp = new RoundPanel(round);
			_roundpanels.add(rp);
			_tabpanel.add(round.getName(), rp);
			_tabpanel.setSelectedComponent(rp);
		}
	}

	public void repaintAll() {
		for (RoundPanel panel : _roundpanels)
			panel.repaintAll();
		this.repaint();
	}
	
}
