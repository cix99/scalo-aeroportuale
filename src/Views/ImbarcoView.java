package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.Prenotazione;
import Models.Tratta;
import Views.Tables.TableModelPrenotazione;

@SuppressWarnings("serial")
public class ImbarcoView extends JFrame{

	private JPanel mainPanel;
	private TopPanel topPanel;
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
	private TableModelPrenotazione tableModel;
	//private String[] columnNames = {"Coda", "Nome", "Cognome", "Codice", "CK", "Imbarcato"};
	//private Object[][] data = {{"Premium", "Mario", "Rossi", "ABC303", "Si", "Si"}, {"Standard", "Luigi", "Verdi", "BDC127", "Si", "No"},
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
		
		topPanel = new TopPanel(controller, false);
		
		gateSelectionPanel = new JPanel();
		gateSelectionPanel.setBackground(new Color(0, 204, 255));
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
				controller.loadImbarcoCenterPanel(gateComboBox.getSelectedItem().toString());
			}
		});
		gateSelectionPanel.add(okButton);
		topPanel.add(gateSelectionPanel, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new Color (0,0,153));
		centerPanel.setMinimumSize(new Dimension (500,100));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        topPanel.UpdateBackButton();
		    }
		});
	}
	
	public void showTrattaInfoView (Tratta tratta) { //(Tratta tratta)		
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
		
		JPanel mainSubPanel = new JPanel (new FlowLayout());
		mainSubPanel.setBackground(new Color(0, 153, 255));
		
		mainSubPanel.add(destinazioneLabel);
		mainSubPanel.add(Box.createHorizontalStrut(50));
		mainSubPanel.add(compagniaLabel);
		mainSubPanel.add(Box.createHorizontalStrut(50));
		mainSubPanel.add(dataLabel);
		mainSubPanel.add(Box.createHorizontalStrut(50));
		mainSubPanel.add(oraLabel);
		
		centerPanel.add(mainSubPanel, BorderLayout.NORTH);
		
		mainPanel.revalidate();
	}
	
	public void showListaPrenotati(LinkedList<Prenotazione> prenotati) { //(List prenotati) ?
//		data = new Object[100][6];
//		ListIterator<Prenotazione> cursor = prenotati.listIterator();
//		for (int i = 0; cursor.hasNext(); i ++) {
//				Prenotazione current = cursor.next();
//				data[i][0] = current.getCoda().getNomeCoda();
//				data[i][1] = current.getNomePasseggero();
//				data[i][2] = current.getCognomePasseggero();
//				data[i][3] = current.getCentoKilometri().getCodiceCompagnia();
//				data[i][4] = "no";
//				data[i][5] = new Boolean (false);
//		}
		tableModel = new TableModelPrenotazione();
		//table = new JTable(data, columnNames);
		table = new JTable(tableModel);
		setData(prenotati);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel chiudiImbarcoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chiudiImbarcoPanel.setBackground(new Color(0, 0, 153));
		chiudiImbarcoPanel.setBorder(new EmptyBorder(0,5,5,5));
		JButton chiudiImbarcoButton = new JButton("Chiudi Imbarco");
		chiudiImbarcoButton.setFocusPainted(false);
		chiudiImbarcoPanel.add(chiudiImbarcoButton);
		centerPanel.add(chiudiImbarcoPanel, BorderLayout.SOUTH);
		
		mainPanel.revalidate();
		
//		table.getModel().addTableModelListener(new TableModelListener() {
//
//	            @Override
//	            public void tableChanged(TableModelEvent e) {
//	                System.out.println(tableModel.getValueAt(e.getFirstRow(), 0)
//	                    + " " + tableModel.getValueAt(e.getFirstRow(), 1));
//	                tableModel.fireTableCellUpdated(e.getFirstRow(), e.getFirstRow());
//	                //tableRefresh();
//	            }
//	        });
	}
	
	public void setData (LinkedList<Prenotazione> prenotati) {
		tableModel.setData(prenotati);
	}
	
//	public void tableRefresh() {
//		tableModel.fireTableDataChanged();
//	}
	
	public void emptyCenterPanel () {
		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel.repaint();
		}
	}
}
