package gui;

import middleend.*;
import backbone.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

public class AddingPanel extends JPanel implements GUIConstants {

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
	private void initialize() {
		initLabel = new JLabel();
		initLabel.setText("Choose a category on the side to begin editing!");
		initLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setSize(ADDINGPANEL_SIZE); //TODO: make constants
		this.setLayout(new BorderLayout());
		this.add(initLabel, BorderLayout.CENTER);
		
		/**
		this.setSize(400, 400);
		this.removeAll();
		this.setLayout(new SpringLayout());
		
		int xspacing = 20;
		int yspacing = 10;
		int cols = 3 + 1;
		int rows = 5 + 2;
		
		this.add(this.getHeader1Label("School", true));
		for (int i = 1; i < cols; i++)
			this.add(this.getHeader2Label("Attribute " + i, true));
		this.add(this.getHeader3Label("Brown", true));
		for (int i = 1; i < cols; i++)
			this.add(this.getDoubleField(new backbone.DoubleAttribute("SomeAtt", 1.03)));
		for (int i = 2; i < rows; i++) {
			JLabel category = this.getHeader4Label("Name of Category " + (i-1) + ":", true);
			category.addMouseListener(new MouseInputAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					//TODO link to category
				}
			});
			this.add(category);
			for (int j = 1; j < cols; j++) {
				JLabel unit = new JLabel("Unit " + j + " of Category " + (i-1));
				this.setLink(unit);
				unit.addMouseListener(new MouseInputAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						//TODO link to unit
					}
				});
				this.add(unit);
			}
			
		}
		
		SpringUtilities.makeCompactGrid(this, rows, cols, xspacing, yspacing, xspacing, yspacing);
		**/
		/** //TODO: Table?
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
		**/
	}
	
	public void setViewPanel(Unit unit) {
		this.removeAll();
		this.add(new UnitPanel(_middleEnd, unit));
	}
	
	public void setViewPanel(Grouping<Unit> grouping) {
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for (Unit u : grouping.getMembers()) {
			this.add(new UnitPanel(_middleEnd, u));
			this.add(Box.createRigidArea(new Dimension(10, 10)));
		}
		/**
		this.setLayout(new SpringLayout());
		
		int spacing = 10;
		Collection<Attribute> attributes = grouping.getAttributes();
		Collection<? extends Unit> units = grouping.getMembers();
		int cols = attributes.size() + 1;
		int rows = _middleEnd.getNumAttributeCategories() + 2;
		
		this.add(this.getHeader1Label(grouping.name, true));
		for (Attribute attr : attributes) {
			this.add(new JLabel(attr.getTitle()));
		}
		this.add(this.getHeader2Label(grouping.name, true));
		for (int i = 1; i < cols; i++) {
			this.add(this.getHeader3Label(attributes of GroupingGroup));
			this.setLink(label);
			category.addMouseListener(new MouseInputAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					// TODO Link to category
				}
			});
		}
		for (int i = 2; i < rows; i++) {
			//this.add(new JLabel(name of Grouping));
			for (int j = 1; j < cols; j++) {
				this.add(units of grouping with this attribute);
				this.setLink(unit);
				unit.addMouseListener(new MouseInputAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						// TODO Link to unit
					}
				});
			}
			
		}
		
		SpringUtilities.makeCompactGrid(this, rows, cols, spacing, spacing, spacing, spacing);
		**/
	}
	
	public void setAddPanel(Grouping<Unit> grouping) {
		this.removeAll();
		//TODO
	}
}
