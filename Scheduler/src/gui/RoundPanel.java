package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import middleend.*;
import backbone.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RoundPanel extends JPanel implements GUIConstants {

	private Round _round;
	private ArrayList<PairingPanel> _pairingpanels;
	
	public RoundPanel(Round r) {
		_round = r;
		_pairingpanels = new ArrayList<PairingPanel>();
		initialize();
	}
	
	public void initialize() {
		this.setSize(DEFAULT_SIZE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (Pairing pairing : _round.getPairings()) {
			PairingPanel pp = new PairingPanel(pairing);
			_pairingpanels.add(pp);
			this.add(pp);
			this.add(Box.createRigidArea(SPACING_SIZE));
		}
	}
	
	public void repaintAll() {
		for (PairingPanel pairingpanel : _pairingpanels)
			pairingpanel.repaintAll();
		this.repaint();
	}
}