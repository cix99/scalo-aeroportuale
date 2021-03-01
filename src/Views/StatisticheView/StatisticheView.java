package Views.StatisticheView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Views.TopPanel;
import Views.Tables.TableModelStatistiche;

@SuppressWarnings("serial")
public class StatisticheView extends JFrame{

	private TopPanel topPanel;
	private JPanel menuPanel;
	private JLabel menuLabel;
	private TableModelStatistiche tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	
	public StatisticheView (ViewsController controller) {	
		setTitle("Scalo Aeroportuale - Statistiche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));		
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(0, 0, 153));		
		
		topPanel = new TopPanel(controller, false);
		menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color (0, 204, 255));
		menuLabel = new JLabel(" Utilizzo Gates");
		menuLabel.setForeground(Color.WHITE);
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		menuPanel.add(menuLabel);
		topPanel.add(menuPanel, BorderLayout.SOUTH);
    	
    	tableModel = new TableModelStatistiche(controller);
		table = new JTable(tableModel);
		tableModel.setData(controller.getGatesList());
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer); //Nome Gate
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);	//Utilizzo stimato giornaliero
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer); //Utilizzo effettivo giornaliero
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer); //Utilizzo stimato settimanale
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);	//Utilizzo effettivo settimanale
		table.getColumnModel().getColumn(5).setCellRenderer(tableRenderer); //Utilizzo stimato mensile
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer);	//Utilizzo effettivo mensile
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(false);
			
		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        topPanel.UpdateBackButton();
		    }
		});
	}
}
