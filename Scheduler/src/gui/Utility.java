package gui;

import backbone.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Utility implements GUIConstants {

	public static JLabel getHeader1Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		}
		return label;
	}
	
	public static JLabel getHeader2Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		if (border) {
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		}
		return label;
	}
	
	public static JLabel getHeader3Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
		}
		return label;
	}
	
	public static JLabel getHeader4Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		}
		return label;
	}
	
	public static void setLink(JLabel j) {
		j.setForeground(Color.BLUE);
		j.setText("<html><u>" + j.getText() + "</u></html>");
	}
	
	public static JComponent getField(Attribute attribute) {
		return getField(attribute, attribute.isEditable());
	}
	
	public static JComponent getField(Attribute attribute, boolean isEditable) {
		if (attribute.getType() == Attribute.Type.BOOLEAN) {
			return getBooleanField((BooleanAttribute) attribute, isEditable);
		}
		else if (attribute.getType() == Attribute.Type.DOUBLE) {
			return getDoubleField((DoubleAttribute) attribute, isEditable);
		}
		else if (attribute.getType() == Attribute.Type.GROUPING) {
			return getGroupingField((GroupingAttribute<?>) attribute, isEditable);
		}
		else if (attribute.getType() == Attribute.Type.INT) {
			return getIntegerField((IntAttribute) attribute, isEditable);
		}
		else if (attribute.getType() == Attribute.Type.STRING) {
			return getStringField((StringAttribute) attribute, isEditable);
		}
		else if (attribute.getType() == Attribute.Type.UNIT) {
			return getUnitField((UnitAttribute) attribute, isEditable);
		}
		return null;
	}
	
	public static JTextField getStringField(StringAttribute attribute, boolean isEditable) {
		JTextField field = new JTextField(attribute.getAttribute());
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(isEditable);
		return field;
	}
	
	public static JFormattedTextField getIntegerField(IntAttribute attribute, boolean isEditable) {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(nf);
		field.setValue(attribute.getAttribute());
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(isEditable);
		return field;
	}
	
	public static JFormattedTextField getDoubleField(DoubleAttribute attribute, boolean isEditable) {
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(df);
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setValue(attribute.getAttribute());
		field.setEditable(isEditable);
		field.setMaximumSize(new Dimension(100, 20));
		return field;
	}
	
	public static JCheckBox getBooleanField(BooleanAttribute attribute, boolean isEditable) {
		final JCheckBox field = new JCheckBox();
		field.setSelected(attribute.getAttribute());
		if (!isEditable) {
			final boolean val = attribute.getAttribute();
			field.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					field.setSelected(val);
				}
			});
		}
		return field;
	}
	
	public static JButton getGroupingField(GroupingAttribute<?> attribute, boolean isEditable) {
		JButton button = new JButton("Edit " + attribute.getTitle() + "...");
		return button;
	}
	
	public static UnitAttributeComboBox getUnitField(UnitAttribute<Unit> attribute, boolean isEditable) {
		final UnitAttributeComboBox combobox = new UnitAttributeComboBox(attribute);
		return combobox;
	}
	
	public static JLabel getTitleLabel(Attribute attribute) {
		return new JLabel(attribute.getTitle());
	}
	
	public static JLabel getValueLabel(Attribute attribute) {
		if (attribute.getType() == Attribute.Type.BOOLEAN) {
			return new JLabel(new Boolean(((BooleanAttribute) attribute).getAttribute()).toString());
		}
		else if (attribute.getType() == Attribute.Type.DOUBLE) {
			return new JLabel(new Double(((DoubleAttribute) attribute).getAttribute()).toString());
		}
		else if (attribute.getType() == Attribute.Type.GROUPING) {
			return new JLabel(((GroupingAttribute<?>) attribute).getTitle());
		}
		else if (attribute.getType() == Attribute.Type.INT) {
			return new JLabel(new Integer(((IntAttribute) attribute).getAttribute()).toString());
		}
		else if (attribute.getType() == Attribute.Type.STRING) {
			return new JLabel(((StringAttribute) attribute).getAttribute());
		}
		else if (attribute.getType() == Attribute.Type.UNIT) {
			return new JLabel(((UnitAttribute) attribute).att.getName());
		}
		return null;
	}
	
}
