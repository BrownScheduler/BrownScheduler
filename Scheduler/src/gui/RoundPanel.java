package gui;

import middleend.*;
import backbone.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the panel that allows the user to view all
 * the pairings in a round. Each panel consists of
 * multiple PairingPanels.
 */
public class RoundPanel extends JPanel implements GUIConstants {

	private MiddleEnd _middleEnd;
	private Round _round;
	private ArrayList<PairingPanel> _pairingpanels;
	
	/**
	 * Constructor.
	 * @param me
	 * @param r
	 */
	public RoundPanel(MiddleEnd me, Round r) {
		_middleEnd = me;
		_round = r;
		_pairingpanels = new ArrayList<PairingPanel>();
		resetPanel();
	}
	
	/**
	 * This resets the panel.
	 */
	public void resetPanel() {
		this.removeAll();
		_pairingpanels.clear();
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		this.setSize(DEFAULT_SIZE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Allows the user to add new blank pairings
		JButton addpairingbutton = new JButton("Add New Blank Pairing");
		if (IMAGESON)
			addpairingbutton.setIcon(ADDBUTTONIMAGE);
		if (COLORSON) {
			addpairingbutton.setBackground(BACKGROUND_COLOR);
			addpairingbutton.setForeground(FOREGROUND_COLOR);
		}
		addpairingbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_round.addPairing(_round.getBlank());
				repaintAll();
			}
		});
		this.add(Box.createHorizontalGlue());
		addpairingbutton.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(addpairingbutton);
		this.add(Box.createHorizontalGlue());
		if (_round == null)
			return;
		// Display all the pairings in this round
		for (Pairing pairing : _round.getPairings()) {
			PairingPanel pp = new PairingPanel(_middleEnd, _round, pairing);
			_pairingpanels.add(pp);
			this.add(pp);
			this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		}
		this.add(Box.createVerticalGlue());
	}
	
	/**
	 * Repaints this component and all the component contained in it.
	 */
	public void repaintAll() {
		this.resetPanel();
		for (PairingPanel pairingpanel : _pairingpanels)
			pairingpanel.repaintAll();
		this.repaint();
	}
}
