package Views.Tables;

import java.util.LinkedList;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Gate;

@SuppressWarnings("serial")
public class TableModelStatistiche extends AbstractTableModel {

	private Map<String, Map> statistiche;
	private int numberOfColumns = 10;
	private String[] columnNames = {"Nome Gate", "Voli Ultime 24 ore", "Stimato Ultime 24 ore", "Effettivo Ultime 24 ore", "Voli ultima settimana", "Stimato settimanale", "Effettivo settimanale", "Voli ultimo mese", "Stimato mensile", "Effettivo mensile"};
	
	private ViewsController controller;
	
	public TableModelStatistiche (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (Map<String, Map> statistiche) {
		this.statistiche = statistiche;
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
		Map<String, Map> gate = statistiche.get(rowIndex);
		switch (columnIndex) {
		case 0:
			//return gate.getNomeGate();
			return 0.0f
		case 1:
			return 0.0f; //utilizzo stimato giornaliero
		case 2:
			return 0.0f; //utilizzo effettivo giornaliero
		case 3:
			return 0.0f; //utilizzo stimato settimanale
		case 4:
			return 0.0f; //utilizzo effettivo settimanale
		case 5:
			return 0.0f; //utilizzo stimato mensile
		case 6:
			return 0.0f; //utilizzo effettivo mensile
		default:
			return null;
		}
	}

}
