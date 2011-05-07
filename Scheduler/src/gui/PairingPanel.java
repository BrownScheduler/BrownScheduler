package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import middleend.*;
import backbone.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class PairingPanel extends JPanel implements GUIConstants {

	private Pairing _pairing;
	
	public PairingPanel(Pairing p) {
		_pairing = p;
		resetPanel();
	}
	
	public void resetPanel() {
		this.removeAll();
		this.setSize(PAIRINGPANEL_SIZE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		for (Attribute attribute : _pairing.getAttributes()) {
			if (attribute.getType() == Attribute.Type.UNIT) {
				JPanel attrpanel = new JPanel();
				attrpanel.setLayout(new BoxLayout(attrpanel, BoxLayout.Y_AXIS));
				attrpanel.add(new UnitAttributeComboBox((UnitAttribute) attribute, _pairing, this));//Not header, needs to be editable
				if (((UnitAttribute) attribute).att != null) {
					for (Attribute attr : ((UnitAttribute) attribute).att.getAttributes()) {
						this.add(Box.createRigidArea(SPACING_SIZE));
						if (attr.getType() == Attribute.Type.GROUPING) {
							attrpanel.add(Utility.getTitleLabel(attr));
						}
						else {
							JLabel title = Utility.getTitleLabel(attr);
							JLabel value = Utility.getValueLabel(attr);
							attrpanel.add(new JLabel(title.getText() + ": " + value.getText()));
						}
					}
				}
				else {
					this.add(Box.createRigidArea(SPACING_SIZE));
					JLabel title = Utility.getTitleLabel(attribute);
					title.setText(title.getText() + ": N/A");
					attrpanel.add(title);
				}
				this.add(attrpanel);
			}
			else if (attribute.getType() == Attribute.Type.GROUPING) {
				this.add(Utility.getTitleLabel(attribute));
			}
			else {
				this.add(Utility.getField(attribute));
			}
			this.add(Box.createHorizontalGlue());//.createRigidArea(SPACING_SIZE));
		}
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
			
			//TODO: how to get Grouping<Unit> from UnitAttribute if UnitAttribute.att == null?
			final Unit[] units = (Unit[]) _unitattribute.getListOfUnits().toArray(new Unit[0]);
			String[] unitnames = new String[units.length];
			units[0] = null;
			unitnames[0] = "";
			for (int i = 1; i < units.length; i++)
				unitnames[i] = units[i].getName();
			this.setModel(new DefaultComboBoxModel(unitnames));
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UnitAttributeComboBox cb = (UnitAttributeComboBox) e.getSource();
					if (cb.getSelectedIndex() == 0) {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), _unitattribute.getMemberOf()));
					}
					else {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), units[cb.getSelectedIndex()]));
					}
				}
			});
		}
	}
	
}
