package gui;

import middleend.*;
import backbone.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.KeyStroke;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import exception.InvalidRoundException;

/**
 * This is the App class that runs the program.
 * 
 * It takes care of creating all the basic GUI components.
 * 
 * All components in this should be accessed via the appropriate
 * getters, which initialize the component if necessary. Again,
 * all components are initialized by their getters.
 */
public class App implements GUIConstants {

	private MiddleEnd _middleEnd;
	private JFrame _jFrame;
	private JToolBar _toolbar;
	private JPanel _mainContentAndToolbarPane;
	private JComponent _mainContentPane;
	private InputPanel _inputPane;
	private ManagementPanel _managementPane;
	private JMenuBar _jJMenuBar;
	private JMenu _fileMenu;
	private JMenuItem _newTournamentMenuItem;
	private JMenuItem _openTournamentMenuItem;
	private JMenuItem _saveTournamentMenuItem;
	private JMenuItem _importCategoryMenuItem;
	private JMenuItem _exportCategoryMenuItem;
	private JMenuItem _exitMenuItem;
	private JMenu _optionsMenu;
	private JMenuItem _programOptionsMenuItem;
	private JDialog _programOptionsDialog;
	private JPanel _programOptionsContentPane;
	private JMenuItem _pluginOptionsMenuItem;
	private JDialog _pluginOptionsDialog;
	private JPanel _pluginOptionsContentPane;
	private JMenu _editMenu;
	private JMenu _viewMenu;
	private JRadioButtonMenuItem _viewInputMenuItem;
	private JRadioButtonMenuItem _viewManagementMenuItem;
	private JMenu _helpMenu;
	private JMenuItem _aboutMenuItem;
	private JDialog _aboutDialog;
	private JPanel _aboutContentPane;
	private JLabel _aboutVersionLabel;
	
	/**
	 * Constructor, requires a MiddleEnd.
	 * 
	 * @param me
	 */
	public App(MiddleEnd me) {
		_middleEnd = me;
		this.getJFrame().setVisible(true);
	}
	
	/**
	 * Getter for this app's MiddleEnd.
	 * 
	 * @return
	 */
	public MiddleEnd getMiddleEnd() {
		return _middleEnd;
	}
	
	/**
	 * Getter for the outer JFrame.
	 * 
	 * @return JFrame
	 */
	public JFrame getJFrame() {
		if (_jFrame == null) {
			_jFrame = new JFrame();
			_jFrame.setJMenuBar(getJJMenuBar());
			_jFrame.setSize(DEFAULT_SIZE);
			_jFrame.setMinimumSize(MIN_SIZE);
			_jFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					_middleEnd.closeThisMiddleEnd();
				}
			});
			if (IMAGESON) {
				if (FRAMEIMAGE != null)
					_jFrame.setIconImage(FRAMEIMAGE.getImage());
			}
			getViewInputMenuItem().doClick();
			_jFrame.setContentPane(getMainContentAndToolbarPane());
			getViewInputMenuItem().setSelected(true);
			_jFrame.setTitle("TurtleTab v1.0");
		}
		return _jFrame;
	}
	
	/**
	 * Getter for the JToolBar.
	 * 
	 * @return JToolBar
	 */
	public JToolBar getToolbar() {
		if (_toolbar == null) {
			_toolbar = new JToolBar();
			_toolbar.setSize(TOOLBAR_SIZE);
			_toolbar.setMinimumSize(TOOLBAR_SIZE);
			_toolbar.setMaximumSize(TOOLBAR_SIZE);
			for (int i = 0; i < getEditMenu().getMenuComponentCount(); i++) {
				Component comp = getEditMenu().getMenuComponent(i);
				if (comp instanceof JMenuItem) {
					_toolbar.add(createButtonFromMenuItem((JMenuItem) comp));
					_toolbar.add(Box.createRigidArea(SMALLSPACING_SIZE));
				}
			}
		}
		return _toolbar;
	}
	
	/**
	 * Creates a JButton from a JMenuItem
	 * 
	 * @param JMenuItem
	 * @return JButton
	 */
	public JButton createButtonFromMenuItem(final JMenuItem item) {
		JButton button = new JButton(item.getText());
		if (IMAGESON)
			button.setIcon(ADDBUTTONIMAGE);
		if (COLORSON) {
			button.setBackground(BACKGROUND_COLOR);
			button.setForeground(FOREGROUND_COLOR);
		}
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				item.doClick();
			}
		});
		return button;
	}
	
	/**
	 * Getter for the outermost JPanel.
	 * 
	 * @return
	 */
	public JPanel getMainContentAndToolbarPane() {
		if (_mainContentAndToolbarPane == null) {
			_mainContentAndToolbarPane = new JPanel();
			if (COLORSON) {
				_mainContentAndToolbarPane.setBackground(BACKGROUND_COLOR);
				_mainContentAndToolbarPane.setForeground(FOREGROUND_COLOR);
			}
			_mainContentAndToolbarPane.setLayout(new BorderLayout());
			_mainContentAndToolbarPane.add(getToolbar(), BorderLayout.NORTH);
			_mainContentAndToolbarPane.add(getMainContentPane(), BorderLayout.CENTER);
		}
		return _mainContentAndToolbarPane;
	}
	
	/**
	 * Setter for the outermost JPanel.
	 * 
	 * @param pane
	 */
	public void setMainContentPane(JComponent pane) {
		getMainContentPane().removeAll();
		getMainContentPane().add(pane, BorderLayout.CENTER);
	}
	
	/**
	 * Getter for the main content pane.
	 * 
	 * @return JComponent
	 */
	public JComponent getMainContentPane() {
		if (_mainContentPane == null) {
			_mainContentPane = new JPanel();
			if (COLORSON) {
				_mainContentPane.setBackground(BACKGROUND_COLOR);
				_mainContentPane.setForeground(FOREGROUND_COLOR);
			}
			_mainContentPane.setLayout(new BorderLayout());
			_mainContentPane.add(getInputPanel(), BorderLayout.CENTER);
		}
		return _mainContentPane;
	}
	
	/**
	 * Getter for the InputPanel.
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
	 * Getter for the ManagementPanel.
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
	 * Getter for the JMenuBar.
	 * 	
	 * @return JMenuBar	
	 */
	public JMenuBar getJJMenuBar() {
		if (_jJMenuBar == null) {
			_jJMenuBar = new JMenuBar();
			_jJMenuBar.add(getFileMenu());
//			_jJMenuBar.add(getOptionsMenu());
			_jJMenuBar.add(getViewMenu());
			_jJMenuBar.add(getEditMenu());
			_jJMenuBar.add(getHelpMenu());
		}
		return _jJMenuBar;
	}

	/**
	 * Getter for the File JMenu.
	 * 	
	 * @return JMenu	
	 */
	public JMenu getFileMenu() {
		if (_fileMenu == null) {
			_fileMenu = new JMenu();
			_fileMenu.setText("File");
			_fileMenu.add(getNewTournamentMenuItem());
			_fileMenu.add(getOpenTournamentMenuItem());
			_fileMenu.add(getSaveTournamentMenuItem());
			_fileMenu.add(getImportCategoryMenuItem());
			_fileMenu.add(getExportCategoryMenuItem());
			_fileMenu.add(getExitMenuItem());
		}
		return _fileMenu;
	}
	
	/**
	 * Getter for the New Tournament JMenuItem.
	 * 
	 * Opens a new window for a new tournament.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getNewTournamentMenuItem() {
		if (_newTournamentMenuItem == null) {
			_newTournamentMenuItem = new JMenuItem();
			_newTournamentMenuItem.setText("New Tournament...");
			_newTournamentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
					Event.CTRL_MASK, true));
			_newTournamentMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					_middleEnd.openNewMiddleEnd();
				}
			});
		}
		return _newTournamentMenuItem;
	}
	
	/**
	 * Getter for the Open Tournament JMenuItem.
	 * 	
	 * @return JMenuItem	
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
					chooser.setFileFilter(new FileNameExtensionFilter("Tournament File (.tmnt)", TOURNAMENT_EXTENSION));
					int returnval = chooser.showOpenDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().openTournament(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The selected file was not a valid tournament file.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							_middleEnd.repaintAll();
						}
					}
				}
			});
		}
		return _openTournamentMenuItem;
	}
	
	/**
	 * Getter for the Save Tournament JMenuItem.	
	 * 
	 * Saves the current tournament.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getSaveTournamentMenuItem() {
		if (_saveTournamentMenuItem == null) {
			_saveTournamentMenuItem = new JMenuItem();
			_saveTournamentMenuItem.setText("Save Tournament...");
			_saveTournamentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			_saveTournamentMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter("Tournament File (.tmnt)", TOURNAMENT_EXTENSION));
					int returnval = chooser.showSaveDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().saveTournament(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The name specified for the file was invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							_middleEnd.repaintAll();
						}
					}
				}
			});
		}
		return _saveTournamentMenuItem;
	}
	
	/**
	 * Getter for the Import Category JMenuItem.	
	 * 
	 * Imports a category from a file.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getImportCategoryMenuItem() {
		if (_importCategoryMenuItem == null) {
			_importCategoryMenuItem = new JMenuItem();
			_importCategoryMenuItem.setText("Import Category...");
			_importCategoryMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
					Event.CTRL_MASK, true));
			_importCategoryMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] options = {"Continue importing", "Save first, then import", "Cancel import"};
					int returnval = JOptionPane.showOptionDialog(getJFrame(), "Importing overwrites data in the current tournament. Importing a malformed CSV file could destroy some data, would you like to save the tournament before importing?",
							"Import Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
							options, options[2]);
					if (returnval == JOptionPane.CANCEL_OPTION)
						return;
					if (returnval == JOptionPane.NO_OPTION)
						getSaveTournamentMenuItem().doClick();
					JFileChooser chooser = new JFileChooser();
					chooser.setApproveButtonText("Import");
					chooser.setFileFilter(new FileNameExtensionFilter("CSV File (.csv)", CATEGORY_EXTENSION));
					returnval = chooser.showOpenDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().importCategory(chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The selected file was not a valid tournament file.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							_middleEnd.repaintAll();
						}
					}
				}
			});
		}
		return _importCategoryMenuItem;
	}
	
	/**
	 * Getter for the Export Category JMenuItem.	
	 * 
	 * Exports a category to a file.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getExportCategoryMenuItem() {
		if (_exportCategoryMenuItem == null) {
			_exportCategoryMenuItem = new JMenuItem();
			_exportCategoryMenuItem.setText("Export Category...");
			_exportCategoryMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
					Event.CTRL_MASK, true));
			_exportCategoryMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<Grouping> categories = new ArrayList<Grouping>(_middleEnd.getTournament().getCategories());
					ArrayList<String> catnames = new ArrayList<String>();
					for (Grouping<Unit> category : categories)
						catnames.add(category.getName());
					String str = (String) JOptionPane.showInputDialog(getJFrame(), "Which category would you like to export?", "Export Category",
							JOptionPane.PLAIN_MESSAGE, null, catnames.toArray(new String[0]), catnames.get(0));
					if (catnames.indexOf(str) < 0) {
						JOptionPane.showMessageDialog(getJFrame(), "Some error occurred while importing, please try again.", "Import error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Grouping toadd = categories.get(catnames.indexOf(str));
					JFileChooser chooser = new JFileChooser();
					chooser.setApproveButtonText("Export");
					chooser.setFileFilter(new FileNameExtensionFilter("CSV File (.csv)", CATEGORY_EXTENSION));
					int returnval = chooser.showSaveDialog(getJFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						if (!getMiddleEnd().exportCategory(toadd, chooser.getSelectedFile())) {
							JOptionPane.showMessageDialog(_jFrame, "The name specified for the file was invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							_middleEnd.repaintAll();
						}
					}
				}
			});
		}
		return _exportCategoryMenuItem;
	}
	
	/**
	 * Getter for the Exit JMenuItem.
	 * 
	 * Exits the program.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getExitMenuItem() {
		if (_exitMenuItem == null) {
			_exitMenuItem = new JMenuItem();
			_exitMenuItem.setText("Exit");
			_exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
					Event.CTRL_MASK, true));
			_exitMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					_middleEnd.closeThisMiddleEnd();
				}
			});
		}
		return _exitMenuItem;
	}
	
	/**
	 * Getter for the Options JMenu	
	 * 	
	 * @return JMenu	
	 */
	public JMenu getOptionsMenu() {
		if (_optionsMenu == null) {
			_optionsMenu = new JMenu();
			_optionsMenu.setText("Options");
			_optionsMenu.add(getProgramOptionsMenuItem());
			_optionsMenu.add(getPluginOptionsMenuItem());
		}
		return _optionsMenu;
	}
	
	/**
	 * Getter for the Plugin Options JMenuItem.
	 * 
	 * Shows user-editable options of the plugin.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getPluginOptionsMenuItem() {
		if (_pluginOptionsMenuItem == null) {
			_pluginOptionsMenuItem = new JMenuItem();
			_pluginOptionsMenuItem.setText("Plugin Options...");
			_pluginOptionsMenuItem.addActionListener(new ActionListener() {
				@Override
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
	 * Getter for the Plugin Options JDialog box.
	 * 	
	 * @return JDialog	
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
	 * Getter for the content panel of the Plugin Options Dialog box.
	 * 	
	 * @return JPanel	
	 */
	private JPanel getPluginOptionsContentPane() {
		if (_pluginOptionsContentPane == null) {
			_pluginOptionsContentPane = new JPanel();
			if (COLORSON) {
				_pluginOptionsContentPane.setBackground(BACKGROUND_COLOR);
				_pluginOptionsContentPane.setForeground(FOREGROUND_COLOR);
			}
			_pluginOptionsContentPane.setLayout(new BorderLayout());
		}
		return _pluginOptionsContentPane;
	}
	
	/**
	 * Getter for the Program Options JMenuItem.
	 * 
	 * Shows user-editable of the program.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getProgramOptionsMenuItem() {
		if (_programOptionsMenuItem == null) {
			_programOptionsMenuItem = new JMenuItem();
			_programOptionsMenuItem.setText("Program Options...");
			_programOptionsMenuItem.addActionListener(new ActionListener() {
				@Override
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
	 * Getter for the Program Options Dialog box.
	 * 	
	 * @return JDialog	
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
	 * Getter for the content pane of the Program Options Dialog box.	
	 * 	
	 * @return JPanel	
	 */
	private JPanel getProgramOptionsContentPane() {
		if (_programOptionsContentPane == null) {
			_programOptionsContentPane = new JPanel();
			if (COLORSON) {
				_programOptionsContentPane.setBackground(BACKGROUND_COLOR);
				_programOptionsContentPane.setForeground(FOREGROUND_COLOR);
			}
			_programOptionsContentPane.setLayout(new BorderLayout());
		}
		return _programOptionsContentPane;
	}
	
	/**
	 * Getter for the View JMenu	
	 * 	
	 * @return JMenu	
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
	 * Getter for the View Input Panel JMenuItem.
	 * 
	 * Switches the view to the input panel.
	 * 	
	 * @return JMenuItem	
	 */
	public JRadioButtonMenuItem getViewInputMenuItem() {
		if (_viewInputMenuItem == null) {
			_viewInputMenuItem = new JRadioButtonMenuItem();
			_viewInputMenuItem.setText("View Input Panel");
			_viewInputMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,	Event.CTRL_MASK, true));
			_viewInputMenuItem.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getInputPanel().setSize(_jFrame.getContentPane().getSize());
					setMainContentPane(getInputPanel());
					getMainContentAndToolbarPane().repaint();
					getInputPanel().repaintAll();
				}
			});
		}
		return _viewInputMenuItem;
	}
	
	/**
	 * Getter for the View Management Panel JMenuItem.	
	 * 
	 * Switches the view to the management panel.
	 * 	
	 * @return JMenuItem	
	 */
	public JRadioButtonMenuItem getViewManagementMenuItem() {
		if (_viewManagementMenuItem == null) {
			_viewManagementMenuItem = new JRadioButtonMenuItem();
			_viewManagementMenuItem.setText("View Management Panel");
			_viewManagementMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK, true));
			_viewManagementMenuItem.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getManagementPanel().setSize(_jFrame.getSize());
					setMainContentPane(getManagementPanel());
					getMainContentAndToolbarPane().repaint();
					getManagementPanel().repaintAll();
				}
			});
		}
		return _viewManagementMenuItem;
	}
	
	/**
	 * Getter for the Edit JMenu	
	 * 	
	 * @return JMenu	
	 */
	public JMenu getEditMenu() {
		if (_editMenu == null) {
			_editMenu = new JMenu();
			_editMenu.setText("Edit");
			_editMenu.add(getCreateRoundMenuItem());
			List<Grouping> groupings = _middleEnd.getTournament().getCategories();
			for (Grouping<Unit> g : groupings) {
				_editMenu.add(createEditMenuItem(g));
			}
		}
		return _editMenu;
	}
	
	/**
	 * Getter for the Create Round JMenuItem.	
	 * 
	 * Creates a new round.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getCreateRoundMenuItem() {
		JMenuItem item = new JMenuItem();
		item.setText("Create new round from existing units...");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					_middleEnd.getTournament().createNextRound(false);
					getViewManagementMenuItem().doClick();
				}catch(InvalidRoundException err){ //Thrown if a valid tournament cannot be created with the current unit setup
//					int result = JOptionPane.showConfirmDialog(getJFrame(), err.getMessage(), "Round Creation Error", JOptionPane.ERROR_MESSAGE);
					String[] options = {"Continue anyway", "Abort"};
					int result = JOptionPane.showOptionDialog(getJFrame(), err.getMessage(),
							"Round Creation Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,
							null, options, options[1]);
					if (result == JOptionPane.YES_OPTION) {
						try {
							_middleEnd.getTournament().createNextRound(true);
							getViewManagementMenuItem().doClick();
						} catch (InvalidRoundException e1) {
							JOptionPane.showMessageDialog(getJFrame(), e1.getMessage(), "Round Creation Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		return item;
	}
	
	/**
	 * Creates a JMenuItem to create a new unit of the given grouping.	
	 * 
	 * Allows the user to create a new unit of the given grouping.
	 * 
	 * @param Grouping	
	 * @return JMenuItem	
	 */
	public JMenuItem createEditMenuItem(final Grouping<Unit> g) {
		JMenuItem item = new JMenuItem();
		item.setText("New " + g.getName() + "...");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInputPanel().getAddingPanel().setAddPanel(g);
				getViewInputMenuItem().doClick();
			}
		});
		return item;
	}
	
	/**
	 * Getter for the Help JMenu.	
	 * 	
	 * @return JMenu	
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
	 * Getter for the About JMenuItem.
	 * 	
	 * @return JMenuItem	
	 */
	public JMenuItem getAboutMenuItem() {
		if (_aboutMenuItem == null) {
			_aboutMenuItem = new JMenuItem();
			_aboutMenuItem.setText("About...");
			_aboutMenuItem.addActionListener(new ActionListener() {
				@Override
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
	 * Getter for the About Dialog box.
	 * 	
	 * @return JDialog
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
	 * Getter for the content pane of the About Dialog box.
	 * 
	 * @return JPanel
	 */
	public JPanel getAboutContentPane() {
		if (_aboutContentPane == null) {
			_aboutContentPane = new JPanel();
			if (COLORSON) {
				_aboutContentPane.setBackground(BACKGROUND_COLOR);
				_aboutContentPane.setForeground(FOREGROUND_COLOR);
			}
			_aboutContentPane.setLayout(new BorderLayout());
			_aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return _aboutContentPane;
	}

	/**
	 * Getter for the about version label.
	 * 	
	 * @return JLabel	
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
	 * Repaints all the important components, calls repaintAll on each of them
	 * so that they can recursively do the same, effectively repainting all components
	 * of the GUI.
	 */
	public void repaintAll() {
		getInputPanel().repaintAll();
		getManagementPanel().repaintAll();
		getMainContentAndToolbarPane().repaint();
	}

}
