package Views.AggiungiView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class NuovaPrenotazioneView extends JPanel {
	
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	private JLabel compagniaLabel;
	private JTextField nomeTextField;
	private JTextField cognomeTextField;


	public NuovaPrenotazioneView () {
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new GridBagLayout());
		setBackground(new Color(0, 0, 153));
		
		nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeTextField.setColumns(10);
		
		cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setColumns(10);
		
		JButton annullaButton = new JButton("Annulla");
		annullaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JButton salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(nomeLabel, gc);
		gc.gridx = 1;      
		gc.gridy = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(nomeTextField, gc);
		
		gc.gridx = 0;      
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10,0,0,0);
		gc.anchor = GridBagConstraints.LINE_END;
		add(cognomeLabel, gc);
		gc.gridx = 1;   
		gc.gridy = 1;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(cognomeTextField, gc);
		
		gc.gridx = 0;  
		gc.gridy = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(10,0,0,0);
		gc.anchor = GridBagConstraints.LINE_END;
		add(compagniaLabel, gc);
		gc.gridx = 1;     
		gc.gridy = 2;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10,20,0,0);
		add(comboBox, gc);
	
	}

}
