package gui;

import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import middleend.MiddleEnd;

public class AttributeTree extends JTree {

	public static final long serialVersionUID = 1L;
	
	private TreeModel _treeModel;
	private MiddleEnd _middleEnd;
	
	public AttributeTree(MiddleEnd m) {
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
			this.setSize(200, 300); //TODO: make constants
			this.setMaximumSize(new Dimension(getWidth(), Integer.MAX_VALUE));
			this.setPreferredSize(getSize());
			this.setModel(getTree());
	}
	
	/**
	 * This method initializes the actual tree.
	 * 
	 * @return
	 */
	public TreeModel getTree() {
		if (_treeModel == null) {
			DefaultMutableTreeNode root;
			root = new DefaultMutableTreeNode("Blahblah");
			root.add(new DefaultMutableTreeNode("Blahblahblah"));
			_treeModel = new DefaultTreeModel(root);
		}
		return _treeModel;
	}
	
	/**
	 * Setter for the TreeModel of the tree.
	 */
	public void setTree(TreeModel m) {
		_treeModel = m;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
