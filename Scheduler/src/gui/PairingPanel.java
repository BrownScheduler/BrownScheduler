package gui;

import middleend.*;
import backbone.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PairingPanel extends JPanel implements GUIConstants {

	private MiddleEnd _middleEnd;
	private Round _round;
	private Pairing _pairing;
	
	public PairingPanel(MiddleEnd me, Round r, Pairing p) {
		_middleEnd = me;
		_round = r;
		_pairing = p;
		resetPanel();
	}
	
	public void resetPanel() {
		this.removeAll();
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		this.setSize(PAIRINGPANEL_SIZE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		JPanel deletepanel = new JPanel();
		if (COLORSON) {
			deletepanel.setBackground(BACKGROUND_COLOR);
			deletepanel.setForeground(FOREGROUND_COLOR);
		}
		deletepanel.setLayout(new BoxLayout(deletepanel, BoxLayout.Y_AXIS));
		final JButton actuallydeletebutton = new JButton("Actually delete this pairing");
		if (IMAGESON)
			actuallydeletebutton.setIcon(DELETEBUTTONIMAGE);
		if (COLORSON) {
			actuallydeletebutton.setBackground(BACKGROUND_COLOR);
			actuallydeletebutton.setForeground(FOREGROUND_COLOR);
		}
		actuallydeletebutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_round.removePairing(_pairing);
				_middleEnd.repaintAll();
			}
		});
		actuallydeletebutton.setVisible(false);
		final JButton deletebutton = new JButton("Delete this pairing");
		if (IMAGESON)
			deletebutton.setIcon(DELETEBUTTONIMAGE);
		if (COLORSON) {
			deletebutton.setBackground(BACKGROUND_COLOR);
			deletebutton.setForeground(FOREGROUND_COLOR);
		}
		deletebutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actuallydeletebutton.setVisible(true);
				repaint();
				deletebutton.setText("Click over there to delete ----->");
			}
		});
		deletepanel.add(deletebutton);
		deletepanel.add(actuallydeletebutton);
		this.add(deletepanel);
		this.add(Box.createRigidArea(BIGSPACING_SIZE));
		for (Attribute attribute : _pairing.getAttributes()) {
			if (attribute.getType() == Attribute.Type.UNIT) {
				JPanel attrpanel = new JPanel();
				if (COLORSON) {
					attrpanel.setBackground(BACKGROUND_COLOR);
					attrpanel.setForeground(FOREGROUND_COLOR);
				}
				attrpanel.setLayout(new BoxLayout(attrpanel, BoxLayout.Y_AXIS));
				attrpanel.add(Utility.getTitleLabel(attribute));
				attrpanel.add(new UnitAttributeComboBox((UnitAttribute<?>) attribute, _pairing, this));//Not header, needs to be editable
				if (((UnitAttribute<?>) attribute).att != null) {
					for (Attribute attr : ((UnitAttribute<?>) attribute).att.getAttributes()) {
						this.add(Box.createRigidArea(SMALLSPACING_SIZE));
						if (attr.getType() == Attribute.Type.GROUPING) {
							JLabel label = Utility.getTitleLabel(attr);
							label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
							attrpanel.add(label);
						}
						else {
							JLabel title = Utility.getTitleLabel(attr);
							JLabel value = Utility.getValueLabel(attr);
							JLabel label = new JLabel(title.getText() + ": " + value.getText());
							label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
							attrpanel.add(label);
						}
					}
				}
				else {
					this.add(Box.createRigidArea(SMALLSPACING_SIZE));
					JLabel title = Utility.getTitleLabel(attribute);
					title.setText(title.getText() + ": N/A");
					attrpanel.add(title);
				}
				this.add(attrpanel);
			}
			else if (attribute.getType() == Attribute.Type.GROUPING) {
				JLabel label = Utility.getTitleLabel(attribute);
				label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
				this.add(label);
			}
			else {
				this.add(Utility.getField(attribute));
			}
			this.add(Box.createRigidArea(BIGSPACING_SIZE));
		}
		this.add(Box.createHorizontalGlue());
	}
	
	public void repaintAll() {
		this.resetPanel();
		this.repaint();
	}
	
	public class UnitAttributeComboBox extends JComboBox {
		
		private UnitAttribute _unitattribute;
		private PairingPanel _pairingpanel;
		private Pairing _pairing;
		
		public UnitAttributeComboBox(UnitAttribute ua, Pairing p, PairingPanel pp) {
			_unitattribute = ua;
			_pairingpanel = pp;
			_pairing = p;
			
			this.setMaximumSize(JCOMBOBOX_SIZE);
			
			final ArrayList<Unit> units = new ArrayList<Unit>();
			units.add(null);
			units.addAll(_unitattribute.getListOfUnits());
			ArrayList<String> unitnames = new ArrayList<String>();
			unitnames.add("");
			int toSelect = 0;
			for (int i = 1; i < units.size(); i++) {
				unitnames.add(units.get(i).getName());
				if (units.get(i) == _unitattribute.att)
					toSelect = i;
			}
			this.setModel(new DefaultComboBoxModel(unitnames.toArray(new String[0])));
			this.setSelectedIndex(toSelect);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UnitAttributeComboBox cb = (UnitAttributeComboBox) e.getSource();
					if (cb.getSelectedIndex() <= 0) {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), _unitattribute.getMemberOf()));
						_pairingpanel.repaintAll();
					}
					else {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), units.get(cb.getSelectedIndex()), _unitattribute.getMemberOf()));
						_pairingpanel.repaintAll();
					}
				}
			});
		}
	}
	
}
