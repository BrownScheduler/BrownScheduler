package gui;

import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class AttributeTree extends JTree {

	public static final long serialVersionUID = 1L;
	
	public TreeModel treeModel;
	
	public AttributeTree() {
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
		if (treeModel == null) {
			DefaultMutableTreeNode root;
			root = new DefaultMutableTreeNode("Blahblah");
			root.add(new DefaultMutableTreeNode("Blahblahblah"));
			treeModel = new DefaultTreeModel(root);
		}
		return treeModel;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
