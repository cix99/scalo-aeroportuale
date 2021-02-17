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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class NuovoCentoKilometriView extends JPanel {

	private JPanel mainPanel;
	private JLabel compagniaLabel;
	private JTextField compagniaTextField;
	private JLabel codiceLabel;
	private JTextField codiceTextField;
	private JLabel puntiLabel;
	private JTextField puntiTextField;
	
	public NuovoCentoKilometriView (ViewsController controller) {			
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		JLabel menuLabel = new JLabel("   Nuova Cento Kilometri");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		compagniaLabel = new JLabel("Nome Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaLabel.setMinimumSize(new Dimension(100, 30));
		compagniaTextField = new JTextField();
		compagniaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaTextField.setColumns(15);
		compagniaTextField.setMinimumSize(new Dimension(150,30));
		
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
		
		JPanel centerPanel = new JPanel (new GridBagLayout());
		centerPanel.setBackground(new Color (0, 0, 153));
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaTextField, BorderLayout.SOUTH);
		JPanel codicePanel = new JPanel(new BorderLayout());
		codicePanel.setBackground(new Color(0, 0, 153));
		codicePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		codicePanel.add(codiceLabel, BorderLayout.WEST);
		codicePanel.add(codiceTextField, BorderLayout.SOUTH);
		JPanel puntiPanel = new JPanel(new BorderLayout());
		puntiPanel.setBackground(new Color(0, 0, 153));
		puntiPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		puntiPanel.add(puntiLabel, BorderLayout.WEST);
		puntiPanel.add(puntiTextField, BorderLayout.SOUTH);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		//gc.gridwidth = 1;
		//gc.insets = new Insets(10,20,0,0);
		gc.anchor = GridBagConstraints.WEST;
		centerPanel.add(compagniaPanel, gc);
		gc.gridx = 0;  
		gc.gridy = 1;
		gc.insets = new Insets(10,0,0,0);
		centerPanel.add(codicePanel, gc);
		gc.gridx = 0;  
		gc.gridy = 2;
		gc.insets = new Insets(10,0,0,0);
		centerPanel.add(puntiPanel, gc);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		JButton salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);
		
		bottomPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		salvaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.salvaNuovoCentoKilometri(codiceTextField.getText(), compagniaTextField.getText(), puntiTextField.getText());
			}
		});
	}
}
