package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controllers.DatabaseController;

@SuppressWarnings("serial")
public class ImbarcoView extends JPanel{

	private JLabel gateLabel;
	private JComboBox gateComboBox;
	private String[] items = { "GATE1", "GATE2", "GATE3" };
	private JTextField gateText;
	private JButton okButton;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	
	private CardLayout cl = new CardLayout();
	
	public ImbarcoView () {	
		setPreferredSize (new Dimension(800,650));
		//setBackground(new Color(0, 0, 0));
		//setLayout(new CardLayout());
		setLayout(new BorderLayout(0, 0));
		
		topPanel = new JPanel();
		//topPanel.setBackground(new Color (0,0,153));
		topPanel.setLayout(new FlowLayout());
		topPanel.setMinimumSize(new Dimension(800,500));
		gateLabel = new JLabel("Gate ");
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(gateLabel);
		gateComboBox = new JComboBox<String>(items);
		//gateChoice.setBounds(20, 5, 5, 5);
		//gateComboBox.setSize(new Dimension (20,10));
		
		
		//gateComboBox.setEditable(false);
		//gateComboBox.addItemListener(items);
		
		//pane.add(comboBoxPane, BorderLayout.PAGE_START);
		
		gateText = new JTextField(20);
		
		
		okButton = new JButton("ok");
		
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//controller.login(usernameField.getText(), passwordField.getPassword());
				
			}
		});
		
		topPanel.add(gateText);
		topPanel.add(okButton);
		topPanel.add(gateComboBox);
		
		//cl.show(topPanel, "2");

		add(topPanel, BorderLayout.NORTH);
		
		//tratta = DatabaseController...
		//JLabel l = new JLabel (tratta.getDestinazione())
		//add(centerPanel, BorderLayout.WEST);
		//add(bottomPanel, BorderLayout.CENTER);
		
	}
}
