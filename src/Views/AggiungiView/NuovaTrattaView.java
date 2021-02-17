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
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;
import Models.Tratta;

import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

@SuppressWarnings("serial")
public class NuovaTrattaView extends JPanel {
		
	int[] idList;
	
	private JPanel mainPanel;
	private JLabel destinazioneLabel;
	private JTextField destinazioneTextField;
	private JLabel compagniaLabel;
	private JTextField compagniaTextField;
	private JLabel dataLabel;
	private JLabel oraInizioLabel;
	private JLabel oraFineLabel;
	private JComboBox<String> hourStartComboBox;
	private JComboBox<String> minuteStartComboBox;
	private JComboBox<String> hourEndComboBox;
	private JComboBox<String> minuteEndComboBox;
	private String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", 
								"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	private String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", 
								"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", 
								"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", 
								"45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", };
	private JLabel codeLabel;
	private JComboBox<String> numeroCodeComboBox;
	private String[] numeroCode = {"1", "2", "3", "4", "5"};
	
	private JDialog codaDialog;
	
	public NuovaTrattaView (ViewsController controller, JFrame aggiungiFrame) {	
	
		setBorder(new EmptyBorder(10, 5, 10, 10));
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 153));
		
		JLabel menuLabel = new JLabel("   Nuova Tratta");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		
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
		compagniaTextField = new JTextField();
		compagniaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaTextField.setColumns(15);
		compagniaTextField.setMinimumSize(new Dimension(290,30));
		
		dataLabel = new JLabel("Data");
		dataLabel.setForeground(Color.WHITE);
		dataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		oraInizioLabel = new JLabel("Ora Inizio Imbarco");
		oraInizioLabel.setForeground(Color.WHITE);
		oraInizioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		hourStartComboBox = new JComboBox<String>(hours);
		hourStartComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		minuteStartComboBox = new JComboBox<String>(minutes);
		minuteStartComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		oraFineLabel = new JLabel("Ora Fine Imbarco");
		oraFineLabel.setForeground(Color.WHITE);
		oraFineLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		hourEndComboBox = new JComboBox<String>(hours);
		hourEndComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		minuteEndComboBox = new JComboBox<String>(minutes);
		minuteEndComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		codeLabel = new JLabel("Numero Code");
		codeLabel.setForeground(Color.WHITE);
		codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		numeroCodeComboBox = new JComboBox<String>(numeroCode);
		numeroCodeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		numeroCodeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int code;
		    	try {
		    	   code = Integer.parseInt(numeroCodeComboBox.getSelectedItem().toString());
		    	}
		    	catch (NumberFormatException en)
		    	{
		    	   code = 1;
		    	}
		    	//ottieni una lista di code una volta chiuso
		    	showCodaDialog(aggiungiFrame, code);
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		
		JPanel midTopPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midTopPanel.setBackground(new Color (0, 0, 153));
		JPanel destinazionePanel = new JPanel(new BorderLayout());
		destinazionePanel.setBackground(new Color(0, 0, 153));
		destinazionePanel.add(destinazioneLabel, BorderLayout.WEST);
		destinazionePanel.add(destinazioneTextField, BorderLayout.SOUTH);
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaTextField, BorderLayout.SOUTH);
		midTopPanel.add(destinazionePanel);
		midTopPanel.add(compagniaPanel);
		
		JPanel midCenterPanel = new JPanel();
		midCenterPanel.setBackground(new Color(0, 0, 153));
		JPanel dataPanel = new JPanel(new BorderLayout());
		dataPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		dataPanel.setBackground(new Color(0, 0, 153));
		dataPanel.add(dataLabel, BorderLayout.WEST);
		dataPanel.add(datePicker, BorderLayout.SOUTH);
		JPanel oraInizioPanel = new JPanel(new BorderLayout());
		oraInizioPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		oraInizioPanel.setBackground(new Color(0, 0, 153));
		oraInizioPanel.add(oraInizioLabel, BorderLayout.NORTH);
		JPanel startPanel = new JPanel(new FlowLayout());
		startPanel.setBackground(new Color(0, 0, 153));
		startPanel.add(hourStartComboBox);
		startPanel.add(minuteStartComboBox);
		oraInizioPanel.add(startPanel, BorderLayout.CENTER);
		JPanel oraFinePanel = new JPanel(new BorderLayout());
		oraFinePanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		oraFinePanel.setBackground(new Color(0, 0, 153));
		oraFinePanel.add(oraFineLabel, BorderLayout.NORTH);
		JPanel endPanel = new JPanel(new FlowLayout());
		endPanel.setBackground(new Color(0, 0, 153));
		endPanel.add(hourEndComboBox);
		endPanel.add(minuteEndComboBox);
		oraFinePanel.add(endPanel, BorderLayout.CENTER);
		
		midCenterPanel.add(dataPanel);
		midCenterPanel.add(oraInizioPanel);
		midCenterPanel.add(oraFinePanel);
		
		JPanel midBottomPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
		midBottomPanel.setBackground(new Color (0, 0, 153));
		JPanel codePanel = new JPanel(new BorderLayout());
		codePanel.setBackground(new Color(0, 0, 153));
		codePanel.add(codeLabel, BorderLayout.WEST);
		codePanel.add(numeroCodeComboBox, BorderLayout.SOUTH);
		midBottomPanel.add(codePanel);
		
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midTopPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 1;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midCenterPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 2;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(midBottomPanel, gc);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(0, 0, 153));
		
		JButton salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		salvaButton.setFocusPainted(false);

		bottomPanel.add(salvaButton);
		
		add(menuLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
	}

	public void showCodaDialog (JFrame aggiungiFrame, int numeroCode) {
		String[] priorities = {"0", "1", "2", "3", "4", "5"};
		
		codaDialog = new JDialog(aggiungiFrame, "Code", true);
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
		priority1ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda1TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeCoda1TextField.setColumns(10);
		nomeCoda1TextField.setMinimumSize(new Dimension(250,30));
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
		
		if (numeroCode >= 2) {
			JTextField nomeCoda2TextField = new JTextField();
			JComboBox<String> priority2ComboBox = new JComboBox<String>(priorities);
			priority2ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda2TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda2TextField.setColumns(10);
			nomeCoda2TextField.setMinimumSize(new Dimension(250,30));
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
			JTextField nomeCoda3TextField = new JTextField();
			JComboBox<String> priority3ComboBox = new JComboBox<String>(priorities);
			priority3ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda3TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda3TextField.setColumns(10);
			nomeCoda3TextField.setMinimumSize(new Dimension(250,30));
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
			JTextField nomeCoda4TextField = new JTextField();
			JComboBox<String> priority4ComboBox = new JComboBox<String>(priorities);
			priority4ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda4TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda4TextField.setColumns(10);
			nomeCoda4TextField.setMinimumSize(new Dimension(250,30));				
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
			JTextField nomeCoda5TextField = new JTextField();
			JComboBox<String> priority5ComboBox = new JComboBox<String>(priorities);
			priority5ComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda5TextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			nomeCoda5TextField.setColumns(10);
			nomeCoda5TextField.setMinimumSize(new Dimension(250,30));			
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
    	
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setBackground(new Color(0, 204, 255));
    	JButton aggiungiButton = new JButton("Aggiungi");
    	buttonPanel.add(aggiungiButton);
    	codaDialog.add(buttonPanel, BorderLayout.SOUTH);
    	
    	aggiungiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Save values into new code and go back to nuova tratta view
				//Show error if a name already exists, if a coda has already a given priority or if more code have the same priority
				//Add check of how many coda there are (numeroCode)
				codaDialog.dispose();
			}
		});
    	
    	codaDialog.setLocationRelativeTo(null);
    	codaDialog.setVisible(true);
	}
	
	
}
