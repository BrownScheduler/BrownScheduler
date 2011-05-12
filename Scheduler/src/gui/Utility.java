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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This is a utility class created for convenience. It has
 * several methods that create GUI elements, abstracting
 * out some of the default work for components that represent
 * attributes and their values.
 */
public class Utility implements GUIConstants {

	/**
	 * Returns a label with the given string, formatted as a
	 * Header 1, with a border if specified so.
	 * @param s
	 * @param border
	 * @return JLabel
	 */
	public static JLabel getHeader1Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		}
		return label;
	}
	
	/**
	 * Returns a label with the given string, formatted as a
	 * Header 2, with a border if specified so.
	 * @param s
	 * @param border
	 * @return JLabel
	 */
	public static JLabel getHeader2Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		if (border) {
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		}
		return label;
	}
	
	/**
	 * Returns a label with the given string, formatted as a
	 * Header 3, with a border if specified so.
	 * @param s
	 * @param border
	 * @return JLabel
	 */
	public static JLabel getHeader3Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
		}
		return label;
	}
	
	/**
	 * Returns a label with the given string, formatted as a
	 * Header 4, with a border if specified so.
	 * @param s
	 * @param border
	 * @return JLabel
	 */
	public static JLabel getHeader4Label(String s, boolean border) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		if (border) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		}
		return label;
	}
	
	/**
	 * Returns a field appropriate to the type of the given attribute.
	 * 
	 * @param attribute
	 * @return JComponent
	 */
	public static JComponent getField(Attribute attribute) {
		return getField(attribute, attribute.isEditable());
	}
	
	/**
	 * Returns a field appropriate to the type of the given attribute,
	 * which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JComponent
	 */
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
	
	/**
	 * Returns a textfield appropriate to the given StringAttribute,
	 * which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JTextField
	 */
	public static JTextField getStringField(StringAttribute attribute, boolean isEditable) {
		JTextField field = new JTextField(attribute.getAttribute());
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(isEditable);
		if (!isEditable)
			field.setBackground(UNEDITABLE_COLOR);
		return field;
	}
	
	/**
	 * Returns a formatted textfield appropriate to the given
	 * IntAttribute, which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JFormattedTextField
	 */
	public static JFormattedTextField getIntegerField(IntAttribute attribute, boolean isEditable) {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(nf);
		field.setValue(attribute.getAttribute());
		field.setPreferredSize(TEXTFIELD_SIZE);
		field.setMaximumSize(TEXTFIELD_SIZE);
		field.setEditable(isEditable);
		if (!isEditable)
			field.setBackground(UNEDITABLE_COLOR);
		return field;
	}
	
	/**
	 * Returns a formatted textfield appropriate to the given
	 * DoubleAttribute, which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JFormattedTextField
	 */
	public static JFormattedTextField getDoubleField(DoubleAttribute attribute, boolean isEditable) {
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);
		JFormattedTextField field = new JFormattedTextField(df);
		field.setPreferredSize(TEXTFIELD_SIZE);
		//field.setMinimumSize(new Dimension(TEXTFIELD_SIZE.width, TEXTFIELD_SIZE.height + 10));
		//field.setMaximumSize(TEXTFIELD_SIZE);
		field.setValue(attribute.getAttribute());
		field.setEditable(isEditable);
		if (!isEditable)
			field.setBackground(UNEDITABLE_COLOR);
		field.setMaximumSize(new Dimension(100, 25));
		return field;
	}
	
	/**
	 * Returns a checkbox appropriate to the given
	 * BooleanAttribute, which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JCheckBox
	 */
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
		if (!isEditable)
			field.setBackground(UNEDITABLE_COLOR);
		return field;
	}
	
	/**
	 * Returns a button (which should open a table) appropriate to the given
	 * GroupingAttribute, which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JButton
	 */
	public static JButton getGroupingField(GroupingAttribute<?> attribute, boolean isEditable) {
		JButton button = new JButton("Edit " + attribute.getTitle() + "...");
		if (IMAGESON)
			button.setIcon(EDITBUTTONIMAGE);
		if (COLORSON) {
			button.setBackground(BACKGROUND_COLOR);
			button.setForeground(FOREGROUND_COLOR);
		}
		return button;
	}
	
	/**
	 * Returns a combobox appropriate to the given
	 * UnitAttribute, which is editable if specified so.
	 * @param attribute
	 * @param isEditable
	 * @return JComponent
	 */
	public static UnitAttributeComboBox getUnitField(UnitAttribute<Unit> attribute, boolean isEditable) {
		final UnitAttributeComboBox combobox = new UnitAttributeComboBox(attribute);
		if (!isEditable)
			combobox.setBackground(UNEDITABLE_COLOR);
		return combobox;
	}
	
	/**
	 * Returns a label with the title of the given attribute.
	 * 
	 * @param attribute
	 * @return JLabel
	 */
	public static JLabel getTitleLabel(Attribute attribute) {
		return new JLabel(attribute.getTitle());
	}
	
	/**
	 * Returns a label with the value of the given attribute.
	 * 
	 * @param attribute
	 * @return JLabel
	 */
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
			if(((UnitAttribute) attribute).att == null) return new JLabel("");
			return new JLabel(((UnitAttribute) attribute).att.getName());
		}
		return null;
	}
	
	/**
	 * Returns a JPanel with the specified component wrapped leftwards.
	 * 
	 * @param toWrap
	 * @return JPanel
	 */
	public static JPanel wrapLeft(JComponent toWrap){
		JPanel lilWrapper = new JPanel();
		lilWrapper.setLayout(new BoxLayout(lilWrapper, BoxLayout.X_AXIS));
		if (COLORSON) {
			lilWrapper.setBackground(BACKGROUND_COLOR);
			lilWrapper.setForeground(FOREGROUND_COLOR);
		}
		lilWrapper.add(toWrap);
		lilWrapper.add(Box.createHorizontalGlue());
		return lilWrapper;
	}
	
	/**
	 * Returns a JPanel with the specified component wrapped upwards.
	 * 
	 * @param toWrap
	 * @return JPanel
	 */
	public static JPanel wrapUp(JComponent toWrap){
		JPanel lilWrapper = new JPanel();
		lilWrapper.setLayout(new BoxLayout(lilWrapper, BoxLayout.Y_AXIS));
		if (COLORSON) {
			lilWrapper.setBackground(BACKGROUND_COLOR);
			lilWrapper.setForeground(FOREGROUND_COLOR);
		}
		lilWrapper.add(toWrap);
		lilWrapper.add(Box.createVerticalGlue());
		return lilWrapper;
	}
	
	/**
	 * Mixes the two colors with the specified ratio, returning the resulting
	 * color.
	 * 
	 * @param color1
	 * @param color2
	 * @param color1ratio
	 * @return Color
	 */
	public static Color mixColors(Color color1, Color color2, float color1ratio) {
		color1ratio = Math.max(color1ratio, 1);
		color1ratio = Math.min(color1ratio, -1);
		int red = (int) ((color1.getRed() * color1ratio) + (color2.getRed() * (1 - color1ratio)));
		int blue = (int) ((color1.getBlue() * color1ratio) + (color2.getBlue() * (1 - color1ratio)));
		int green = (int) ((color1.getGreen() * color1ratio) + (color2.getGreen() * (1 - color1ratio)));
		return new Color(red, blue, green);
	}
	
}
