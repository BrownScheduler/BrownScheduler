package gui;

import backbone.Unit;
import backbone.*;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnitAttributeComboBox extends JComboBox implements GUIConstants {
	
	private Unit _selectedunit;
	private Grouping<Unit> _grouping;
	private ArrayList<Unit> _units;
	
	public UnitAttributeComboBox(final UnitAttribute<Unit> attribute) {
		this.setSize(JCOMBOBOX_SIZE);
		this.setMaximumSize(this.getSize());
		this.setToolTipText("Create a new team by typing a name instead of selecting one from the drop-down box");
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		_grouping = attribute.getMemberOf();
		_units = new ArrayList<Unit>(attribute.getListOfUnits());
		ArrayList<String> unitnames = new ArrayList<String>();
		this.addItem("");
		int toSelect = -1;
		for (int i = 0; i < _units.size(); i++) {
			unitnames.add(_units.get(i).getName());
			this.addItem(_units.get(i).getName());
			if (_units.get(i) == attribute.att)
				toSelect = i+1;
		}
		_units.add(0, null);
		if (attribute.att == null)
			toSelect = 0;
		this.setSelectedIndex(toSelect);
		this.setEditable(true);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((getSelectedIndex() < 0) && (getSelectedItem() != null)) {
					Unit toadd = attribute.getMemberOf().getBlank();
					toadd.setName((String) getSelectedItem());
					_selectedunit = toadd;
				}
				else {
					_selectedunit = _units.get(getSelectedIndex());
				}
			}
		});
	}
	
	public Unit getSelectedUnit() {
		return _selectedunit;
	}
	
	public Grouping<Unit> getGrouping() {
		return _grouping;
	}
}
