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

/**
 * This is the panel that displays a pairing attributes and allows
 * the user to edit them.
 */
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
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(Color.green);
//		this.add(Box.createHorizontalGlue());
		JPanel deletepanel = new JPanel();
		if (COLORSON) {
			deletepanel.setBackground(BACKGROUND_COLOR);
			deletepanel.setForeground(FOREGROUND_COLOR);
		}
		deletepanel.setLayout(new BoxLayout(deletepanel, BoxLayout.Y_AXIS));
		final JButton deletebutton = new JButton("Delete this pairing");
		if (IMAGESON)
			deletebutton.setIcon(DELETEBUTTONIMAGE);
		if (COLORSON) {
			deletebutton.setBackground(BACKGROUND_COLOR);
			deletebutton.setForeground(FOREGROUND_COLOR);
		}
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deletebutton.getText().equals("Delete this pairing"))
					deletebutton.setText("Are you sure?");
				else {
					_round.removePairing(_pairing);
					_middleEnd.repaintAll();
				}
					
			}
		});
		deletepanel.add(deletebutton);
		this.add(deletepanel);
		this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		int attNum = 0;
		JPanel bigWrapper = new JPanel();
		if (COLORSON) {
			bigWrapper.setBackground(BACKGROUND_COLOR);
			bigWrapper.setForeground(FOREGROUND_COLOR);
		}
		bigWrapper.setLayout(new BoxLayout(bigWrapper, BoxLayout.Y_AXIS));
		JPanel toAddTo = new JPanel();
		for (Attribute attribute : _pairing.getAttributes()) {
			if(attNum % 4 == 0){
				if(attNum != 0){
					bigWrapper.add(Utility.wrapLeft(toAddTo));
					bigWrapper.add(Box.createRigidArea(SMALLSPACING_SIZE));
				}
				toAddTo = new JPanel();
				if (COLORSON) {
					toAddTo.setBackground(BACKGROUND_COLOR);
					toAddTo.setForeground(FOREGROUND_COLOR);
				}
				toAddTo.setLayout(new BoxLayout(toAddTo, BoxLayout.X_AXIS));
			}
			attNum++;
			if (attribute.getType() == Attribute.Type.UNIT) {
				JPanel attrpanel = new JPanel();
				if (COLORSON) {
					attrpanel.setBackground(BACKGROUND_COLOR);
					attrpanel.setForeground(FOREGROUND_COLOR);
				}
				attrpanel.setLayout(new BoxLayout(attrpanel, BoxLayout.Y_AXIS));
				attrpanel.add(Utility.wrapLeft(Utility.getTitleLabel(attribute)));
				attrpanel.add(Utility.wrapLeft(new UnitAttributeComboBox((UnitAttribute<?>) attribute, _pairing, this)));//Not header, needs to be editable
				if (((UnitAttribute<?>) attribute).att != null) {
					for (Attribute attr : ((UnitAttribute<?>) attribute).att.getAttributes()) {
						toAddTo.add(Box.createRigidArea(SMALLSPACING_SIZE));
						if (attr.getType() == Attribute.Type.GROUPING) {
							JLabel label = Utility.getTitleLabel(attr);							
							label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
							attrpanel.add(Utility.wrapLeft(label));
						}
						else {
							JLabel title = Utility.getTitleLabel(attr);
							JLabel value = Utility.getValueLabel(attr);
							JLabel label = new JLabel(title.getText() + ": " + value.getText());
							label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
							attrpanel.add(Utility.wrapLeft(label));
						}
					}
				}
				else {
					toAddTo.add(Box.createRigidArea(SMALLSPACING_SIZE));
					JLabel title = Utility.getTitleLabel(attribute);
					title.setText(title.getText() + ": N/A");
					attrpanel.add(Utility.wrapLeft(title));
				}
				toAddTo.add(Utility.wrapUp(attrpanel));
			}
//			else if (attribute.getType() == Attribute.Type.GROUPING) {
//				JLabel label = Utility.getTitleLabel(attribute);
//				label.setToolTipText("Go to the input panel to edit/view this attribute of this unit.");
//				toAddTo.add(label);
//			}
			else {
				toAddTo.add(Utility.wrapUp(Utility.getField(attribute)));
			}
			toAddTo.add(Box.createRigidArea(SMALLSPACING_SIZE));
		}
		bigWrapper.add(Utility.wrapLeft(toAddTo));
//		bigWrapper.add(Box.createVerticalGlue());
		//bigWrapper.setPreferredSize(new Dimension(PAIRINGPANEL_SIZE.width, PAIRINGPANEL_SIZE.height * ((attNum / 4)+1) ));
		//this.setSize(new Dimension(PAIRINGPANEL_SIZE.width, PAIRINGPANEL_SIZE.height * ((attNum / 4)+1) ));
		this.add(bigWrapper);
		this.add(Box.createHorizontalGlue());
	}
	
	public void repaintAll() {
		this.resetPanel();
		this.repaint();
	}
	
	private class UnitAttributeComboBox extends JComboBox {
		
		private UnitAttribute _unitattribute;
		private PairingPanel _pairingpanel;
		private Pairing _pairing;
		
		public UnitAttributeComboBox(UnitAttribute ua, Pairing p, PairingPanel pp) {
			_unitattribute = ua;
			_pairingpanel = pp;
			_pairing = p;
			
			this.setPreferredSize(JCOMBOBOX_SIZE);
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
