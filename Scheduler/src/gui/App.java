package gui;

import middleend.*;
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
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JRadioButtonMenuItem;

public class App implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JFrame _jFrame;
	private InputPanel _inputPane;
	private ManagementPanel _managementPane;
	private JMenuBar _jJMenuBar;
	private JMenu _fileMenu;
	private JMenuItem _pluginOptionsMenuItem;
	private JMenuItem _programOptionsMenuItem;
	private JMenuItem _printMenuItem;
	private JMenuItem _openPluginMenuItem;
	private JMenuItem _openTournamentMenuItem;
	private JMenuItem _saveMenuItem;
	private JMenuItem _exitMenuItem;
	private JMenu _editMenu;
	private JMenuItem _cutMenuItem;
	private JMenuItem _copyMenuItem;
	private JMenuItem _pasteMenuItem;
	private JMenu _addMenu;
	private JMenu _viewMenu;
	private JRadioButtonMenuItem _viewInputMenuItem;
	private JRadioButtonMenuItem _viewManagementMenuItem;
	private JMenu _helpMenu;
	private JMenuItem _aboutMenuItem;
	private JDialog _aboutDialog;
	private JPanel _aboutContentPane;
	private JLabel _aboutVersionLabel;
	
	public MiddleEnd getMiddleEnd() {
		if (_middleEnd == null) {
			_middleEnd = new MiddleEnd();
		}
		return _middleEnd;
	}
	
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	public JFrame getJFrame() {
		if (_jFrame == null) {
			_jFrame = new JFrame();
			_jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			_jFrame.setJMenuBar(getJJMenuBar());
			_jFrame.setSize(new Dimension(600, 400)); //TODO: make constants
			_jFrame.setMinimumSize(new Dimension(400, 300));
			_jFrame.setContentPane(getInputPanel());
			getViewInputMenuItem().setSelected(true);
			_jFrame.setTitle("Tournament Scheduler v1.0");
		}
		return _jFrame;
	}
	
	/**
	 * This method initializes InputPanel
	 * 
	 * @return InputPanel
	 */
	public InputPanel getInputPanel() {
		if (_inputPane == null) {
			_inputPane = new InputPanel(_middleEnd);
		}
		return _inputPane;
	}
	
	/**
	 * This method initializes ManagementPanel
	 * 
	 * @return MangementPanel
	 */
	public ManagementPanel getManagementPanel() {
		if (_managementPane == null) {
			_managementPane = new ManagementPanel(_middleEnd);
		}
		return _managementPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	public JMenuBar getJJMenuBar() {
		if (_jJMenuBar == null) {
			_jJMenuBar = new JMenuBar();
			_jJMenuBar.add(getFileMenu());
			_jJMenuBar.add(getEditMenu());
			_jJMenuBar.add(getViewMenu());
			_jJMenuBar.add(getAddMenu());
			_jJMenuBar.add(getHelpMenu());
		}
		return _jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getFileMenu() {
		if (_fileMenu == null) {
			_fileMenu = new JMenu();
			_fileMenu.setText("File");
			_fileMenu.add(getOpenPluginMenuItem());
			_fileMenu.add(getOpenTournamentMenuItem());
			_fileMenu.add(getPluginOptionsMenuItem());
			_fileMenu.add(getProgramOptionsMenuItem());
			_fileMenu.add(getPrintMenuItem());
			_fileMenu.add(getSaveMenuItem());
			_fileMenu.add(getExitMenuItem());
		}
		return _fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getEditMenu() {
		if (_editMenu == null) {
			_editMenu = new JMenu();
			_editMenu.setText("Edit");
			_editMenu.add(getCutMenuItem());
			_editMenu.add(getCopyMenuItem());
			_editMenu.add(getPasteMenuItem());
		}
		return _editMenu;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getViewMenu() {
		if (_viewMenu == null) {
			_viewMenu = new JMenu();
			_viewMenu.setText("View");
			_viewMenu.add(getViewInputMenuItem());
			_viewMenu.add(getViewManagementMenuItem());
			ButtonGroup viewgroup = new ButtonGroup();
			viewgroup.add(getViewInputMenuItem());
			viewgroup.add(getViewManagementMenuItem());
		}
		return _viewMenu;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getAddMenu() {
		if (_addMenu == null) {
			_addMenu = new JMenu();
			_addMenu.setText("Add");
		}
		return _addMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getHelpMenu() {
		if (_helpMenu == null) {
			_helpMenu = new JMenu();
			_helpMenu.setText("Help");
			_helpMenu.add(getAboutMenuItem());
		}
		return _helpMenu;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getSaveMenuItem() {
		if (_saveMenuItem == null) {
			_saveMenuItem = new JMenuItem();
			_saveMenuItem.setText("Save");
			_saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return _saveMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPluginOptionsMenuItem() {
		if (_pluginOptionsMenuItem == null) {
			_pluginOptionsMenuItem = new JMenuItem();
			_pluginOptionsMenuItem.setText("Plugin Options...");
		}
		return _pluginOptionsMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getProgramOptionsMenuItem() {
		if (_programOptionsMenuItem == null) {
			_programOptionsMenuItem = new JMenuItem();
			_programOptionsMenuItem.setText("Program Options...");
		}
		return _programOptionsMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPrintMenuItem() {
		if (_printMenuItem == null) {
			_printMenuItem = new JMenuItem();
			_printMenuItem.setText("Print...");
			_printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
					Event.CTRL_MASK, true));
		}
		return _printMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getOpenPluginMenuItem() {
		if (_openPluginMenuItem == null) {
			_openPluginMenuItem = new JMenuItem();
			_openPluginMenuItem.setText("Open Plugin...");
			_openPluginMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
					Event.CTRL_MASK, true));
		}
		return _openPluginMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getOpenTournamentMenuItem() {
		if (_openTournamentMenuItem == null) {
			_openTournamentMenuItem = new JMenuItem();
			_openTournamentMenuItem.setText("Open Tournament...");
			_openTournamentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
					Event.CTRL_MASK, true));
		}
		return _openTournamentMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getExitMenuItem() {
		if (_exitMenuItem == null) {
			_exitMenuItem = new JMenuItem();
			_exitMenuItem.setText("Exit");
			_exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
					Event.CTRL_MASK, true));
			_exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return _exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getCutMenuItem() {
		if (_cutMenuItem == null) {
			_cutMenuItem = new JMenuItem();
			_cutMenuItem.setText("Cut");
			_cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return _cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getCopyMenuItem() {
		if (_copyMenuItem == null) {
			_copyMenuItem = new JMenuItem();
			_copyMenuItem.setText("Copy");
			_copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return _copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getPasteMenuItem() {
		if (_pasteMenuItem == null) {
			_pasteMenuItem = new JMenuItem();
			_pasteMenuItem.setText("Paste");
			_pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return _pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JRadioButtonMenuItem getViewInputMenuItem() {
		if (_viewInputMenuItem == null) {
			_viewInputMenuItem = new JRadioButtonMenuItem();
			_viewInputMenuItem.setText("View Input Panel");
			_viewInputMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
					Event.CTRL_MASK, true));
			_viewInputMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					_jFrame.setContentPane(getInputPanel());
				}
			});
		}
		return _viewInputMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JRadioButtonMenuItem getViewManagementMenuItem() {
		if (_viewManagementMenuItem == null) {
			_viewManagementMenuItem = new JRadioButtonMenuItem();
			_viewManagementMenuItem.setText("View Management Panel");
			_viewManagementMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
					Event.CTRL_MASK, true));
			_viewManagementMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					_jFrame.setContentPane(getManagementPanel());
				}
			});
		}
		return _viewManagementMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getAboutMenuItem() {
		if (_aboutMenuItem == null) {
			_aboutMenuItem = new JMenuItem();
			_aboutMenuItem.setText("About...");
			_aboutMenuItem.addActionListener(new ActionListener() {
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
		return _aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	public JDialog getAboutDialog() {
		if (_aboutDialog == null) {
			_aboutDialog = new JDialog(getJFrame(), true);
			_aboutDialog.setTitle("About");
			_aboutDialog.setContentPane(getAboutContentPane());
		}
		return _aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getAboutContentPane() {
		if (_aboutContentPane == null) {
			_aboutContentPane = new JPanel();
			_aboutContentPane.setLayout(new BorderLayout());
			_aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return _aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	public JLabel getAboutVersionLabel() {
		if (_aboutVersionLabel == null) {
			_aboutVersionLabel = new JLabel();
			_aboutVersionLabel.setText("<html><center>Tournament Scheduler - Version 1.0<br>" +
					"Created by Patrick Clay, Matthew Mahoney, and Aswin Karumbunathan</center></html>");
			_aboutVersionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
		return _aboutVersionLabel;
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
