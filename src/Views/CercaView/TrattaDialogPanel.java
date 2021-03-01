package Views.CercaView;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controllers.ViewsController;
import Models.Coda;
import Views.AggiungiView.DateLabelFormatter;

@SuppressWarnings("serial")
public class TrattaDialogPanel extends JPanel {

	private JPanel mainPanel;
	private JLabel destinazioneLabel;
	private JTextField destinazioneTextField;
	private JLabel compagniaLabel;
	private JComboBox<String> compagniaComboBox;
	private JLabel gateLabel;
	private JComboBox<String> gateComboBox;
	private JLabel dataStartLabel;
	private JDatePanelImpl datePanelStart;
	private JDatePickerImpl datePickerStart;
	private JLabel oraInizioLabel;
	private JComboBox<String> hourStartComboBox;
	private JComboBox<String> minuteStartComboBox;
	private JLabel dataEndLabel;
	private JDatePanelImpl datePanelEnd;
	private JDatePickerImpl datePickerEnd;
	private JLabel oraFineLabel;
	private JComboBox<String> hourEndComboBox;
	private JComboBox<String> minuteEndComboBox;

	private JLabel codeLabel;
	private JButton codeButton;
	private ArrayList<Coda> codaList;
	private JLabel maxPrenotazioniLabel;
	private JTextField maxPrenotazioniTextField;
	
	private int numeroCodeAttuale;
	
	private String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", 
			  "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	private String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", 
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", 
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", 
				"45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", };
	
	private ViewsController viewsController;
	private CercaView cercaView;
	
	public TrattaDialogPanel(ViewsController viewsController, CercaView cercaView) {
		this.viewsController = viewsController;
		this.cercaView = cercaView;
		
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(0, 0, 153));
		
		destinazioneLabel = new JLabel("Destinazione");
		destinazioneLabel.setForeground(Color.WHITE);
		destinazioneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		destinazioneLabel.setMinimumSize(new Dimension(100, 30));
		destinazioneTextField = new JTextField();
		destinazioneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		destinazioneTextField.setColumns(15);
		destinazioneTextField.setMinimumSize(new Dimension(290,30));
		
		compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaComboBox = new JComboBox<String>(viewsController.getCompagnieAeree());
		compagniaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		gateLabel = new JLabel ("Gate");
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		gateComboBox = new JComboBox<String>(viewsController.getGates());
		gateComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		dataStartLabel = new JLabel("Data");
		dataStartLabel.setForeground(Color.WHITE);
		dataStartLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		UtilDateModel utilDateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanelStart = new JDatePanelImpl(utilDateModel, p);
		datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
		
		oraInizioLabel = new JLabel("Ora");
		oraInizioLabel.setForeground(Color.WHITE);
		oraInizioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		hourStartComboBox = new JComboBox<String>(hours);
		hourStartComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		minuteStartComboBox = new JComboBox<String>(minutes);
		minuteStartComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		dataEndLabel = new JLabel("Data");
		dataEndLabel.setForeground(Color.WHITE);
		dataEndLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		datePanelEnd = new JDatePanelImpl(model2, p2);
		datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());
		
		oraFineLabel = new JLabel("Ora");
		oraFineLabel.setForeground(Color.WHITE);
		oraFineLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		hourEndComboBox = new JComboBox<String>(hours);
		hourEndComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		minuteEndComboBox = new JComboBox<String>(minutes);
		minuteEndComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		codeLabel = new JLabel("Code");
		codeLabel.setForeground(Color.WHITE);
		codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codeButton = new JButton ("Vedi code");
		codeButton.setFocusPainted(false);
		codeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCodaDialog();
			}
		});

		maxPrenotazioniLabel = new JLabel("Max Prenotazioni");
		maxPrenotazioniLabel.setForeground(Color.WHITE);
		maxPrenotazioniLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		maxPrenotazioniLabel.setMinimumSize(new Dimension(100, 30));
		maxPrenotazioniTextField = new JTextField();
		maxPrenotazioniTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		maxPrenotazioniTextField.setColumns(5);
		maxPrenotazioniTextField.setMinimumSize(new Dimension(70,30));
		maxPrenotazioniTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		
		JPanel midTopPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midTopPanel.setBackground(new Color (0, 0, 153));
		TitledBorder topTitleBorder = new TitledBorder("Info principali");
		topTitleBorder.setTitleColor(Color.WHITE);
		topTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midTopPanel.setBorder(topTitleBorder);
		JPanel destinazionePanel = new JPanel(new BorderLayout());
		destinazionePanel.setBackground(new Color(0, 0, 153));
		destinazionePanel.add(destinazioneLabel, BorderLayout.WEST);
		destinazionePanel.add(destinazioneTextField, BorderLayout.SOUTH);
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaComboBox, BorderLayout.SOUTH);
		JPanel gatePanel = new JPanel(new BorderLayout());
		gatePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		gatePanel.setBackground(new Color(0, 0, 153));
		gatePanel.add(gateLabel, BorderLayout.WEST);
		gatePanel.add(gateComboBox, BorderLayout.SOUTH);
		midTopPanel.add(destinazionePanel);
		midTopPanel.add(compagniaPanel);
		midTopPanel.add(gatePanel);
		
		JPanel midCenterTopPanel = new JPanel();
		midCenterTopPanel.setBackground(new Color(0, 0, 153));
		TitledBorder centerTopTitleBorder = new TitledBorder("Inizio Imbarco");
		centerTopTitleBorder.setTitleColor(Color.WHITE);
		centerTopTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midCenterTopPanel.setBorder(centerTopTitleBorder);
		JPanel dataStartPanel = new JPanel(new BorderLayout());
		dataStartPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		dataStartPanel.setBackground(new Color(0, 0, 153));
		dataStartPanel.add(dataStartLabel, BorderLayout.WEST);
		dataStartPanel.add(datePickerStart, BorderLayout.SOUTH);
		JPanel oraInizioPanel = new JPanel(new BorderLayout());
		oraInizioPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		oraInizioPanel.setBackground(new Color(0, 0, 153));
		oraInizioPanel.add(oraInizioLabel, BorderLayout.NORTH);
		JPanel startPanel = new JPanel(new FlowLayout());
		startPanel.setBackground(new Color(0, 0, 153));
		startPanel.add(hourStartComboBox);
		startPanel.add(minuteStartComboBox);
		oraInizioPanel.add(startPanel, BorderLayout.CENTER);
		midCenterTopPanel.add(dataStartPanel);
		midCenterTopPanel.add(oraInizioPanel);
		
		JPanel midCenterBottomPanel = new JPanel();
		midCenterBottomPanel.setBackground(new Color(0, 0, 153));
		TitledBorder centerBottomTitleBorder = new TitledBorder("Fine Imbarco");
		centerBottomTitleBorder.setTitleColor(Color.WHITE);
		centerBottomTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midCenterBottomPanel.setBorder(centerBottomTitleBorder);
		JPanel dataEndPanel = new JPanel(new BorderLayout());
		dataEndPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		dataEndPanel.setBackground(new Color(0, 0, 153));
		dataEndPanel.add(dataEndLabel, BorderLayout.WEST);
		dataEndPanel.add(datePickerEnd, BorderLayout.SOUTH);
		JPanel oraFinePanel = new JPanel(new BorderLayout());
		oraFinePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		oraFinePanel.setBackground(new Color(0, 0, 153));
		oraFinePanel.add(oraFineLabel, BorderLayout.NORTH);
		JPanel endPanel = new JPanel(new FlowLayout());
		endPanel.setBackground(new Color(0, 0, 153));
		endPanel.add(hourEndComboBox);
		endPanel.add(minuteEndComboBox);
		oraFinePanel.add(endPanel, BorderLayout.CENTER);
		midCenterBottomPanel.add(dataEndPanel);
		midCenterBottomPanel.add(oraFinePanel);
		
		JPanel midBottomPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midBottomPanel.setBackground(new Color (0, 0, 153));
		TitledBorder bottomTitleBorder = new TitledBorder("Info secondarie");
		bottomTitleBorder.setTitleColor(Color.WHITE);
		bottomTitleBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 18));
		midBottomPanel.setBorder(bottomTitleBorder);
		JPanel codePanel = new JPanel(new BorderLayout());
		codePanel.setBackground(new Color(0, 0, 153));
		codePanel.add(codeLabel, BorderLayout.WEST);
		codePanel.add(codeButton, BorderLayout.SOUTH);
		JPanel maxPrenotazioniPanel = new JPanel(new BorderLayout());
		maxPrenotazioniPanel.setBackground(new Color(0, 0, 153));
		maxPrenotazioniPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		maxPrenotazioniPanel.add(maxPrenotazioniLabel, BorderLayout.WEST);
		maxPrenotazioniPanel.add(maxPrenotazioniTextField, BorderLayout.SOUTH);
		midBottomPanel.add(codePanel);
		midBottomPanel.add(maxPrenotazioniPanel);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midTopPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midCenterTopPanel, gc);
		gc.gridx = 1;     
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midCenterBottomPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 2;
		gc.gridwidth = 2;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midBottomPanel, gc);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		JButton aggiornaButton = new JButton("Aggiorna");
		aggiornaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		aggiornaButton.setFocusPainted(false);

		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDateTime dataInizio = viewsController.convertiIntToDate(datePickerStart.getJDateInstantPanel().getModel().getYear(), 
																		datePickerStart.getJDateInstantPanel().getModel().getMonth()+1,
																		datePickerStart.getJDateInstantPanel().getModel().getDay(),
																		Integer.parseInt(hourStartComboBox.getSelectedItem().toString()), 
																		Integer.parseInt(minuteStartComboBox.getSelectedItem().toString()));
				LocalDateTime dataFine = viewsController.convertiIntToDate(datePickerEnd.getJDateInstantPanel().getModel().getYear(), 
																		datePickerEnd.getJDateInstantPanel().getModel().getMonth()+1,
																		datePickerEnd.getJDateInstantPanel().getModel().getDay(),
																		Integer.parseInt(hourEndComboBox.getSelectedItem().toString()), 
																		Integer.parseInt(minuteEndComboBox.getSelectedItem().toString()));
				viewsController.saveNuovaTratta(destinazioneTextField.getText(), compagniaComboBox.getSelectedItem().toString(), gateComboBox.getSelectedItem().toString(),dataInizio, dataFine, maxPrenotazioniTextField.getText(), codaList);
			}
		});
		
		bottomPanel.add(aggiornaButton);
		
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void showCodaDialog () {
		String[] priorities = {"0", "1", "2", "3", "4", "5"};
		
		int numeroCode = codaList.size();
		
		JDialog codaDialog = new JDialog(cercaView, "Code", true);
    	codaDialog.setMinimumSize(new Dimension(650,450));
    	codaDialog.setLayout(new BorderLayout());
    	
    	JPanel mainPanelCD = new JPanel(new GridBagLayout());
    	mainPanelCD.setBackground(new Color(0, 0, 153));
    	
    	GridBagConstraints gc = new GridBagConstraints();
    
    	JPanel topPanel = new JPanel();
    	topPanel.setLayout(new FlowLayout());
    	JLabel nomeCodaLabel = new JLabel("Nome coda");
    	nomeCodaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
    	nomeCodaLabel.setForeground(Color.WHITE);
    	JLabel priorityLabel = new JLabel("Priority");
    	priorityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
    	priorityLabel.setForeground(Color.WHITE);
    	priorityLabel.setToolTipText("5 è la priorità più alta");
    	
       	gc.gridx = 0;  
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		mainPanelCD.add(nomeCodaLabel, gc);
		gc.gridx = 1;     
		gc.gridy = 0;
		gc.insets = new Insets(0,20,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanelCD.add(priorityLabel, gc);

		JTextField nomeCoda1TextField = new JTextField();
		JComboBox<String> priority1ComboBox = new JComboBox<String>(priorities);
		priority1ComboBox.setSelectedItem(String.valueOf(codaList.get(0).getPriority()));
		priority1ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda1TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda1TextField.setEnabled(false);
		nomeCoda1TextField.setColumns(10);
		nomeCoda1TextField.setMinimumSize(new Dimension(250,30));
		nomeCoda1TextField.setText(codaList.get(0).getNomeCoda());
		gc.gridx = 0;     
		gc.gridy = 1;
		gc.insets = new Insets(5,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanelCD.add(nomeCoda1TextField, gc);
		gc.gridx = 1;     
		gc.gridy = 1;
		gc.insets = new Insets(5,20,0,0);
		gc.anchor = GridBagConstraints.CENTER;
		mainPanelCD.add(priority1ComboBox, gc);
		
		JTextField nomeCoda2TextField = new JTextField();
		nomeCoda2TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda2TextField.setColumns(10);
		nomeCoda2TextField.setMinimumSize(new Dimension(250,30));
		JComboBox<String> priority2ComboBox = new JComboBox<String>(priorities);
		priority2ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JTextField nomeCoda3TextField = new JTextField();
		nomeCoda3TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda3TextField.setColumns(10);
		nomeCoda3TextField.setMinimumSize(new Dimension(250,30));
		JComboBox<String> priority3ComboBox = new JComboBox<String>(priorities);
		priority3ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JTextField nomeCoda4TextField = new JTextField();
		nomeCoda4TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda4TextField.setColumns(10);
		nomeCoda4TextField.setMinimumSize(new Dimension(250,30));
		JComboBox<String> priority4ComboBox = new JComboBox<String>(priorities);
		priority4ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JTextField nomeCoda5TextField = new JTextField();
		nomeCoda5TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda5TextField.setColumns(10);
		nomeCoda5TextField.setMinimumSize(new Dimension(250,30));
		JComboBox<String> priority5ComboBox = new JComboBox<String>(priorities);
		priority5ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		if (numeroCode >= 2) {
			nomeCoda2TextField.setText(codaList.get(1).getNomeCoda());
			nomeCoda2TextField.setEnabled(false);
			priority2ComboBox.setSelectedItem(String.valueOf(codaList.get(1).getPriority()));
			gc.gridx = 0;     
			gc.gridy = 2;
			gc.insets = new Insets(5,0,0,0);
			gc.anchor = GridBagConstraints.WEST;
			mainPanelCD.add(nomeCoda2TextField, gc);
			gc.gridx = 1;     
			gc.gridy = 2;
			gc.insets = new Insets(5,20,0,0);
			gc.anchor = GridBagConstraints.CENTER;
			mainPanelCD.add(priority2ComboBox, gc);
		}
		if (numeroCode >= 3) {
			nomeCoda3TextField.setText(codaList.get(2).getNomeCoda());
			nomeCoda3TextField.setEnabled(false);
			priority3ComboBox.setSelectedItem(String.valueOf(codaList.get(2).getPriority()));
			gc.gridx = 0;     
			gc.gridy = 3;
			gc.insets = new Insets(5,0,0,0);
			gc.anchor = GridBagConstraints.WEST;
			mainPanelCD.add(nomeCoda3TextField, gc);
			gc.gridx = 1;     
			gc.gridy = 3;
			gc.insets = new Insets(5,20,0,0);
			gc.anchor = GridBagConstraints.CENTER;
			mainPanelCD.add(priority3ComboBox, gc);
		}
		if (numeroCode >= 4) {
			nomeCoda4TextField.setText(codaList.get(3).getNomeCoda());
			nomeCoda4TextField.setEnabled(false);
			priority4ComboBox.setSelectedItem(String.valueOf(codaList.get(3).getPriority()));
			gc.gridx = 0;     
			gc.gridy = 4;
			gc.insets = new Insets(5,0,0,0);
			gc.anchor = GridBagConstraints.WEST;
			mainPanelCD.add(nomeCoda4TextField, gc);
			gc.gridx = 1;     
			gc.gridy = 4;
			gc.insets = new Insets(5,20,0,0);
			gc.anchor = GridBagConstraints.CENTER;
			mainPanelCD.add(priority4ComboBox, gc);
		}
		if (numeroCode >= 5) {
			nomeCoda5TextField.setText(codaList.get(4).getNomeCoda());
			nomeCoda5TextField.setEnabled(false);
			priority5ComboBox.setSelectedItem(String.valueOf(codaList.get(4).getPriority()));
			gc.gridx = 0;     
			gc.gridy = 5;
			gc.insets = new Insets(5,0,0,0);
			gc.anchor = GridBagConstraints.WEST;
			mainPanelCD.add(nomeCoda5TextField, gc);
			gc.gridx = 1;     
			gc.gridy = 5;
			gc.insets = new Insets(5,20,0,0);
			gc.anchor = GridBagConstraints.CENTER;
			mainPanelCD.add(priority5ComboBox, gc);
		}
		
    	codaDialog.add(mainPanelCD, BorderLayout.CENTER);
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	buttonPanel.setBackground(new Color(0, 204, 255));
    	JButton aggiungiButton = new JButton("Aggiungi coda");
    	aggiungiButton.setFocusPainted(false);
    	JButton salvaButton = new JButton("Salva");
    	salvaButton.setFocusPainted(false);
    	buttonPanel.add(aggiungiButton);
    	buttonPanel.add(salvaButton);
    	codaDialog.add(buttonPanel, BorderLayout.SOUTH);
    	
    	numeroCodeAttuale = numeroCode;
    	
    	aggiungiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (numeroCodeAttuale < 5) {
					switch (numeroCodeAttuale) {
						case 1: {
							gc.gridx = 0;     
							gc.gridy = 2;
							gc.insets = new Insets(5,0,0,0);
							gc.anchor = GridBagConstraints.WEST;
							mainPanelCD.add(nomeCoda2TextField, gc);
							gc.gridx = 1;     
							gc.gridy = 2;
							gc.insets = new Insets(5,20,0,0);
							gc.anchor = GridBagConstraints.CENTER;
							mainPanelCD.add(priority2ComboBox, gc);	
							mainPanelCD.revalidate();
							break;
						}
						case 2: {
							gc.gridx = 0;     
							gc.gridy = 3;
							gc.insets = new Insets(5,0,0,0);
							gc.anchor = GridBagConstraints.WEST;
							mainPanelCD.add(nomeCoda3TextField, gc);
							gc.gridx = 1;     
							gc.gridy = 3;
							gc.insets = new Insets(5,20,0,0);
							gc.anchor = GridBagConstraints.CENTER;
							mainPanelCD.add(priority3ComboBox, gc);
							mainPanelCD.revalidate();
							break;
						}
						case 3: {
							gc.gridx = 0;     
							gc.gridy = 4;
							gc.insets = new Insets(5,0,0,0);
							gc.anchor = GridBagConstraints.WEST;
							mainPanelCD.add(nomeCoda4TextField, gc);
							gc.gridx = 1;     
							gc.gridy = 4;
							gc.insets = new Insets(5,20,0,0);
							gc.anchor = GridBagConstraints.CENTER;
							mainPanelCD.add(priority4ComboBox, gc);
							mainPanelCD.revalidate();
							break;
						}
						case 4: {
							gc.gridx = 0;     
							gc.gridy = 5;
							gc.insets = new Insets(5,0,0,0);
							gc.anchor = GridBagConstraints.WEST;
							mainPanelCD.add(nomeCoda5TextField, gc);
							gc.gridx = 1;     
							gc.gridy = 5;
							gc.insets = new Insets(5,20,0,0);
							gc.anchor = GridBagConstraints.CENTER;
							mainPanelCD.add(priority5ComboBox, gc);
							mainPanelCD.revalidate();
							break;
						}
					}
				numeroCodeAttuale++;
				} else {
					JOptionPane.showMessageDialog(cercaView, "Limite massimo di code raggiunto", "Errore code", JOptionPane.ERROR_MESSAGE);
				}
				
			}
    	});
    	
    	salvaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codaList = new ArrayList<Coda>();
				if (!nomeCoda1TextField.getText().isBlank())
					codaList.add(new Coda(nomeCoda1TextField.getText(), Integer.parseInt(priority1ComboBox.getSelectedItem().toString())));
				if (numeroCode > 1) {
					for (int i = 2; i <= numeroCode; i++) {
						switch (i) {
							case 2: {
								if (!nomeCoda2TextField.getText().isBlank())
									codaList.add(new Coda(nomeCoda2TextField.getText(),Integer.parseInt(priority2ComboBox.getSelectedItem().toString())));
								break;
							}
							case 3: {
								if (!nomeCoda3TextField.getText().isBlank())
									codaList.add(new Coda(nomeCoda3TextField.getText(),Integer.parseInt(priority3ComboBox.getSelectedItem().toString())));
								break;
							}
							case 4: {
								if (!nomeCoda4TextField.getText().isBlank())
									codaList.add(new Coda(nomeCoda4TextField.getText(),Integer.parseInt(priority4ComboBox.getSelectedItem().toString())));
								break;
							}
							case 5: {
								if (!nomeCoda5TextField.getText().isBlank())
									codaList.add(new Coda(nomeCoda5TextField.getText(),Integer.parseInt(priority5ComboBox.getSelectedItem().toString())));
								break;
							}
							default:
								break;
						}		
					}
				}
				if (viewsController.checkCode(codaList))
					codaDialog.dispose();
			}
		});
    	
    	codaDialog.setLocationRelativeTo(null);
    	codaDialog.setVisible(true);
	}
	
	public void setDestinazione(String destinazione) {
		destinazioneTextField.setText(destinazione);
		destinazioneTextField.setEnabled(false);
	}

	public void setCompagniaAerea(String nomeCompagnia) {
		compagniaComboBox.setSelectedItem(nomeCompagnia);
		compagniaComboBox.setEnabled(false);
	}

	public void setInizioImbarcoStimato(String inizioImbarcoStimatoString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime inizioImbarcoStimato = LocalDateTime.parse(inizioImbarcoStimatoString, formatter);
		
		String hour = String.format("%02d", inizioImbarcoStimato.getHour());
		String minute = String.format("%02d", inizioImbarcoStimato.getMinute());
		hourStartComboBox.setSelectedItem(hour);
		minuteStartComboBox.setSelectedItem(minute);
			
		datePickerStart.getModel().setDate(inizioImbarcoStimato.getYear(), inizioImbarcoStimato.getMonthValue()-1, inizioImbarcoStimato.getDayOfMonth());
		datePickerStart.getModel().setSelected(true);
	}

	public void setFineImbarcoStimato(String fineImbarcoStimatoString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fineImbarcoStimato = LocalDateTime.parse(fineImbarcoStimatoString, formatter);
		
		String hour = String.format("%02d", fineImbarcoStimato.getHour());
		String minute = String.format("%02d", fineImbarcoStimato.getMinute());
		hourEndComboBox.setSelectedItem(hour);
		minuteEndComboBox.setSelectedItem(minute);
		
		datePickerEnd.getModel().setDate(fineImbarcoStimato.getYear(), fineImbarcoStimato.getMonthValue()-1, fineImbarcoStimato.getDayOfMonth());
		datePickerEnd.getModel().setSelected(true);
	}

	public void setGate(String nomeGate) {
		gateComboBox.setSelectedItem(nomeGate);
		
	}

	public void setMaxPrenotazioni(int maxPrenotazioni) {
		maxPrenotazioniTextField.setText("" + maxPrenotazioni);
	}

	public void setCode(LinkedList<Coda> code) {
		 codaList = new ArrayList<Coda>(code);
		
	}
}
