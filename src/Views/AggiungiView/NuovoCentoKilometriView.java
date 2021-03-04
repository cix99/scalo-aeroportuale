package Views.AggiungiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class NuovoCentoKilometriView extends JPanel {

	private JPanel mainPanel;
	private JLabel menuLabel;
	private JPanel buttonPanel;
	private JButton salvaButton;
	private JLabel compagniaLabel;
	private JComboBox<String> compagniaComboBox;
	private JLabel codiceLabel;
	private JTextField codiceTextField;
	private JLabel nomeLabel;
	private JTextField nomeTextField;
	private JLabel cognomeLabel;
	private JTextField cognomeTextField;
	private JLabel puntiLabel;
	private JTextField puntiTextField;
	
	private ViewsController controller;
	
	public NuovoCentoKilometriView (ViewsController viewsController) {			
		controller = viewsController;
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		menuLabel = new JLabel("   Nuovo Cento Kilometri");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		compagniaLabel = new JLabel("Nome Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaLabel.setMinimumSize(new Dimension(100, 30));
		compagniaComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		compagniaComboBox.setEditable(false);
		compagniaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		codiceLabel = new JLabel("Codice");
		codiceLabel.setForeground(Color.WHITE);
		codiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codiceLabel.setMinimumSize(new Dimension(100, 30));
		codiceTextField = new JTextField();
		codiceTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codiceTextField.setColumns(5);
		codiceTextField.setMinimumSize(new Dimension(50,30));		
		codiceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int pos = codiceTextField.getCaretPosition();
				codiceTextField.setText(codiceTextField.getText().toUpperCase());
				codiceTextField.setCaretPosition(pos);
			}
		});
		
		nomeLabel = new JLabel("Nome");
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeLabel.setMinimumSize(new Dimension(100, 30));
		nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeTextField.setColumns(10);
		nomeTextField.setMinimumSize(new Dimension(50,30));
		
		cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeLabel.setMinimumSize(new Dimension(100, 30));
		cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setColumns(10);
		cognomeTextField.setMinimumSize(new Dimension(50,30));
		
		puntiLabel = new JLabel("Punti");
		puntiLabel.setForeground(Color.WHITE);
		puntiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		puntiLabel.setMinimumSize(new Dimension(100, 30));
		puntiTextField = new JTextField();
		puntiTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		puntiTextField.setColumns(5);
		puntiTextField.setMinimumSize(new Dimension(50,30));		
		puntiTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		
		JPanel topPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(new Color (0, 0, 153));
		TitledBorder topTitleBorder = new TitledBorder("Info compagnia");
		topTitleBorder.setTitleColor(Color.WHITE);
		topTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		topPanel.setBorder(topTitleBorder);
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaComboBox, BorderLayout.SOUTH);
		JPanel codicePanel = new JPanel(new BorderLayout());
		codicePanel.setBackground(new Color(0, 0, 153));
		codicePanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		codicePanel.add(codiceLabel, BorderLayout.WEST);
		codicePanel.add(codiceTextField, BorderLayout.SOUTH);
		
		topPanel.add(compagniaPanel);
		topPanel.add(codicePanel);
		
		JPanel centerPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		centerPanel.setBackground(new Color (0, 0, 153));
		TitledBorder centerTitleBorder = new TitledBorder("Info cliente");
		centerTitleBorder.setTitleColor(Color.WHITE);
		centerTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		centerPanel.setBorder(centerTitleBorder);
		JPanel nomePanel = new JPanel(new BorderLayout());
		nomePanel.setBackground(new Color(0, 0, 153));
		nomePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		nomePanel.add(nomeLabel, BorderLayout.WEST);
		nomePanel.add(nomeTextField, BorderLayout.SOUTH);
		JPanel cognomePanel = new JPanel(new BorderLayout());
		cognomePanel.setBackground(new Color(0, 0, 153));
		cognomePanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		cognomePanel.add(cognomeLabel, BorderLayout.WEST);
		cognomePanel.add(cognomeTextField, BorderLayout.SOUTH);
		
		centerPanel.add(nomePanel);
		centerPanel.add(cognomePanel);
		
		JPanel bottomPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		bottomPanel.setBackground(new Color (0, 0, 153));
		TitledBorder bottomTitleBorder = new TitledBorder("Info extra");
		bottomTitleBorder.setTitleColor(Color.WHITE);
		bottomTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		bottomPanel.setBorder(bottomTitleBorder);
		JPanel puntiPanel = new JPanel(new BorderLayout());
		puntiPanel.setBackground(new Color(0, 0, 153));
		puntiPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		puntiPanel.add(puntiLabel, BorderLayout.WEST);
		puntiPanel.add(puntiTextField, BorderLayout.SOUTH);
		
		bottomPanel.add(puntiPanel);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		//gc.gridwidth = 1;
		//gc.insets = new Insets(10,20,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(topPanel, gc);
		gc.gridx = 0;  
		gc.gridy = 1;
		gc.insets = new Insets(20,0,0,0);
		mainPanel.add(centerPanel, gc);
		gc.gridx = 0;  
		gc.gridy = 2;
		gc.insets = new Insets(20,0,0,0);
		mainPanel.add(bottomPanel, gc);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(new Color(0, 0, 153));
		
		salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);
		
		buttonPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		salvaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.saveNuovoCentoKilometri(codiceTextField.getText(), compagniaComboBox.getSelectedItem().toString(), 
												   nomeTextField.getText(), cognomeTextField.getText(), puntiTextField.getText());
			}
		});
	}
}
