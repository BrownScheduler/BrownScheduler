package gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;

public class App implements GUIConstants {

	public JFrame jFrame;
	public InputPanel inputPane;
	public ManagementPanel managementPane;
	public JMenuBar jJMenuBar;
	public JMenu fileMenu;
	public JMenuItem pluginOptionsMenuItem;
	public JMenuItem programOptionsMenuItem;
	public JMenuItem printMenuItem;
	public JMenuItem openPluginMenuItem;
	public JMenuItem openTournamentMenuItem;
	public JMenuItem saveMenuItem;
	public JMenuItem exitMenuItem;
	public JMenu editMenu;
	public JMenuItem cutMenuItem;
	public JMenuItem copyMenuItem;
	public JMenuItem pasteMenuItem;
	public JMenu addMenu;
	public JMenu viewMenu;
	public JMenuItem viewInputMenuItem;
	public JMenuItem viewManagementMenuItem;
	public JMenu helpMenu;
	public JMenuItem aboutMenuItem;
	public JDialog aboutDialog;
	public JPanel aboutContentPane;
	public JLabel aboutVersionLabel;
	
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(new Dimension(600, 400)); //TODO: make constants
			jFrame.setMinimumSize(new Dimension(400, 300));
			jFrame.setContentPane(getInputPanel());
			jFrame.setTitle("Tournament Scheduler v1.0");
		}
		return jFrame;
	}
	
	/**
	 * This method initializes InputPanel
	 * 
	 * @return InputPanel
	 */
	public InputPanel getInputPanel() {
		if (inputPane == null) {
			inputPane = new InputPanel();
		}
		return inputPane;
	}
	
	/**
	 * This method initializes ManagementPanel
	 * 
	 * @return MangementPanel
	 */
	public ManagementPanel getManagementPanel() {
		if (managementPane == null) {
			managementPane = new ManagementPanel();
		}
		return managementPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	public JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getViewMenu());
			jJMenuBar.add(getAddMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getOpenPluginMenuItem());
			fileMenu.add(getOpenTournamentMenuItem());
			fileMenu.add(getPluginOptionsMenuItem());
			fileMenu.add(getProgramOptionsMenuItem());
			fileMenu.add(getPrintMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getViewMenu() {
		if (viewMenu == null) {
			viewMenu = new JMenu();
			viewMenu.setText("View");
			viewMenu.add(getViewInputMenuItem());
			viewMenu.add(getViewManagementMenuItem());
		}
		return viewMenu;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getAddMenu() {
		if (addMenu == null) {
			addMenu = new JMenu();
			addMenu.setText("Add");
		}
		return addMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPluginOptionsMenuItem() {
		if (pluginOptionsMenuItem == null) {
			pluginOptionsMenuItem = new JMenuItem();
			pluginOptionsMenuItem.setText("Plugin Options...");
		}
		return pluginOptionsMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getProgramOptionsMenuItem() {
		if (programOptionsMenuItem == null) {
			programOptionsMenuItem = new JMenuItem();
			programOptionsMenuItem.setText("Program Options...");
		}
		return programOptionsMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPrintMenuItem() {
		if (printMenuItem == null) {
			printMenuItem = new JMenuItem();
			printMenuItem.setText("Print...");
			printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
					Event.CTRL_MASK, true));
		}
		return printMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getOpenPluginMenuItem() {
		if (openPluginMenuItem == null) {
			openPluginMenuItem = new JMenuItem();
			openPluginMenuItem.setText("Open Plugin...");
		}
		return openPluginMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getOpenTournamentMenuItem() {
		if (openTournamentMenuItem == null) {
			openTournamentMenuItem = new JMenuItem();
			openTournamentMenuItem.setText("Open Tournament...");
		}
		return openTournamentMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getViewInputMenuItem() {
		if (viewInputMenuItem == null) {
			viewInputMenuItem = new JMenuItem();
			viewInputMenuItem.setText("View Input Panel");
			viewInputMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jFrame.setContentPane(getInputPanel());
				}
			});
		}
		return viewInputMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getViewManagementMenuItem() {
		if (viewManagementMenuItem == null) {
			viewManagementMenuItem = new JMenuItem();
			viewManagementMenuItem.setText("View Management Panel");
			viewManagementMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jFrame.setContentPane(getManagementPanel());
				}
			});
		}
		return viewManagementMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About...");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	public JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	public JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("<html><center>Tournament Scheduler - Version 1.0<br>" +
					"Created by Patrick Clay, Matthew Mahoney, and Aswin Karumbunathan</center></html>");
			aboutVersionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
		return aboutVersionLabel;
	}
	
	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				App application = new App();
				application.getJFrame().setVisible(true);
			}
		});
	}

}
