package gui;

import middleend.*;
import backbone.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class InputTable extends JScrollPane implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JTable _table;
	
	public InputTable(MiddleEnd middleEnd, List<Attribute> headers, List<Unit> units) {
		_middleEnd = middleEnd;
		this.initialize(headers, units);
	}
	
	private void initialize(List<Attribute> headers, List<Unit> units) {
		this.setSize(INPUTTABLE_SIZE);
		_table = new JTable();
		_table.setFillsViewportHeight(true);
		_table.setAutoCreateRowSorter(true);
		List<List<Attribute>> data = (List<List<Attribute>>) new ArrayList<List<Attribute>>();
		for (Unit u : units) {
			
		}
		_table.setModel(new InputTableModel(headers, data));
		ExcelAdapter exceladapt = new ExcelAdapter(_table);
		this.add(_table.getTableHeader());
		this.add(_table);
	}
	
	private class InputTableModel extends AbstractTableModel {
		private List<Attribute> headers;
		private List<List<Attribute>> data;
		public InputTableModel(List<Attribute> headers2, List<List<Attribute>> d) {
			headers = headers2;
			data = d;
			this.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent arg0) {
					// TODO Auto-generated method stub
				}
			});
		}
		
		public String getColumnName(int i) {
			return headers.get(i).getTitle();
		}
		
		public Class getColumnClass(int i) {
			Attribute a = headers.get(i);
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
			return headers.size();
		}

		public int getRowCount() {
			return data.size();
		}
		
		public boolean isCellEditable(int x, int y) {
			Attribute a = data.get(x).get(y);
			return a.isEditable();
		}

		public Object getValueAt(int x, int y) {
			Attribute a = data.get(x).get(y);
			if (a.getType() == Attribute.Type.BOOLEAN) {
				return ((BooleanAttribute) a).getAttribute();
			}
			else if (a.getType() == Attribute.Type.DOUBLE) {
				return ((DoubleAttribute) a).getAttribute();
			}
			else if (a.getType() == Attribute.Type.INT) {
				return ((IntAttribute) a).getAttribute();
			}
			else if (a.getType() == Attribute.Type.STRING) {
				return ((StringAttribute) a).getAttribute();
			}
			return null;
		}
		
		public void setValueAt(Object value, int x, int y) {
			Attribute a = data.get(x).get(y);
			if ((a.getType() == Attribute.Type.BOOLEAN) && (value instanceof Boolean)) {
				((BooleanAttribute) a).setAttribute((Boolean) value);
			}
			else if ((a.getType() == Attribute.Type.DOUBLE) && (value instanceof Double)) {
				((DoubleAttribute) a).setAttribute((Double) value);
			}
			else if ((a.getType() == Attribute.Type.INT) && (value instanceof Integer)) {
				((IntAttribute) a).setAttribute((Integer) value);
			}
			else if ((a.getType() == Attribute.Type.STRING) && (value instanceof String)) {
				((StringAttribute) a).setAttribute((String) value);
			}
		}
		
	}
}
