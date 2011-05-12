package gui;

import backbone.*;
import middleend.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This displays the attributes of a unit and allows the user
 * to edit them.
 */
public class UnitPanel extends JPanel implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JPanel _mainPanel, _buttonPanel, _tablePanel;
	private Grouping<Unit> _grouping;
	
	/**
	 * Constructor when viewing an existing unit.
	 * @param m
	 * @param u
	 */
	public UnitPanel(MiddleEnd m, Unit u) {
		_middleEnd = m;
		_grouping = u.getMemberOf();
		_mainPanel = new JPanel();
		_buttonPanel = new JPanel();
		_tablePanel = new JPanel();
		initialize(u, "Save Changes", "Delete this unit");
	}
	
	/**
	 * Constructor when adding a new unit.
	 * @param m
	 * @param u
	 * @param g
	 */
	public UnitPanel(MiddleEnd m, Unit u, Grouping<Unit> g) {
		_middleEnd = m;
		_grouping = g;
		_mainPanel = new JPanel();
		_buttonPanel = new JPanel();
		_tablePanel = new JPanel();
		initialize(u, "Save Changes and Add Another New Unit", "Clear values");
	}
	
	/**
	 * This initializes this.
	 * @param unit
	 * @param savestring
	 * @param deletestring
	 */
	public void initialize(final Unit unit, String savestring, final String deletestring) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
			_mainPanel.setBackground(BACKGROUND_COLOR);
			_mainPanel.setForeground(FOREGROUND_COLOR);
			_buttonPanel.setBackground(BACKGROUND_COLOR);
			_buttonPanel.setForeground(FOREGROUND_COLOR);
			_tablePanel.setBackground(BACKGROUND_COLOR);
			_tablePanel.setForeground(FOREGROUND_COLOR);
		}
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.X_AXIS));
		_tablePanel.setLayout(new BoxLayout(_tablePanel, BoxLayout.Y_AXIS));
		final HashMap<Attribute, JComponent> components = new HashMap<Attribute, JComponent>();
		final ArrayList<Attribute> originalattributes = new ArrayList<Attribute>();
		// Loops through every attribute in the Unit, adding the appropriate field
		for (final Attribute attr : unit.getAttributes()) {
			originalattributes.add(attr);
			JLabel titleLabel = Utility.getTitleLabel(attr);
			if (attr instanceof GroupingAttribute) {
				GroupingAttribute<Unit> g = (GroupingAttribute<Unit>) attr;
				if (null == components.put(attr, new InputTablePane(_middleEnd, g.getBlankUnit().getAttributes(), g))) {
					_tablePanel.add(components.get(attr));
					_tablePanel.setVisible(false);
				}
			}
			JComponent comp = Utility.getField(attr);
			// If getField() returns a button, then an InputTablePane needs to be added to it.
			if (comp instanceof JButton) {
				((JButton) comp).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < _tablePanel.getComponentCount(); i++) {
							if (_tablePanel.getComponent(i) instanceof InputTablePane) {
								InputTablePane pane = (InputTablePane) _tablePanel.getComponent(i);
								if (pane == components.get(attr)) {
									pane.setVisible(!pane.isVisible());
									_tablePanel.setVisible(pane.isVisible());
								}
								else {
									pane.setVisible(false);
								}
								repaint();
							}
						}
						_tablePanel.repaint();
					}
				});
			}
			if (!components.containsKey(attr))
				components.put(attr, comp);
			JPanel toAdd = new JPanel();
			toAdd.setLayout(new BoxLayout(toAdd, BoxLayout.Y_AXIS));
			toAdd.add(titleLabel);
			toAdd.add(comp);
			if (COLORSON) {
				toAdd.setBackground(BACKGROUND_COLOR);
				toAdd.setForeground(FOREGROUND_COLOR);
			}
			_mainPanel.add(toAdd);
		}
		JButton savebutton = new JButton(savestring);
		if (IMAGESON)
			savebutton.setIcon(SAVEBUTTONIMAGE);
		if (COLORSON) {
			savebutton.setBackground(BACKGROUND_COLOR);
			savebutton.setForeground(FOREGROUND_COLOR);
		}
		// When save is clicked, this loops through all the fields,
		// saving the values of each field. A second loop is inside
		// for GroupingAttributes
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection<Attribute> attributes = components.keySet();
				int repaint = 0;
				for (Attribute attr : attributes) {
					if (attr.getType() == Attribute.Type.BOOLEAN) {
						boolean value = ((JCheckBox) components.get(attr)).isSelected();
						unit.setAttribute(new BooleanAttribute(attr.getTitle(), value));
					}
					else if (attr.getType() == Attribute.Type.DOUBLE) {
						double value = Double.parseDouble(((JTextField) components.get(attr)).getText());
						unit.setAttribute(new DoubleAttribute(attr.getTitle(), value));
					}
					else if (attr.getType() == Attribute.Type.GROUPING) {
						InputTablePane table = (InputTablePane) components.get(attr);
						GroupingAttribute<Unit> groupattr = (GroupingAttribute) attr;
						HashMap<Unit, Boolean> unitsintable = new HashMap<Unit, Boolean>();
						ArrayList<Unit> unitsinrowslist = new ArrayList<Unit>();
						for (int i = 0; i < table.getTable().getRowCount(); i++) {
							Unit rowunit;
							if (i < table.getUnitsInRowsList().size()) {
								rowunit = table.getUnitsInRowsList().get(i);
								unitsinrowslist.add(rowunit);
								unitsintable.put(rowunit, true);
							}
							else {
								rowunit = groupattr.getBlankUnit();
							}
							if (!unitsintable.containsKey(rowunit))
								unitsintable.put(rowunit, false);
							int j = 0;
							for (Attribute rowattr : rowunit.getAttributes()) {
								if (rowattr.getType() == Attribute.Type.BOOLEAN) {
									boolean value = false;
									if (table.getTable().getValueAt(i, j) != null) {
										value = (Boolean) table.getTable().getValueAt(i, j);
									}
									rowunit.setAttribute(new BooleanAttribute(rowattr.getTitle(), value));
								}
								else if (rowattr.getType() == Attribute.Type.DOUBLE) {
									double value = 0;
									if (table.getTable().getValueAt(i, j) != null) {
										value = (Double) table.getTable().getValueAt(i, j);
									}
									rowunit.setAttribute(new DoubleAttribute(rowattr.getTitle(), value));
								}
								else if (rowattr.getType() == Attribute.Type.INT) {
									int value = 0;
									if (table.getTable().getValueAt(i, j) != null) {
										value = (Integer) table.getTable().getValueAt(i, j);
									}
									rowunit.setAttribute(new IntAttribute(rowattr.getTitle(), value));
								}
								else if (rowattr.getType() == Attribute.Type.STRING) {
									String value = "";
									if (table.getTable().getValueAt(i, j) != null) {
										value = (String) table.getTable().getValueAt(i, j);
									}
									rowunit.setAttribute(new StringAttribute(rowattr.getTitle(), value));
								}
								else if ((rowattr.getType() == Attribute.Type.UNIT) || (rowattr.getType() == Attribute.Type.GROUPING)) {
									j--;
								}
//								else if (rowattr.getType() == Attribute.Type.UNIT) {
//									DefaultCellEditor editor = (DefaultCellEditor) table.getTable().getCellEditor(i, j);
//									UnitAttributeComboBox combobox = (UnitAttributeComboBox) editor.getComponent();
//									Grouping<Unit> g = combobox.getGrouping();
//									Unit value = null;
//									if (combobox.getSelectedUnit() != null) {
//										value = combobox.getSelectedUnit();
//										if ((value != null) && (!value.getName().trim().equals("")) && (!g.getMembers().contains(value)))
//											g.addMember(value);
//									}
//									System.out.println(combobox.getSelectedIndex());
//									rowunit.setAttribute(new UnitAttribute<Unit>(rowattr.getTitle(), value, g));
//								}
								j++;
							}
						}
						// Duplicate and null handling
						for (Unit rowunit : unitsintable.keySet()) {
							for (Unit rowunit2 : unitsintable.keySet()) {
								if ((rowunit != rowunit2) && (rowunit.getName().equals(rowunit2.getName())) && (!rowunit.getName().equals(""))) {
									repaint = 1;
								}
							}
						}
						for (Unit rowunit : unitsinrowslist) {
							if (rowunit.getName().equals(""))
								repaint = 2;
						}
						for (int i = 0; i < unitsinrowslist.size(); i++) {
							if (unitsintable.get(unitsinrowslist.get(i))) {
								Unit duplicate = groupattr.getBlankUnit().getMemberOf().getDuplicate(unitsinrowslist.get(i));
								if ((unitsinrowslist.get(i) != duplicate) && (duplicate != null)) {
									repaint = 3;
								}
							}
						}
						if (repaint == 0) {
							for (Unit rowunit : unitsintable.keySet()) {
								Unit duplicate = groupattr.getBlankUnit().getMemberOf().getDuplicate(rowunit);
								boolean rowunitisnull = false;
								if ((rowunit.getName() == null) || (rowunit.getName().equals("")))
									rowunitisnull = true;
								if (!rowunitisnull && !unitsintable.get(rowunit)) {
									groupattr.addMember(rowunit);
									if (duplicate == null)
										groupattr.getBlankUnit().getMemberOf().addMember(rowunit);
								}
								else if (!rowunitisnull && !unitsintable.get(rowunit)) {
									for (Attribute a : rowunit.getAttributes()) {
										if (a.getType() != Attribute.Type.GROUPING)
											duplicate.setAttribute(a);
									}
								}
								else {
									
								}
							}
							unit.setAttribute(groupattr);
							table = new InputTablePane(_middleEnd, groupattr.getBlankUnit().getAttributes(), groupattr);
							components.put(attr, table);
						}
					}
					else if (attr.getType() == Attribute.Type.INT) {
						int value = Integer.parseInt(((JTextField) components.get(attr)).getText());
						unit.setAttribute(new IntAttribute(attr.getTitle(), value));
					}
					else if (attr.getType() == Attribute.Type.STRING) {
						String value = ((JTextField) components.get(attr)).getText();
						unit.setAttribute(new StringAttribute(attr.getTitle(), value));
					}
					else if (attr.getType() == Attribute.Type.UNIT) {
						Unit value = ((UnitAttributeComboBox) components.get(attr)).getSelectedUnit();
						Grouping grouping = ((UnitAttributeComboBox) components.get(attr)).getGrouping();
						if ((value != null) && (!grouping.getMembers().contains(value)))
							grouping.addMember(value);
						if (value != null)
							System.out.println(value.getName());
						unit.setAttribute(new UnitAttribute(attr.getTitle(), value, grouping));
					}
				}
				// Error message showing and adding of the actual attribute to the unit
				if (unit.getName().equals("")) {
					for (Attribute attr : originalattributes) {
						unit.setAttribute(attr);
					}
					repaint = 4;
					JOptionPane.showMessageDialog(_mainPanel, "The unit's name is blank! Change the name and try again.",
							"Nameless Unit", JOptionPane.ERROR_MESSAGE);
				}
				else if (_grouping.getDuplicate(unit) != null) {
					for (Attribute attr : originalattributes) {
						unit.setAttribute(attr);
					}
					repaint = 4;
					JOptionPane.showMessageDialog(_mainPanel, "A unit with the same name already exists. Change the name and try again.",
							"Duplicate Unit", JOptionPane.ERROR_MESSAGE);
				}
				else if (!_grouping.getMembers().contains(unit)) {
					_grouping.addMember(unit);
				}
				switch (repaint) {
				case 1:
					JOptionPane.showMessageDialog(_mainPanel, "There are two units in the table with the same name! Change the name and try again.",
							"Duplicate Units", JOptionPane.ERROR_MESSAGE);
					break;
				case 2:
					JOptionPane.showMessageDialog(_mainPanel, "One of the units that was in the table now has a blank name! Change the name and try again.",
							"Duplicate Units", JOptionPane.ERROR_MESSAGE);
					break;
				case 3:
					JOptionPane.showMessageDialog(_mainPanel, "One of the units that was in the table is a duplicate of an already existing unit! Change the name and try again.",
							"Duplicate Units", JOptionPane.ERROR_MESSAGE);
					break;
				case 4:
					break;
				default:
					_middleEnd.repaintAll();
				}
			}
		});
		// This button allows the user to delete this unit
		final JButton deletebutton = new JButton(deletestring);
		if (IMAGESON)
			deletebutton.setIcon(DELETEBUTTONIMAGE);
		if (COLORSON) {
			deletebutton.setBackground(BACKGROUND_COLOR);
			deletebutton.setForeground(FOREGROUND_COLOR);
		}
		// This button needs to be clicked twice, helps 
		// prevent accidental deletions
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deletebutton.getText().equals(deletestring))
					deletebutton.setText("Are you sure?");
				else {
					_mainPanel.removeAll();
					_buttonPanel.removeAll();
					_tablePanel.removeAll();
					unit.deleteFromGrouping();
					_middleEnd.repaintAll();
				}
			}
		});
		_buttonPanel.add(savebutton);
		_buttonPanel.add(deletebutton);
		_buttonPanel.setMaximumSize(UNITPANEL_SIZE);
		this.add(_mainPanel);
		this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		this.add(_buttonPanel);
		this.add(Box.createRigidArea(SMALLSPACING_SIZE));
		this.add(_tablePanel);
		this.add(Box.createVerticalGlue());
		this.repaint();
	}
}
