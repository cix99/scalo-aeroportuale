package Views.ImbarcoView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.Prenotazione;
import Views.Tables.TableModelImbarco;

@SuppressWarnings("serial")
public class PrenotatiPanel extends JPanel{

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelImbarco tableModel;
	
	private ViewsController controller;
	
	public PrenotatiPanel(LinkedList<Prenotazione> prenotati, ViewsController viewsController) { 
		controller = viewsController;
		setLayout(new BorderLayout());
		
		tableModel = new TableModelImbarco(controller);
		table = new JTable(tableModel);
		setData(prenotati);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));			
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(2).setMinWidth(200);
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void setData (LinkedList<Prenotazione> prenotati) {
		tableModel.setData(prenotati);
	}
	
	public void emptyTable () {
		if (scrollPane != null) {
			scrollPane.removeAll();
		}
	}
}
