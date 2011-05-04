package gui;

import backbone.*;
import middleend.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class UnitPanel extends JPanel {

	private MiddleEnd _middleEnd;
	private Utility _util;
	private JPanel _mainPanel, _buttonPanel, _tablePanel;
	
	public UnitPanel(MiddleEnd m, Unit u) {
		_middleEnd = m;
		_util = new Utility();
		_mainPanel = new JPanel();
		_buttonPanel = new JPanel();
		_tablePanel = new JPanel();
		initialize(u);
	}
	
	public void initialize(Unit u) {
		this.setLayout(new GridLayout(0,1));
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.X_AXIS));
		final HashMap<Attribute, InputTable> tables = new HashMap<Attribute, InputTable>();
		for (final Attribute a : u.getAttributes()) {
			if (a instanceof GroupingAttribute) {
				GroupingAttribute<Unit> g = (GroupingAttribute<Unit>) a;
				tables.put(a, new InputTable(_middleEnd, g.getMembers().get(0).getAttributes(), g.getMembers()));
			}
			JComponent c = _util.getField(a);
			if (c instanceof JButton) {
				((JButton) c).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (_tablePanel.getComponent(0) == tables.get(a)) {
							_tablePanel.removeAll();
						}
						else {
							_tablePanel.removeAll();
							_tablePanel.add(tables.get(a));
						}
					}
				});
			}
			_mainPanel.add(c);
		}
		this.add(_mainPanel);
		JButton savebutton = new JButton();
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		_buttonPanel.add(savebutton);
		this.add(_buttonPanel);
		this.add(_tablePanel);
	}
}
