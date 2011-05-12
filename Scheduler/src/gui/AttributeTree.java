package gui;

import middleend.*;
import backbone.*;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * This class is the tree that allows users to see an overview of all
 * the groupings and units in the tournament.
 */
public class AttributeTree extends JTree implements GUIConstants {

	public static final long serialVersionUID = 1L;
	
	private TreeModel _treeModel;
	private MiddleEnd _middleEnd;
	private AddingPanel _addingPanel;
	
	/**
	 * Constructor.
	 * 
	 * @param me
	 * @param ap
	 */
	public AttributeTree(MiddleEnd me, AddingPanel ap) {
		super();
		_middleEnd = me;
		_addingPanel = ap;
		initialize();
	}
		
	/**
	 * This method initializes this component.
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(TREE_SIZE);
		this.setMaximumSize(new Dimension(getWidth(), Integer.MAX_VALUE));
		this.setPreferredSize(getSize());
		if (IMAGESON) {
			DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
			renderer.setOpenIcon(TREEOPENIMAGE);
			renderer.setClosedIcon(TREECLOSEDIMAGE);
			renderer.setLeafIcon(TREELEAFIMAGE);
			this.setCellRenderer(renderer);
		}
		if (COLORSON) {
			this.setBackground(BACKGROUND_COLOR);
			this.setForeground(FOREGROUND_COLOR);
		}
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.setScrollsOnExpand(true);
		this.resetTreeModel();
	}
	
	/**
	 * This method initializes the actual tree, refreshing
	 * all of its values
	 */
	public void resetTreeModel() {
		DefaultMutableTreeNode root;
		root = new DefaultMutableTreeNode("Categories");
		for (Grouping<Unit> g : _middleEnd.getTournament().getCategories()) {
			// Add a node for each grouping
			DefaultMutableTreeNode groupingroot = new DefaultMutableTreeNode(new GroupingNodeObject(g));
			for (Unit u : g.getMembers()) {
				// Add a leaf node to each of those nodes to represent the units in the grouping
				DefaultMutableTreeNode unitroot = new DefaultMutableTreeNode(new UnitNodeObject(u));
				groupingroot.add(unitroot);
			}
			root.add(groupingroot);
		}
		_treeModel = new DefaultTreeModel(root);
		this.setModel(_treeModel);
		this.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				// View a unit or grouping's information if clicked
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
				if (node == null)
					return;
				if (node.getUserObject() instanceof GroupingNodeObject) {
					_addingPanel.setViewPanel(((GroupingNodeObject) node.getUserObject()).group);
				}
				else if (node.getUserObject() instanceof UnitNodeObject) {
					_addingPanel.setViewPanel(((UnitNodeObject) node.getUserObject()).unit);
				}
				_middleEnd.repaintAll();
			}
		});
		// Expand all rows
		for (int i = 0; i < this.getRowCount(); i++)
			this.expandRow(i);
		this.repaint();
	}
	
	// A wrapper for a Grouping so that the toString() of the
	// GroupingNodeObject shows up as its getName() attribute
	private class GroupingNodeObject {
		public Grouping<Unit> group;
		
		public GroupingNodeObject(Grouping<Unit> g) {
			group = g;
		}
		
		@Override
		public String toString() {
			return group.getName();
		}
	}
	
	// A wrapper for a Unit so that the toString() of the
	// UnitNodeObject shows up as its getName() attribute
	private class UnitNodeObject {
		public Unit unit;
		
		public UnitNodeObject(Unit u) {
			unit = u;
		}
		
		@Override
		public String toString() {
			return unit.getName();
		}
	}
	
}
