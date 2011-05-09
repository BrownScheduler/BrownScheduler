package gui;

import middleend.*;
import backbone.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class InputTablePane extends JScrollPane implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JTable _table;
	private GroupingAttribute<Unit> _unitsInRows;
	
	public InputTablePane(MiddleEnd middleEnd, List<Attribute> headers, GroupingAttribute<Unit> group) {
		super(new JTable());
		for (int i = 0; i < this.getComponentCount(); i++) {
			if (this.getComponent(i) instanceof JTable) {
				_table = (JTable) this.getComponent(i);
				break;
			}
		}
		_middleEnd = middleEnd;
		_unitsInRows = group;
		this.initialize(headers, group);
	}
	
	public JTable getTable() {
		return _table;
	}
	
	private void initialize(List<Attribute> headers, GroupingAttribute<Unit> group) {
		this.setSize(INPUTTABLE_SIZE);
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getHeight()));
		_table = new JTable();
		_table.setSize(INPUTTABLE_SIZE);
		_table.getTableHeader().setReorderingAllowed(false);
		List<List<Attribute>> data = (List<List<Attribute>>) new ArrayList<List<Attribute>>();
		for (Unit u : group.getMembers()) {
			data.add(u.getAttributes());
		}
		for (int i = 0; i < DEFAULT_TABLE_BLANK_ROWS; i++) {
			data.add(group.getBlankUnit().getAttributes());
		}
		_table.setModel(new InputTableModel(headers, data, group.isEditable()));
		ExcelAdapter exceladapt = new ExcelAdapter(_table);
		this.add(_table.getTableHeader());
		this.add(_table);
	}
	
	public List<Unit> getUnitsInRowsList() {
		return _unitsInRows.getMembers();
	}
	
	private class InputTableModel extends DefaultTableModel {
		private Attribute[] _headers;
		private boolean _editable;
		
		public InputTableModel(List<Attribute> headers, List<List<Attribute>> data, boolean editable) {
			_editable = editable;
			_headers = headers.toArray(new Attribute[0]);
			List<Object[]> d = new ArrayList<Object[]>();
			for (List<Attribute> list : data) {
				ArrayList<Object> row = new ArrayList<Object>();
				for (Attribute attr : list) {
					if (attr.getType() == Attribute.Type.BOOLEAN) {
						row.add(new Boolean(((BooleanAttribute) attr).getAttribute()));
					}
					else if (attr.getType() == Attribute.Type.DOUBLE) {
						row.add(new Double(((DoubleAttribute) attr).getAttribute()));
					}
					else if (attr.getType() == Attribute.Type.INT) {
						row.add(new Integer(((IntAttribute) attr).getAttribute()));
					}
					else if (attr.getType() == Attribute.Type.STRING) {
						row.add(((StringAttribute) attr).getAttribute());
					}
					else if (attr.getType() == Attribute.Type.UNIT) {
						if ((((UnitAttribute) attr).att) == null) {
							row.add("");
						}
						else {
							row.add(((UnitAttribute) attr).att.getName());
						}
					}
				}
				d.add(row.toArray(new Object[0]));
			}
			Object[][] dataarray = d.toArray(new Object[0][0]);
			this.setDataVector(dataarray, _headers);
			for (int i = 0; i < _table.getColumnCount(); i++) {
				if (_headers[i].getType() == Attribute.Type.UNIT) {
					TableColumn unitcolumn = _table.getColumnModel().getColumn(i);
					UnitAttribute<Unit> header = (UnitAttribute) _headers[i];
					UnitAttribute<Unit> uatt = new UnitAttribute<Unit>(header.getTitle(), header.getMemberOf());
					UnitAttributeComboBox combobox = new UnitAttributeComboBox(uatt);
					unitcolumn.setCellEditor(new DefaultCellEditor(combobox));
				}
			}
			this.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if ((e.getLastRow() == (getRowCount()-1)) && (e.getType() == TableModelEvent.UPDATE)) {
						for (int i = 0; i < DEFAULT_TABLE_BLANK_ROWS; i++) {
							insertRow(getRowCount(), new Object[0]);
						}
					}
				}
			});
		}
		
		public boolean isCellEditable(int row, int column) {
			return _editable;
		}
		
		public String getColumnName(int i) {
			return _headers[i].getTitle();
		}
		
		public Class<?> getColumnClass(int i) {
			Attribute a = _headers[i];
			if (a.getType() == Attribute.Type.BOOLEAN) {
				return Boolean.class;
			}
			else if (a.getType() == Attribute.Type.DOUBLE) {
				return Double.class;
			}
			else if (a.getType() == Attribute.Type.INT) {
				return Integer.class;
			}
			else if (a.getType() == Attribute.Type.STRING) {
				return String.class;
			}
			return Object.class;
		}

		public int getColumnCount() {
			return _headers.length;
		}
	}
}
