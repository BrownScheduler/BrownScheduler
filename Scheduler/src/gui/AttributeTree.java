package gui;

import middleend.*;
import backbone.*;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

public class AttributeTree extends JTree implements GUIConstants {

	public static final long serialVersionUID = 1L;
	
	private TreeModel _treeModel;
	private MiddleEnd _middleEnd;
	private AddingPanel _addingPanel;
	
	public AttributeTree(MiddleEnd me, AddingPanel ap) {
		super();
		_middleEnd = me;
		_addingPanel = ap;
		initialize();
	}
		
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
			this.setSize(TREE_SIZE);
			this.setMaximumSize(new Dimension(getWidth(), Integer.MAX_VALUE));
			this.setPreferredSize(getSize());
			this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			this.resetTreeModel();
	}
	
	/**
	 * This method initializes the actual tree.
	 * 
	 * @return
	 */
	public void resetTreeModel() {
		if (_treeModel == null) {
			DefaultMutableTreeNode root;
			root = new DefaultMutableTreeNode("Categories");
			for (Grouping<Unit> g : _middleEnd.getTournament().getCategories()) {
				DefaultMutableTreeNode groupingroot = new DefaultMutableTreeNode(g);
				for (Unit u : g.getMembers()) {
					DefaultMutableTreeNode unitroot = new DefaultMutableTreeNode(u);
					groupingroot.add(unitroot);
				}
				root.add(groupingroot);
			}
			_treeModel = new DefaultTreeModel(root);
		}
		this.setModel(_treeModel);
		this.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
				if (node == null)
					return;
				if (node.getUserObject() instanceof Grouping<?>) {
					_addingPanel.setViewPanel((Grouping<Unit>) node.getUserObject());
				}
				else if (node.getUserObject() instanceof Unit) {
					_addingPanel.setViewPanel((Unit) node.getUserObject());
				}
			}
		});
	}
	
	/**
	 * Setter for the TreeModel of the tree.
	 */
	public void setTree(TreeModel m) {
		_treeModel = m;
	}
	
}
