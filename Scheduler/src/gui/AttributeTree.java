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
	 * This method initializes the actual tree.
	 * 
	 * @return
	 */
	public void resetTreeModel() {
		DefaultMutableTreeNode root;
		root = new DefaultMutableTreeNode("Categories");
		for (Grouping<Unit> g : _middleEnd.getTournament().getCategories()) {
			DefaultMutableTreeNode groupingroot = new DefaultMutableTreeNode(new GroupingNodeObject(g));
			for (Unit u : g.getMembers()) {
				DefaultMutableTreeNode unitroot = new DefaultMutableTreeNode(new UnitNodeObject(u));
				groupingroot.add(unitroot);
			}
			root.add(groupingroot);
		}
		_treeModel = new DefaultTreeModel(root);
		this.setModel(_treeModel);
		this.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
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
		for (int i = 0; i < this.getRowCount(); i++)
			this.expandRow(i);
		this.repaint();
	}
	
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
