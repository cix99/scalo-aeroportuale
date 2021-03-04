package Views.AggiungiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class NuovoGateView extends JPanel {
	
	private JPanel mainPanel;
	private JLabel menuLabel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JButton salvaButton;
	private JPanel gatePanel;
	private JLabel gateLabel;
	private JTextField gateTextField;
	
	private ViewsController controller;

	public NuovoGateView (ViewsController viewsController) {			
		controller = viewsController;
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		menuLabel = new JLabel("   Nuovo Gate");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		gateLabel = new JLabel("Nome Gate");
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		gateLabel.setMinimumSize(new Dimension(100, 30));
		gateTextField = new JTextField();
		gateTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		gateTextField.setColumns(5);
		gateTextField.setMinimumSize(new Dimension(50,30));
		
		centerPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		centerPanel.setBackground(new Color (0, 0, 153));
		gatePanel = new JPanel(new BorderLayout());
		gatePanel.setBackground(new Color(0, 0, 153));
		gatePanel.add(gateLabel, BorderLayout.WEST);
		gatePanel.add(gateTextField, BorderLayout.SOUTH);
		
		centerPanel.add(gatePanel);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(centerPanel, gc);

		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);
		
		bottomPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		salvaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.saveNuovoGate(gateTextField.getText());
			}
		});
	}

}
