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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
				attrpanel.add(Utility.getTitleLabel(attribute));
				attrpanel.add(new UnitAttributeComboBox((UnitAttribute) attribute, _pairing, this));//Not header, needs to be editable
				if (((UnitAttribute) attribute).att != null) {
					for (Attribute attr : ((UnitAttribute) attribute).att.getAttributes()) {
						this.add(Box.createRigidArea(SPACING_SIZE));
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
					this.add(Box.createRigidArea(SPACING_SIZE));
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
				public void actionPerformed(ActionEvent e) {
					UnitAttributeComboBox cb = (UnitAttributeComboBox) e.getSource();
					if (cb.getSelectedIndex() <= 0) {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), _unitattribute.getMemberOf()));
						_pairingpanel.repaintAll();
					}
					else {
						_pairing.setAttribute(new UnitAttribute(_unitattribute.getTitle(), units.get(cb.getSelectedIndex())));
						_pairingpanel.repaintAll();
					}
				}
			});
		}
	}
	
}
