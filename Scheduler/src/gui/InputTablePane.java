package gui;

import middleend.*;
import backbone.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class InputTablePane extends JScrollPane implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JTable _table;
	
	public InputTablePane(MiddleEnd middleEnd, List<Attribute> headers, List<Unit> units) {
		_middleEnd = middleEnd;
		this.initialize(headers, units);
	}
	
	public JTable getTable() {
		return _table;
	}
	
	private void initialize(List<Attribute> headers, List<Unit> units) {
		this.setSize(INPUTTABLE_SIZE);
		_table = new JTable();
		_table.setFillsViewportHeight(true);
		//TODO take care of this: _table.setAutoCreateRowSorter(true);
		List<List<Attribute>> data = (List<List<Attribute>>) new ArrayList<List<Attribute>>();
		for (Unit u : units) {
			data.add(u.getAttributes());
		}
		_table.setModel(new InputTableModel(headers, data));
		ExcelAdapter exceladapt = new ExcelAdapter(_table);
		this.add(_table.getTableHeader());
		this.add(_table);
	}
	
	private class InputTableModel extends DefaultTableModel {
		private Attribute[] _headers;
		private Attribute[][] _data;
		
		public InputTableModel(List<Attribute> headers, List<List<Attribute>> data) {
			_headers = (Attribute[]) headers.toArray();
			List<Attribute[]> d = new ArrayList<Attribute[]>();
			for (List<Attribute> l : data) {
				d.add((Attribute[]) l.toArray());
			}
			_data = (Attribute[][]) d.toArray();
			this.setDataVector(_data, _headers);
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
