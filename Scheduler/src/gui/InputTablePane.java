package gui;

import middleend.*;
import backbone.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class InputTablePane extends JScrollPane implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JTable _table;
	private List<Unit> _unitsInRows;
	
	public InputTablePane(MiddleEnd middleEnd, List<Attribute> headers, List<Unit> units) {
		_middleEnd = middleEnd;
		_unitsInRows = units;
		this.initialize(headers, units);
	}
	
	public JTable getTable() {
		return _table;
	}
	
	private void initialize(List<Attribute> headers, List<Unit> units) {
		this.setSize(INPUTTABLE_SIZE);
		_table = new JTable();
		_table.setSize(INPUTTABLE_SIZE);
		_table.setFillsViewportHeight(true);
		_table.setAutoCreateColumnsFromModel(true);
		_table.setAutoCreateRowSorter(true);
		List<List<Attribute>> data = (List<List<Attribute>>) new ArrayList<List<Attribute>>();
		for (Unit u : units) {
			data.add(u.getAttributes());
		}
		_table.setModel(new InputTableModel(headers, data));
		ExcelAdapter exceladapt = new ExcelAdapter(_table);
		this.add(_table.getTableHeader());
		this.add(_table);
	}
	
	public List<Unit> getUnitsInRowsList() {
		return _unitsInRows;
	}
	
	private class InputTableModel extends DefaultTableModel {
		private Attribute[] _headers;
		private Attribute[][] _data;
		
		public InputTableModel(List<Attribute> headers, List<List<Attribute>> data) {
			_headers = headers.toArray(new Attribute[0]);
			List<Attribute[]> d = new ArrayList<Attribute[]>();
			for (List<Attribute> l : data) {
				d.add(l.toArray(new Attribute[0]));
			}
			_data = d.toArray(new Attribute[0][0]);
			this.setDataVector(_data, _headers);
			//TODO: Figure out how to insert blank rows
		}
		
		public String getColumnName(int i) {
			return _headers[i].getTitle();
		}
		
		public Class getColumnClass(int i) {
			Attribute a = _headers[i];
			if (a.getType() == Attribute.Type.BOOLEAN) {
				return boolean.class;
			}
			else if (a.getType() == Attribute.Type.DOUBLE) {
				return double.class;
			}
			else if (a.getType() == Attribute.Type.INT) {
				return int.class;
			}
			return String.class;
		}

		public int getColumnCount() {
			return _headers.length;
		}
	}
}
