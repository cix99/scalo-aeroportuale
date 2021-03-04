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
public class NuovaCompagniaView extends JPanel {

	private JPanel mainPanel;
	private JLabel menuLabel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JButton salvaButton;
	private JPanel compagniaPanel;
	private JLabel compagniaLabel;
	private JTextField compagniaTextField;
	
	private ViewsController controller;

	public NuovaCompagniaView (ViewsController viewsController) {			
		controller = viewsController;
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		menuLabel = new JLabel("   Nuova Compagnia Aerea");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		compagniaLabel = new JLabel("Nome Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaLabel.setMinimumSize(new Dimension(100, 30));
		compagniaTextField = new JTextField();
		compagniaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaTextField.setColumns(15);
		compagniaTextField.setMinimumSize(new Dimension(150,30));
		
		centerPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		centerPanel.setBackground(new Color (0, 0, 153));
		compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaTextField, BorderLayout.SOUTH);
		
		centerPanel.add(compagniaPanel);
		
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
				controller.saveNuovaCompagniaAerea(compagniaTextField.getText());
			}
		});
	}
}
