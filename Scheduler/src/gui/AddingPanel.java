package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import middleend.MiddleEnd;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class AddingPanel extends JPanel {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private JLabel initLabel = null;

	/**
	 * This is the default constructor
	 */
	public AddingPanel(MiddleEnd m) {
		super();
		_middleEnd = m;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		/**
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		initLabel = new JLabel();
		initLabel.setText("Choose a category on the side to begin editing!");
		this.setSize(400, 400); //TODO: make constants
		this.setLayout(new GridBagLayout());
		this.add(initLabel, gridBagConstraints);
		**/
		/**
		this.setSize(400, 400);
		this.removeAll();
		this.setLayout(new SpringLayout());
		
		int xspacing = 20;
		int yspacing = 10;
		int cols = 3 + 1;
		int rows = 20 + 2;
		
		this.add(this.getHeader1Label("School"));
		for (int i = 1; i < cols; i++)
			this.add(this.getHeader2Label("Attribute " + i));
		this.add(this.getHeader3Label("Brown"));
		for (int i = 1; i < cols; i++)
			this.add(this.getFloatField(i, true));
		for (int i = 2; i < rows; i++) {
			this.add(new JLabel("Name of Competitive Unit " + (i-1) + ":"));
			for (int j = 1; j < cols; j++) {
				this.add(new JTextField("Att " + j + " of Unit " + (i-1)));
			}
		}
		
		SpringUtilities.makeCompactGrid(this, rows, cols, xspacing, yspacing, xspacing, yspacing);
		**/
		this.setSize(400, 400);
		this.removeAll();
		this.setLayout(new BorderLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"1", "2"});
		model.setRowCount(20);
		
		JTable table = new JTable(model);
		table.setSize(this.getSize());
		table.setFillsViewportHeight(true);
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);
	}
	
	public void setPanel(GUIAttribute attribute) {
		
	}
	
	public void setPanel(GUICompetitiveUnit competitiveunit) {
		
	}
	
	public void setPanel(GUIGroupingAttribute groupingattribute) {
		
	}
	
	public void setPanel(GUIGrouping grouping) {
		this.removeAll();
		this.setLayout(new SpringLayout());
		
		int spacing = 10;
		int cols = _middleEnd.getNumAttributeCategories() + 1;
		int rows = _middleEnd.getCompunitsInGrouping(grouping).size() + 2;
		
		this.add(this.getHeader1Label(grouping.getName()));
		for (int i = 1; i < cols; i++)
			{} //this.add(new JLabel(name of attribute));
		this.add(this.getHeader2Label(grouping.getGroup().getName()));
		for (int i = 1; i < cols; i++)
			{} //this.add(this.getHeader3Label(attributes of GroupingGroup));
		for (int i = 2; i < rows; i++) {
			//this.add(new JLabel(name of Competitive Unit));
			for (int j = 1; j < cols; j++) {
				//this.add(attributes of CompetitiveUnit);
			}
		}
		
		SpringUtilities.makeCompactGrid(this, rows, cols, spacing, spacing, spacing, spacing);
	}
	

	public void setGroupingPanel() {
		
	}
	
	public void setCompetitiveUnitPanel() {
		
	}
	
	private JLabel getHeader1Label(String s) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		return label;
	}
	
	private JLabel getHeader2Label(String s) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		return label;
	}
	
	private JLabel getHeader3Label(String s) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
		return label;
	}
	
	private JTextField getStringField(String val, boolean editable) {
		JTextField field = new JTextField(val);
		field.setEditable(editable);
		return field;
	}
	
	private JFormattedTextField getIntegerField(int val, boolean editable) {
		JFormattedTextField field = new JFormattedTextField(NumberFormat.getIntegerInstance());
		field.setValue(val);
		field.setEditable(editable);
		return field;
	}
	
	private JFormattedTextField getFloatField(float val, boolean editable) {
		JFormattedTextField field = new JFormattedTextField(new DecimalFormat());
		field.setValue(val);
		field.setEditable(editable);
		return field;
	}
	
	private JCheckBox getBooleanField(final boolean val, boolean editable) {
		final JCheckBox field = new JCheckBox();
		field.setSelected(val);
		if (!editable) {
			field.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					field.setSelected(val);
				}
			});
		}
		return field;
	}

}
