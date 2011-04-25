package gui;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class InputPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	public AddingPanel addingPanel = null;
	public AttributeTree attributeTree = null;
	public JScrollPane attributeScrollPane = null;
	public JScrollPane addingScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public InputPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(600, 400); //TODO: make constants
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getAttributeScrollPane());
		this.add(getAddingScrollPane());
	}

	/**
	 * This method initializes AddingPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getAddingPanel() {
		if (addingPanel == null) {
			addingPanel = new AddingPanel();
		}
		return addingPanel;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getAttributeTree() {
		if (attributeTree == null) {
			attributeTree = new AttributeTree();
		}
		return attributeTree;
	}

	/**
	 * This method initializes attributePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getAttributeScrollPane() {
		if (attributeScrollPane == null) {
			attributeScrollPane = new JScrollPane(getAttributeTree());
			attributeScrollPane.setSize(getAttributeTree().getSize());
			attributeScrollPane.setMaximumSize(getAttributeTree().getMaximumSize());
		}
		return attributeScrollPane;
	}
	
	/**
	 * This method initializes attributePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getAddingScrollPane() {
		if (addingScrollPane == null) {
			addingScrollPane = new JScrollPane(getAddingPanel());
			addingScrollPane.setSize(getAddingPanel().getSize());
		}
		return addingScrollPane;
	}

}
