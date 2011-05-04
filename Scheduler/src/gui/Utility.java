package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import backbone.Attribute;
import backbone.BooleanAttribute;
import backbone.DoubleAttribute;
import backbone.GroupingAttribute;
import backbone.IntAttribute;
import backbone.StringAttribute;

public class Utility implements GUIConstants {

	public JLabel getHeader1Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		}
		return label;
	}
	
	public JLabel getHeader2Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		if (border) {
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		}
		return label;
	}
	
	public JLabel getHeader3Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
		}
		return label;
	}
	
	public JLabel getHeader4Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		}
		return label;
	}
	
	public void setLink(JLabel j) {
		j.setForeground(Color.BLUE);
		j.setText("<html><u>" + j.getText() + "</u></html>");
	}
	
	public JComponent getField(Attribute attribute) {
		if (attribute.getType() == Attribute.Type.BOOLEAN) {
			return getBooleanField((BooleanAttribute) attribute);
		}
		else if (attribute.getType() == Attribute.Type.DOUBLE) {
			return getDoubleField((DoubleAttribute) attribute);
		}
		else if (attribute.getType() == Attribute.Type.GROUPING) {
			return getGroupingField((GroupingAttribute) attribute);
		}
		else if (attribute.getType() == Attribute.Type.INT) {
			return getIntegerField((IntAttribute) attribute);
		}
		else if (attribute.getType() == Attribute.Type.STRING) {
			return getStringField((StringAttribute) attribute);
		}
		return null;
	}
	
	public JTextField getStringField(StringAttribute attribute) {
		JTextField field = new JTextField(attribute.getAttribute());
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(attribute.isEditable());
		return field;
	}
	
	public JFormattedTextField getIntegerField(IntAttribute attribute) {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(nf);
		field.setValue(attribute.getAttribute());
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(attribute.isEditable());
		return field;
	}
	
	public JFormattedTextField getDoubleField(DoubleAttribute attribute) {
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(df);
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setValue(attribute.getAttribute());
		field.setEditable(attribute.isEditable());
		field.setMaximumSize(new Dimension(100, 20));
		return field;
	}
	
	public JCheckBox getBooleanField(BooleanAttribute attribute) {
		final JCheckBox field = new JCheckBox();
		field.setSelected(attribute.getAttribute());
		if (!attribute.isEditable()) {
			final boolean val = attribute.getAttribute();
			field.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					field.setSelected(val);
				}
			});
		}
		return field;
	}
	
	public JButton getGroupingField(GroupingAttribute attribute) {
		JButton button = new JButton("Edit " + attribute.getTitle() + "...");
		return button;
	}
	
}
