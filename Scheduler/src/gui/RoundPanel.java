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

public class RoundPanel extends JPanel implements GUIConstants {

	private MiddleEnd _middleEnd;
	private Round _round;
	private ArrayList<PairingPanel> _pairingpanels;
	
	public RoundPanel(MiddleEnd me, Round r) {
		_middleEnd = me;
		_round = r;
		_pairingpanels = new ArrayList<PairingPanel>();
		resetPanel();
	}
	
	public void resetPanel() {
		this.removeAll();
		_pairingpanels.clear();
		this.setSize(DEFAULT_SIZE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton addpairingbutton = new JButton("Add New Blank Pairing");
		addpairingbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_round.addPairing(_round.getBlank());
				_middleEnd.repaintAll();
			}
		});
		addpairingbutton.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(addpairingbutton);
		this.add(Box.createHorizontalGlue());
		if (_round == null)
			return;
		for (Pairing pairing : _round.getPairings()) {
			PairingPanel pp = new PairingPanel(_middleEnd, _round, pairing);
			_pairingpanels.add(pp);
			this.add(pp);
			this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		}
		this.add(Box.createVerticalGlue());
	}
	
	public void repaintAll() {
		this.resetPanel();
		for (PairingPanel pairingpanel : _pairingpanels)
			pairingpanel.repaintAll();
		this.repaint();
	}
}
