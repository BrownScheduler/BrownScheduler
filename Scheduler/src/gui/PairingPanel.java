package gui;

import java.awt.Color;

import middleend.*;
import backbone.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class PairingPanel extends JPanel implements GUIConstants {

	private Pairing _pairing;
	
	public PairingPanel(Pairing p) {
		_pairing = p;
		initialize();
	}
	
	public void initialize() {
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		this.setLayout(new SpringLayout());
		
		int cols = _pairing.getAttributes().size() + 1;
		int rows = 1;
		for (Attribute attr : _pairing.getAttributes()) {
			if (attr.getType() == Attribute.Type.UNIT)
				rows = Math.max(rows, ((UnitAttribute) attr).att.getAttributes().size());
		}
		int xspacing = SPACING_X;
		int yspacing = SPACING_Y;
		
		JComponent[][] components = new JComponent[cols][rows];
		for (int i = 0; i < components.length; i++) {
			for (int j = 0; j < components[i].length; j++)
				components[i][j] = new JLabel();
		}
		int i = 0;
		for (Attribute attribute : _pairing.getAttributes()) {
			if (attribute.getType() == Attribute.Type.UNIT) {
				JPanel attrpanel = new JPanel();
				components[i][0] = new JLabel(((UnitAttribute) attribute).att.getName());
				int j = 1;
				for (Attribute a : ((UnitAttribute) attribute).att.getAttributes()) {
					
					j++;
				}
			}
			else {
//				if (attribute.getType() == Attribute.Type.BOOLEAN) {
//					components[i][0] getBooleanField((BooleanAttribute) attribute, attrisEditable);
//				}
//				else if (attribute.getType() == Attribute.Type.DOUBLE) {
//					components[i][0] getDoubleField((DoubleAttribute) attribute, isEditable);
//				}
//				else if (attribute.getType() == Attribute.Type.GROUPING) {
//					components[i][0] getGroupingField((GroupingAttribute) attribute, isEditable);
//				}
//				else if (attribute.getType() == Attribute.Type.INT) {
//					components[i][0] getIntegerField((IntAttribute) attribute, isEditable);
//				}
//				else if (attribute.getType() == Attribute.Type.STRING) {
//					components[i][0] getStringField((StringAttribute) attribute, isEditable);
//				}
			}
			i++;
		}
		
		for (i = 0; i < components.length; i++) {
			for (int j = 0; j < components[i].length; j++)
				this.add(components[i][j]);
		}
		
		SpringUtilities.makeCompactGrid(this, rows, cols, xspacing, yspacing, xspacing, yspacing);
	}
	
	public void repaintAll() {
		this.repaint();
	}
}
