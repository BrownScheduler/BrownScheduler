package gui;

import backbone.Unit;
import backbone.*;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the combobox that allows a user to edit a UnitAttribute
 * of a unit.
 */
public class UnitAttributeComboBox extends JComboBox implements GUIConstants {
	
	private Unit _selectedunit;
	private Grouping<Unit> _grouping;
	private ArrayList<Unit> _units;
	
	/**
	 * Constructor.
	 * @param attribute
	 */
	public UnitAttributeComboBox(final UnitAttribute<Unit> attribute) {
		this.setPreferredSize(JCOMBOBOX_SIZE);
		this.setMaximumSize(JCOMBOBOX_SIZE);
		this.setToolTipText("Create a new team by typing a name instead of selecting one from the drop-down box");
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		_grouping = attribute.getMemberOf();
		_units = new ArrayList<Unit>(attribute.getListOfUnits());
		ArrayList<String> unitnames = new ArrayList<String>();
		this.addItem("");
		// Preselects the existing value of the UnitAttribute
		int toSelect = -1;
		for (int i = 0; i < _units.size(); i++) {
			unitnames.add(_units.get(i).getName());
			this.addItem(_units.get(i).getName());
			if (_units.get(i) == attribute.getAttribute())
				toSelect = i+1;
		}
		_units.add(0, null);
		if (attribute.getAttribute() == null)
			toSelect = 0;

		// This combo box is editable
		this.setEditable(true);
		// Sets the value of getSelectedUnit() of this combobox to return the selected value
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getSelectedIndex() < 0) && (getSelectedItem() != null) && (!((String) getSelectedItem()).trim().equals(""))) {
					Unit toadd = attribute.getMemberOf().getBlank();
					toadd.setName((String) getSelectedItem());
					_selectedunit = toadd;
				}
				else {
					_selectedunit = _units.get(getSelectedIndex());
				}
			}
		});
		this.setSelectedIndex(toSelect);
	}
	
	/**
	 * Returns the selected unit in this combo box.
	 * 
	 * @return Unit
	 */
	public Unit getSelectedUnit() {
		return _selectedunit;
	}
	
	/**
	 * Returns the grouping that this combo box contains
	 * units of.
	 * 
	 * @return Grouping<Unit>
	 */
	public Grouping<Unit> getGrouping() {
		return _grouping;
	}
}
