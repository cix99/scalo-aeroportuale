package Views.AggiungiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Controllers.ViewsController;
import Models.CentoKilometri;
import Models.Coda;
import Models.CompagniaAerea;
import Models.Tratta;

@SuppressWarnings("serial")
public class NuovaPrenotazioneView extends JPanel {
	
	private int idTratta;
	private Coda coda;
    
	int[] idList;
	
	private JPanel mainPanel;
	private JLabel nomeLabel;
	private JTextField nomeTextField;
	private JLabel cognomeLabel;
	private JTextField cognomeTextField;
	private JLabel codicePrenotazioneLabel;
	private JTextField codicePrenotazioneTextField;
	private JLabel centoKilometriLabel;
	private JTextField centoKilometriTextField;
	private JLabel compagniaLabel;
	private JComboBox<String> compagniaComboBox;
	private JLabel trattaLabel;
	private JComboBox<String> trattaComboBox;
	private JLabel codaLabel;
	private JComboBox<String> codaComboBox;

	public NuovaPrenotazioneView (ViewsController controller) {
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		JLabel menuLabel = new JLabel("   Nuova Prenotazione");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		nomeLabel = new JLabel("Nome");
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeLabel.setMinimumSize(new Dimension(100, 30));
		nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeTextField.setColumns(15);
		nomeTextField.setMinimumSize(new Dimension(290,30));
		
		cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setColumns(15);
		cognomeTextField.setMinimumSize(new Dimension(290,30));
		
		codicePrenotazioneLabel = new JLabel("Codice");
		codicePrenotazioneLabel.setForeground(Color.WHITE);
		codicePrenotazioneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codicePrenotazioneLabel.setMinimumSize(new Dimension(100, 30));
		codicePrenotazioneTextField = new JTextField();
		codicePrenotazioneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codicePrenotazioneTextField.setColumns(10);
		codicePrenotazioneTextField.setMinimumSize(new Dimension(195, 30));
		
		centoKilometriLabel = new JLabel("Cento Kilometri");
		centoKilometriLabel.setForeground(Color.WHITE);
		centoKilometriLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		centoKilometriTextField = new JTextField();
		centoKilometriTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		centoKilometriTextField.setColumns(10);
		centoKilometriTextField.setMinimumSize(new Dimension(195, 30));
		
		compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		compagniaComboBox.setEditable(false);
		compagniaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		trattaLabel = new JLabel("Tratte");
		trattaLabel.setForeground(Color.WHITE);
		trattaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
//		trattaComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		trattaComboBox = new JComboBox<String>();
		trattaComboBox.setEditable(false);
		trattaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		codaLabel = new JLabel("Coda");
		codaLabel.setForeground(Color.WHITE);
		codaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
//		codaComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		codaComboBox = new JComboBox<String>();
		codaComboBox.setEditable(false);
		codaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		GridBagConstraints gc = new GridBagConstraints();
		
//		gc.gridx = 0;  
//		gc.gridy = 0;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(0,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(nomeLabel, gc);
//		gc.gridx = 1;      
//		gc.gridy = 0;
//		gc.gridwidth = 2;
//		gc.insets = new Insets(0,20,0,0);
//		gc.anchor = GridBagConstraints.CENTER;
//		mainPanel.add(nomeTextField, gc);
//		
//		gc.gridx = 0;      
//		gc.gridy = 1;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		mainPanel.add(cognomeLabel, gc);
//		gc.gridx = 1;   
//		gc.gridy = 1;
//		gc.gridwidth = 2;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.CENTER;
//		mainPanel.add(cognomeTextField, gc);
//		
//		gc.gridx = 0;  
//		gc.gridy = 2;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		mainPanel.add(codicePrenotazioneLabel, gc);
//		gc.gridx = 1;     
//		gc.gridy = 2;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(codicePrenotazioneTextField, gc);
//		
//		gc.gridx = 0;  
//		gc.gridy = 3;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		mainPanel.add(centoKilometriLabel, gc);
//		gc.gridx = 1;     
//		gc.gridy = 3;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(centoKilometriTextField, gc);
//		
//		gc.gridx = 0;  
//		gc.gridy = 4;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.LINE_START;
//		mainPanel.add(compagniaLabel, gc);
//		gc.gridx = 1;     
//		gc.gridy = 4;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(compagnieComboBox, gc);
//----------------------------------------------------------------		
//		gc.gridx = 0;  
//		gc.gridy = 0;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(nomeLabel, gc);
//		gc.gridx = 0;      
//		gc.gridy = 1;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(nomeTextField, gc);
//		
//		gc.gridx = 1;      
//		gc.gridy = 0;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(cognomeLabel, gc);
//		gc.gridx = 1;   
//		gc.gridy = 1;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(cognomeTextField, gc);
//		
//		gc.gridx = 0;  
//		gc.gridy = 2;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(codicePrenotazioneLabel, gc);
//		gc.gridx = 0;     
//		gc.gridy = 3;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.WEST;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(codicePrenotazioneTextField, gc);
//		
//		gc.gridx = 1;  
//		gc.gridy = 2;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(centoKilometriLabel, gc);
//		gc.gridx = 1;     
//		gc.gridy = 3;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.WEST;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(centoKilometriTextField, gc);
//		
//		gc.gridx = 2;  
//		gc.gridy = 2;
//		gc.gridwidth = 1;
//		gc.insets = new Insets(10,20,0,0);
//		gc.anchor = GridBagConstraints.WEST;
//		mainPanel.add(compagniaLabel, gc);
//		gc.gridx = 2;     
//		gc.gridy = 3;
//		gc.gridwidth = 1;
//		gc.anchor = GridBagConstraints.WEST;
//		gc.insets = new Insets(10,20,0,0);
//		mainPanel.add(compagnieComboBox, gc);
	
		JPanel midTopPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midTopPanel.setBackground(new Color (0, 0, 153));
		JPanel nomePanel = new JPanel(new BorderLayout());
		nomePanel.setBackground(new Color(0, 0, 153));
		nomePanel.add(nomeLabel, BorderLayout.WEST);
		nomePanel.add(nomeTextField, BorderLayout.SOUTH);
		JPanel cognomePanel = new JPanel(new BorderLayout());
		cognomePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		cognomePanel.setBackground(new Color(0, 0, 153));
		cognomePanel.add(cognomeLabel, BorderLayout.WEST);
		cognomePanel.add(cognomeTextField, BorderLayout.SOUTH);
		midTopPanel.add(nomePanel);
		midTopPanel.add(cognomePanel);
		
		JPanel midCenterPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midCenterPanel.setBackground(new Color(0, 0, 153));
		JPanel prenotazionePanel = new JPanel(new BorderLayout());
		prenotazionePanel.setBackground(new Color(0, 0, 153));
		prenotazionePanel.add(codicePrenotazioneLabel, BorderLayout.WEST);
		prenotazionePanel.add(codicePrenotazioneTextField, BorderLayout.SOUTH);
		JPanel centoKilometriPanel = new JPanel(new BorderLayout());
		centoKilometriPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		centoKilometriPanel.setBackground(new Color(0, 0, 153));
		centoKilometriPanel.add(centoKilometriLabel, BorderLayout.WEST);
		centoKilometriPanel.add(centoKilometriTextField, BorderLayout.SOUTH);
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBorder(new EmptyBorder(0, 55, 0, 0));
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaComboBox, BorderLayout.SOUTH);
		midCenterPanel.add(prenotazionePanel);
		midCenterPanel.add(centoKilometriPanel);
		midCenterPanel.add(compagniaPanel);
		
		JPanel midBottomPanel = new JPanel();
		midBottomPanel.setBackground(new Color(0, 0, 153));
		JPanel trattaPanel = new JPanel(new BorderLayout());
		trattaPanel.setBorder(new EmptyBorder(0, 65, 0, 0));
		trattaPanel.setBackground(new Color(0, 0, 153));
		trattaPanel.add(trattaLabel, BorderLayout.WEST);
		trattaPanel.add(trattaComboBox, BorderLayout.SOUTH);
		JPanel codaPanel = new JPanel(new BorderLayout());
		codaPanel.setBorder(new EmptyBorder(0, 65, 0, 0));
		codaPanel.setBackground(new Color(0, 0, 153));
		codaPanel.add(codaLabel, BorderLayout.WEST);
		codaPanel.add(codaComboBox, BorderLayout.SOUTH);
		
		midBottomPanel.add(trattaPanel);
		midBottomPanel.add(codaPanel);
		
		gc.gridx = 0;  
		gc.gridy = 0;
		//gc.gridwidth = 1;
		//gc.insets = new Insets(10,20,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midTopPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 1;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(20,0,0,0);
		mainPanel.add(midCenterPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 2;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.WEST;
		//gc.insets = new Insets(10,20,0,0);
		mainPanel.add(midBottomPanel, gc);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		JButton annullaButton = new JButton("Annulla");
		annullaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		annullaButton.setFocusPainted(false);
		
		JButton salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);
		
		bottomPanel.add(annullaButton);
		bottomPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		compagniaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JComboBox<String> combo = (JComboBox<String>) e.getSource();
				LinkedList<Tratta> tratteList = controller.getTratteFromCompagnie(compagniaComboBox.getSelectedItem().toString());
				
				String [] tratteArray = new String[tratteList.size()];
				ListIterator<Tratta> tratteCursor = tratteList.listIterator();
				int i = 0;
				idList = new int[tratteList.size()];
				while (tratteCursor.hasNext()) {
					Tratta current = tratteCursor.next();
					idList[i] = current.getId();
					String time = current.getOraInizioImbarco().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
					tratteArray[i] = current.getDestinazione() + " - " + time;
					i++;
				}
				UpdateTrattaComboBox(tratteArray);
			}
		});
		
		trattaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JComboBox<String> combo = (JComboBox<String>) e.getSource();
				
				String [] codaArray = controller.getCodaFromIdTratta(idList[trattaComboBox.getSelectedIndex()]);
				UpdateCodaComboBox(codaArray);
			}
		});
	}
	
	public void UpdateTrattaComboBox (String[] trattaArray) {
		
		trattaComboBox.removeAllItems();
		
		for (int i = 0; i < trattaArray.length; i++) {
			trattaComboBox.addItem(trattaArray[i]);
			
		}
		
		mainPanel.repaint();
	}
	
	public void UpdateCodaComboBox (String[] codaArray) {
		
		codaComboBox.removeAllItems();
		
		for (int i = 0; i < codaArray.length; i++) {
			codaComboBox.addItem(codaArray[i]);
		}
		
		mainPanel.repaint();
	}

}
