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
		_grouping = attribute.getMemberOf();
		_units = new ArrayList<Unit>(attribute.getListOfUnits());
		ArrayList<String> unitnames = new ArrayList<String>();
		for (Unit unit : _units) {
			unitnames.add(unit.getName());
		}
		_units.add(0, null);
		unitnames.add(0, "");
		this.setEditable(true);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((getSelectedIndex() < 0) && (getSelectedItem() != null)) {
					Unit toadd = attribute.getMemberOf().getBlank();
					toadd.setName((String) getSelectedItem());
					attribute.getMemberOf().addMember(toadd);
					_units.add(toadd);
					addItem(getSelectedItem());
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
