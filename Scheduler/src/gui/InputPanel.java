package gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import middleend.MiddleEnd;

/**
 * This class is what the user uses to add new units and manage
 * their data. Contains an AttributeTree and an AddingPanel.
 */
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
	 * This method initializes this.
	 */
	public void initialize() {
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		this.setSize(DEFAULT_SIZE);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getAttributeScrollPane());
		this.add(getAddingScrollPane());
	}

	/**
	 * Getter for the AddingPanel, initializes it
	 * if necessary
	 * 
	 * @return AddingPanel
	 */
	public AddingPanel getAddingPanel() {
		if (_addingPanel == null) {
			_addingPanel = new AddingPanel(_middleEnd);
		}
		return _addingPanel;
	}

	/**
	 * Getter for the AttributeTree, initializes it
	 * if necessary
	 * 
	 * @return AttributeTree
	 */
	public AttributeTree getAttributeTree() {
		if (_attributeTree == null) {
			_attributeTree = new AttributeTree(_middleEnd, getAddingPanel());
		}
		return _attributeTree;
	}

	/**
	 * Getter for the JScrollPane containing the AttributeTree,
	 * initializes it if necessary.
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane getAttributeScrollPane() {
		if (_attributeScrollPane == null) {
			_attributeScrollPane = new JScrollPane(getAttributeTree());
			_attributeScrollPane.getVerticalScrollBar().setBlockIncrement(90);
			_attributeScrollPane.getVerticalScrollBar().setUnitIncrement(30);
			_attributeScrollPane.setSize(getAttributeTree().getSize());
			_attributeScrollPane.setMaximumSize(getAttributeTree().getMaximumSize());
		}
		return _attributeScrollPane;
	}
	
	/**
	 * Getter for the JScrollPane containing the AddingPanel,
	 * initializes it if necessary.
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane getAddingScrollPane() {
		if (_addingScrollPane == null) {
			_addingScrollPane = new JScrollPane(getAddingPanel());
			_addingScrollPane.getVerticalScrollBar().setBlockIncrement(90);
			_addingScrollPane.getVerticalScrollBar().setUnitIncrement(30);
			_addingScrollPane.setSize(getAddingPanel().getSize());
		}
		return _addingScrollPane;
	}
	
	/**
	 * Repaints this component and all components in it.
	 */
	public void repaintAll() {
		getAttributeScrollPane().repaint();
		getAttributeTree().resetTreeModel();
		getAddingScrollPane().repaint();
		getAddingPanel().repaintAll();
	}

}
