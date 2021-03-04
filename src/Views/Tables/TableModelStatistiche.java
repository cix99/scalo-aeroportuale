package Views.Tables;

import java.util.LinkedList;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Gate;

@SuppressWarnings("serial")
public class TableModelStatistiche extends AbstractTableModel {

	private LinkedList<Gate> gates;
	private Map<String, Map<String, Integer>> statistiche;
	private int numberOfColumns = 10;
	private String[] columnNames = {"Nome Gate", "Voli Ultime 24 ore", "Stimato Ultime 24 ore", "Effettivo Ultime 24 ore", 
									"Voli ultima settimana", "Stimato settimanale", "Effettivo settimanale", "Voli ultimo mese", "Stimato mensile", "Effettivo mensile"};
	
	private ViewsController controller;
	
	public TableModelStatistiche (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (Map<String, Map<String, Integer>> statistiche) {
		this.statistiche = statistiche;
		gates = controller.getGatesList();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return statistiche.size();
	}

	@Override
	public int getColumnCount() {
		return numberOfColumns;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		case 4:
			return Integer.class;
		case 5:
			return Integer.class;
		case 6:
			return Integer.class;
		case 7:
			return Integer.class;
		case 8:
			return Integer.class;
		case 9:
			return Integer.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String key = gates.get(rowIndex).getNomeGate();
		Map<String, Integer> gate = statistiche.get(key);
		switch (columnIndex) {
		case 0:
			return key;
		case 1:
			return gate.get("voli_giornalieri"); 
		case 2:
			return gate.get("s_giornaliero"); 
		case 3:
			return gate.get("e_giornaliero"); 
		case 4:
			return gate.get("voli_settimanali"); 
		case 5:
			return gate.get("s_settimanale"); 
		case 6:
			return gate.get("e_settiamanale"); 
		case 7:
			return gate.get("voli_mensili"); 
		case 8:
			return gate.get("s_mensile"); 
		case 9:
			return gate.get("e_mensile"); 
		default:
			return null;
		}
	}

}
