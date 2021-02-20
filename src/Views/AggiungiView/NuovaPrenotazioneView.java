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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Controllers.ViewsController;
import Models.Coda;
import Models.Tratta;

@SuppressWarnings("serial")
public class NuovaPrenotazioneView extends JPanel {
	   
	private int[] idTrattaList;
	private int trattaIndex;
	
	private JPanel mainPanel;
	private JLabel nomeLabel;
	private JTextField nomeTextField;
	private JLabel cognomeLabel;
	private JTextField cognomeTextField;
	private JLabel codicePrenotazioneLabel;
	private JTextField codicePrenotazioneTextField;
	private JLabel centoKilometriLabel;
	private JTextField centoKilometriTextField;
	private JLabel compagniaCentoKilometriLabel;
	private JComboBox<String> compagniaCentoKilometriComboBox;
	private JLabel compagniaLabel;
	private JComboBox<String> compagniaComboBox;
	private JLabel trattaLabel;
	private JComboBox<String> trattaComboBox;
	private JLabel codaLabel;
	private JComboBox<String> codaComboBox;
	private JPanel bottomPanel;
	private JButton salvaButton;

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
		nomeTextField.setColumns(12);
		nomeTextField.setMinimumSize(new Dimension(50,30));
		
		cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setColumns(12);
		cognomeTextField.setMinimumSize(new Dimension(50,30));
		
		codicePrenotazioneLabel = new JLabel("Codice Prenotazione");
		codicePrenotazioneLabel.setForeground(Color.WHITE);
		codicePrenotazioneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codicePrenotazioneLabel.setMinimumSize(new Dimension(100, 30));
		codicePrenotazioneTextField = new JTextField();
		codicePrenotazioneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codicePrenotazioneTextField.setColumns(5);
		codicePrenotazioneTextField.setMinimumSize(new Dimension(20, 30));
		codicePrenotazioneTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int pos = codicePrenotazioneTextField.getCaretPosition();
				codicePrenotazioneTextField.setText(codicePrenotazioneTextField.getText().toUpperCase());
				codicePrenotazioneTextField.setCaretPosition(pos);
			}
		});
		
		centoKilometriLabel = new JLabel("Cento Kilometri");
		centoKilometriLabel.setForeground(Color.WHITE);
		centoKilometriLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		centoKilometriTextField = new JTextField();
		centoKilometriTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		centoKilometriTextField.setColumns(10);
		centoKilometriTextField.setMinimumSize(new Dimension(195, 30));
		centoKilometriTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int pos = centoKilometriTextField.getCaretPosition();
				centoKilometriTextField.setText(centoKilometriTextField.getText().toUpperCase());
				centoKilometriTextField.setCaretPosition(pos);
			}
		});
		
		
		compagniaCentoKilometriLabel = new JLabel("Compagnia");
		compagniaCentoKilometriLabel.setForeground(Color.WHITE);
		compagniaCentoKilometriLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaCentoKilometriComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		compagniaCentoKilometriComboBox.setEditable(false);
		compagniaCentoKilometriComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaComboBox = new JComboBox<String>(controller.getCompagnieAeree());
		compagniaComboBox.setEditable(false);
		compagniaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LinkedList<Tratta> tratteList = controller.getTratteFromCompagnia(compagniaComboBox.getSelectedItem().toString());
		
				String [] tratteArray = new String[tratteList.size()];
				ListIterator<Tratta> tratteCursor = tratteList.listIterator();
				trattaIndex = 0;
				idTrattaList = new int[tratteList.size()];
				while (tratteCursor.hasNext()) {
					Tratta current = tratteCursor.next();
					idTrattaList[trattaIndex] = current.getId();
					String time = current.getOraInizioImbarcoStimato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
					tratteArray[trattaIndex] = current.getDestinazione() + " - " + time;
					trattaIndex++;
				}
				UpdateTrattaComboBox(tratteArray);
			}
		});
		
		trattaLabel = new JLabel("Tratte");
		trattaLabel.setForeground(Color.WHITE);
		trattaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		trattaComboBox = new JComboBox<String>();
		trattaComboBox.setEditable(false);
		trattaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		trattaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				//Questo sta venendo chiamato anche quando non dovrebbe, cio� quando viene scelta una nuova compagnia
				//System.out.println("trattaComboBox.selected: " + trattaComboBox.getSelectedIndex());
				if (trattaComboBox.getSelectedIndex() != -1) {			//Questo sembra risolvere la situazione
					LinkedList<Coda> code = controller.getCodaFromIdTratta(idTrattaList[trattaComboBox.getSelectedIndex()]);
					String [] codeArray = new String[code.size()];
					ListIterator<Coda> cursor = code.listIterator();
					int i = 0;
					while (cursor.hasNext()) {
						Coda current = cursor.next();
						codeArray[i] = current.getNomeCoda();
						i++;
					}
					UpdateCodaComboBox(codeArray);
				}
				
			}
		});
		
		codaLabel = new JLabel("Coda");
		codaLabel.setForeground(Color.WHITE);
		codaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codaComboBox = new JComboBox<String>();
		codaComboBox.setEditable(false);
		codaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		GridBagConstraints gc = new GridBagConstraints();
	
		JPanel midTopPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midTopPanel.setBackground(new Color (0, 0, 153));
		TitledBorder topTitleBorder = new TitledBorder("Dati cliente");
		topTitleBorder.setTitleColor(Color.WHITE);
		topTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midTopPanel.setBorder(topTitleBorder);
		JPanel nomePanel = new JPanel(new BorderLayout());
		nomePanel.setBackground(new Color(0, 0, 153));
		nomePanel.add(nomeLabel, BorderLayout.WEST);
		nomePanel.add(nomeTextField, BorderLayout.SOUTH);
		JPanel cognomePanel = new JPanel(new BorderLayout());
		cognomePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		cognomePanel.setBackground(new Color(0, 0, 153));
		cognomePanel.add(cognomeLabel, BorderLayout.WEST);
		cognomePanel.add(cognomeTextField, BorderLayout.SOUTH);
		JPanel prenotazionePanel = new JPanel(new BorderLayout());
		prenotazionePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		prenotazionePanel.setBackground(new Color(0, 0, 153));
		prenotazionePanel.add(codicePrenotazioneLabel, BorderLayout.WEST);
		prenotazionePanel.add(codicePrenotazioneTextField, BorderLayout.SOUTH);
		midTopPanel.add(nomePanel);
		midTopPanel.add(cognomePanel);
		midTopPanel.add(prenotazionePanel);
		
		JPanel midCenterPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midCenterPanel.setBackground(new Color(0, 0, 153));
		TitledBorder centerTitleBorder = new TitledBorder("Dati cento kilometri");
		centerTitleBorder.setTitleColor(Color.WHITE);
		centerTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midCenterPanel.setBorder(centerTitleBorder);
		JPanel centoKilometriPanel = new JPanel(new BorderLayout());
		centoKilometriPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		centoKilometriPanel.setBackground(new Color(0, 0, 153));
		centoKilometriPanel.add(centoKilometriLabel, BorderLayout.WEST);
		centoKilometriPanel.add(centoKilometriTextField, BorderLayout.SOUTH);
		JPanel compagniaCentoKilometriPanel = new JPanel(new BorderLayout());
		compagniaCentoKilometriPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		compagniaCentoKilometriPanel.setBackground(new Color(0, 0, 153));
		compagniaCentoKilometriPanel.add(compagniaCentoKilometriLabel, BorderLayout.WEST);
		compagniaCentoKilometriPanel.add(compagniaCentoKilometriComboBox, BorderLayout.SOUTH);
		
		midCenterPanel.add(centoKilometriPanel);
		midCenterPanel.add(compagniaCentoKilometriPanel);
		
		JPanel midBottomPanel = new JPanel();
		midBottomPanel.setBackground(new Color(0, 0, 153));
		TitledBorder bottomTitleBorder = new TitledBorder("Dati volo");
		bottomTitleBorder.setTitleColor(Color.WHITE);
		bottomTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midBottomPanel.setBorder(bottomTitleBorder);
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaComboBox, BorderLayout.SOUTH);
		JPanel trattaPanel = new JPanel(new BorderLayout());
		trattaPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		trattaPanel.setBackground(new Color(0, 0, 153));
		trattaPanel.add(trattaLabel, BorderLayout.WEST);
		trattaPanel.add(trattaComboBox, BorderLayout.SOUTH);
		JPanel codaPanel = new JPanel(new BorderLayout());
		codaPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		codaPanel.setBackground(new Color(0, 0, 153));
		codaPanel.add(codaLabel, BorderLayout.WEST);
		codaPanel.add(codaComboBox, BorderLayout.SOUTH);
		
		midBottomPanel.add(compagniaPanel);
		midBottomPanel.add(trattaPanel);
		midBottomPanel.add(codaPanel);
		
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midTopPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(20,0,0,0);
		mainPanel.add(midCenterPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midBottomPanel, gc);
		
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);
		salvaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.saveNuovaPrenotazione(nomeTextField.getText(), cognomeTextField.getText(), codicePrenotazioneTextField.getText().toString(), 
						centoKilometriTextField.getText(), compagniaCentoKilometriComboBox.getSelectedItem().toString(), 
						compagniaComboBox.getSelectedItem().toString(), idTrattaList[trattaIndex], codaComboBox.getSelectedItem().toString());
				
			}
		});
		
		bottomPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
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
