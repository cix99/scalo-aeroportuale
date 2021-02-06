package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;
import Models.Prenotazione;
import Models.Tratta;

@SuppressWarnings("serial")
public class ImbarcoView extends JFrame{

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel backButtonPanel;
	private JButton backButton;
	private JPanel gateSelectionPanel;
	private JLabel gateLabel;
	private JComboBox<String> gateComboBox;
	private String[] items = { "1", "2", "3" };
	private JButton okButton;
	
	private JPanel centerPanel;
	private JLabel destinazioneLabel;
	private JLabel compagniaLabel;
	private JLabel dataLabel;
	private JLabel oraLabel;
	
	private JScrollPane scrollPane;
	private JTable table;
	private String[] columnNames = {"Coda", "Nome", "Cognome", "Codice", "CK", "Imbarcato"};
	private Object[][] data;
	// = {{"Premium", "Mario", "Rossi", "ABC303", "Si", "Si"}, {"Standard", "Luigi", "Verdi", "BDC127", "Si", "No"},
	//		{"Standard", "Wario", "Gialli", "ZBE329", "No", "No"}};

	public ImbarcoView (ViewsController controller) {	
		setTitle("Inizia Imbarco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 60, 1150, 650);
		setMinimumSize(new Dimension (1150,700));	
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0,0));
		
		backButtonPanel = new JPanel();
		backButtonPanel.setLayout(new FlowLayout());
		backButtonPanel.setBackground(new Color (0,0,153));
		backButtonPanel.setMinimumSize(new Dimension (50,10));
		backButton = new JButton("back");
		backButtonPanel.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.backToHomeView();
			}
		});
		topPanel.add(backButtonPanel, BorderLayout.WEST);
		
		gateSelectionPanel = new JPanel();
		gateSelectionPanel.setBackground(new Color (0,0,153));
		gateSelectionPanel.setLayout(new FlowLayout());
		gateSelectionPanel.setMinimumSize(new Dimension(300,100));
		
		gateLabel = new JLabel("Gate ");
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		gateSelectionPanel.add(gateLabel);
		gateComboBox = new JComboBox<String>(items);
		gateComboBox.setSize(new Dimension (20,10));
		gateSelectionPanel.add(gateComboBox);
		okButton = new JButton("Ok");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.loadImbarcoCenterPanel(mainPanel, gateComboBox.getSelectedItem().toString());

			}
		});
		gateSelectionPanel.add(okButton);
		topPanel.add(gateSelectionPanel, BorderLayout.CENTER);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		//mainPanel.add(okButton, BorderLayout.CENTER);
	}
	
	public void showTrattaInfoView (Tratta tratta) { //(Tratta tratta)
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		centerPanel.setBackground(new Color (0,0,153));
		centerPanel.setMinimumSize(new Dimension (500,100));
		
		destinazioneLabel = new JLabel("Volo per: " + tratta.getDestinazione()); //tratta.getDestinazione()
		destinazioneLabel.setForeground(Color.WHITE);
		destinazioneLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		compagniaLabel = new JLabel("Compagnia: " + tratta.getCompagniaAerea().getNomeCompagnia()); //tratta.getNomeCompagnia()
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		dataLabel = new JLabel("Data: " + tratta.getOraInizioImbarco().getDayOfMonth() + "/" + tratta.getOraInizioImbarco().getMonthValue() + "/" + tratta.getOraInizioImbarco().getYear()); //tratta.getData().toString()
		dataLabel.setForeground(Color.WHITE);
		dataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		oraLabel = new JLabel("Ora: " + tratta.getOraInizioImbarco().getHour() + ":" + tratta.getOraInizioImbarco().getMinute()); //tratta.getOraImbarco().toString()
		oraLabel.setForeground(Color.WHITE);
		oraLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		centerPanel.add(destinazioneLabel);
		centerPanel.add(Box.createHorizontalStrut(50));
		centerPanel.add(compagniaLabel);
		centerPanel.add(Box.createHorizontalStrut(50));
		centerPanel.add(dataLabel);
		centerPanel.add(Box.createHorizontalStrut(50));
		centerPanel.add(oraLabel);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
	}
	
	public void showListaPrenotati(LinkedList<Prenotazione> prenotati) { //(List prenotati) ?
		data = new Object[100][6];
		ListIterator<Prenotazione> cursor = prenotati.listIterator();
		for (int i = 0; cursor.hasNext(); i ++) {
				Prenotazione current = cursor.next();
				data[i][0] = current.getCoda().getNomeCoda();
				data[i][1] = current.getNomePasseggero();
				data[i][2] = current.getCognomePasseggero();
				data[i][3] = "AAA000";
				data[i][4] = "no";
				data[i][5] = "no";
		}
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		//table.setFillsViewportHeight(true);
		mainPanel.add(scrollPane, BorderLayout.SOUTH);
		mainPanel.revalidate();
	}
}
