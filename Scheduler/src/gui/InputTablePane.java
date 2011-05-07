package gui;

import middleend.*;
import backbone.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

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
//		_table.setAutoCreateRowSorter(true);
		List<List<Attribute>> data = (List<List<Attribute>>) new ArrayList<List<Attribute>>();
		for (Unit u : group.getMembers()) {
			data.add(u.getAttributes());
		}
		for (int i = 0; i < DEFAULT_NUM_BLANK_ROWS; i++) {
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
						row.add(((BooleanAttribute) attr).getAttribute());
					}
					else if (attr.getType() == Attribute.Type.DOUBLE) {
						row.add(((DoubleAttribute) attr).getAttribute());
					}
					else if (attr.getType() == Attribute.Type.INT) {
						row.add(((IntAttribute) attr).getAttribute());
					}
					else if (attr.getType() == Attribute.Type.STRING) {
						row.add(((StringAttribute) attr).getAttribute());
					}
				}
				d.add(row.toArray(new Object[0]));
			}
			Object[][] dataarray = d.toArray(new Object[0][0]);
			this.setDataVector(dataarray, _headers);
		}
		
		public boolean isCellEditable(int row, int column) {
			return _editable;
		}
		
		public String getColumnName(int i) {
			return _headers[i].getTitle();
		}
		
//		public Class<?> getColumnClass(int i) {
//			Attribute a = _headers[i];
//			if (a.getType() == Attribute.Type.BOOLEAN) {
//				return boolean.class;
//			}
//			else if (a.getType() == Attribute.Type.DOUBLE) {
//				return double.class;
//			}
//			else if (a.getType() == Attribute.Type.INT) {
//				return int.class;
//			}
//			return String.class;
//		}

		public int getColumnCount() {
			return _headers.length;
		}
	}
}