package gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import middleend.MiddleEnd;

public class InputPanel extends JPanel implements GUIConstants {

	public static final long serialVersionUID = 1L;
	private MiddleEnd _middleEnd;
	private AddingPanel _addingPanel;
	private AttributeTree _attributeTree;
	private JScrollPane _attributeScrollPane;
	private JScrollPane _addingScrollPane;

	/**
	 * This is the default constructor
	 */
	public InputPanel(MiddleEnd m) {
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
		this.setSize(DEFAULT_SIZE);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getAttributeScrollPane());
		this.add(getAddingScrollPane());
	}

	/**
	 * This method initializes AddingPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public AddingPanel getAddingPanel() {
		if (_addingPanel == null) {
			_addingPanel = new AddingPanel(_middleEnd);
		}
		return _addingPanel;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public AttributeTree getAttributeTree() {
		if (_attributeTree == null) {
			_attributeTree = new AttributeTree(_middleEnd, getAddingPanel());
		}
		return _attributeTree;
	}

	/**
	 * This method initializes attributePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getAttributeScrollPane() {
		if (_attributeScrollPane == null) {
			_attributeScrollPane = new JScrollPane(getAttributeTree());
			_attributeScrollPane.setSize(getAttributeTree().getSize());
			_attributeScrollPane.setMaximumSize(getAttributeTree().getMaximumSize());
		}
		return _attributeScrollPane;
	}
	
	/**
	 * This method initializes attributePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getAddingScrollPane() {
		if (_addingScrollPane == null) {
			_addingScrollPane = new JScrollPane(getAddingPanel());
			_addingScrollPane.setSize(getAddingPanel().getSize());
		}
		return _addingScrollPane;
	}
	
	public void repaintAll() {
		getAttributeScrollPane().repaint();
		getAttributeTree().resetTreeModel();
		getAddingScrollPane().repaint();
		getAddingPanel().repaintAll();
	}

}
