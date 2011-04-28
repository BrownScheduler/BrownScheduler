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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JFrame _jFrame;
	private InputPanel _inputPane;
	private JScrollPane _managementScrollPane;
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
	private JDialog _programOptionsDialog;
	private JPanel _programOptionsContentPane;
	private JDialog _pluginOptionsDialog;
	private JPanel _pluginOptionsContentPane;
	private JDialog _printDialog;
	private JPanel _printContentPane;
	
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
			_inputPane = new InputPanel(getMiddleEnd());
		}
		return _inputPane;
	}
	
	/**
	 * This method initializes JScrollPanel
	 * 
	 * @return JScrollPanel
	 */
	public JScrollPane getManagementScrollPanel() {
		if (_managementScrollPane == null) {
			_managementScrollPane = new JScrollPane(getManagementPanel());
		}
		return _managementScrollPane;
	}
	
	/**
	 * This method initializes ManagementPanel
	 * 
	 * @return MangementPanel
	 */
	public ManagementPanel getManagementPanel() {
		if (_managementPane == null) {
			_managementPane = new ManagementPanel(getMiddleEnd());
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
			//TODO file extension?
			_saveMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					int returnval = chooser.showSaveDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						getMiddleEnd().saveFile(chooser.getSelectedFile());
						if (!getMiddleEnd().saveFile(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The name specified for the file was invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
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
			_pluginOptionsMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog pluginOptionsDialog = getPluginOptionsDialog();
					pluginOptionsDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					pluginOptionsDialog.setLocation(loc);
					pluginOptionsDialog.setVisible(true);
				}
			});
		}
		return _pluginOptionsMenuItem;
	}
	
	/**
	 * This method initializes _pluginOptionsPane	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getPluginOptionsDialog() {
		if (_pluginOptionsDialog == null) {
			_pluginOptionsDialog = new JDialog(getJFrame());
			_pluginOptionsDialog.setTitle("Plugin Options");
			_pluginOptionsDialog.setContentPane(getPluginOptionsContentPane());
		}
		return _pluginOptionsDialog;
	}

	/**
	 * This method initializes _pluginOptionsContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPluginOptionsContentPane() {
		if (_pluginOptionsContentPane == null) {
			_pluginOptionsContentPane = new JPanel();
			_pluginOptionsContentPane.setLayout(new BorderLayout());
		}
		return _pluginOptionsContentPane;
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
			_programOptionsMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog programOptionsDialog = getProgramOptionsDialog();
					programOptionsDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					programOptionsDialog.setLocation(loc);
					programOptionsDialog.setVisible(true);
				}
			});
		}
		return _programOptionsMenuItem;
	}
	
	/**
	 * This method initializes _optionsDialog	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getProgramOptionsDialog() {
		if (_programOptionsDialog == null) {
			_programOptionsDialog = new JDialog(getJFrame());
			_programOptionsDialog.setTitle("Program Options");
			_programOptionsDialog.setContentPane(getProgramOptionsContentPane());
		}
		return _programOptionsDialog;
	}

	/**
	 * This method initializes _optionsContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProgramOptionsContentPane() {
		if (_programOptionsContentPane == null) {
			_programOptionsContentPane = new JPanel();
			_programOptionsContentPane.setLayout(new BorderLayout());
		}
		return _programOptionsContentPane;
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
			_printMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog printDialog = getPrintDialog();
					printDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					printDialog.setLocation(loc);
					printDialog.setVisible(true);
				}
			});
		}
		return _printMenuItem;
	}
	

	/**
	 * This method initializes _printDialog	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getPrintDialog() {
		if (_printDialog == null) {
			_printDialog = new JDialog(getJFrame());
			_printDialog.setTitle("Print");
			_printDialog.setContentPane(getPrintContentPane());
		}
		return _printDialog;
	}

	/**
	 * This method initializes _printContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPrintContentPane() {
		if (_printContentPane == null) {
			_printContentPane = new JPanel();
			_printContentPane.setLayout(new BorderLayout());
		}
		return _printContentPane;
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
			_openPluginMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter("Plugin File", "plug")); //TODO make constants
					int returnval = chooser.showOpenDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().openPlugin(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The selected file was not a valid plugin file.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
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
			_openTournamentMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter("Tournament File", ".xml", ".csv", ".tmnt")); //TODO make constants
					int returnval = chooser.showOpenDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().openTournament(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The selected file was not a valid tournament file.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
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
					getInputPanel().setSize(_jFrame.getContentPane().getSize());
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
					getManagementScrollPanel().setSize(_jFrame.getContentPane().getSize());
					_jFrame.setContentPane(getManagementScrollPanel());
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