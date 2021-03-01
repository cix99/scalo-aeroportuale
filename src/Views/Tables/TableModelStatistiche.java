package Views.Tables;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Gate;

@SuppressWarnings("serial")
public class TableModelStatistiche extends AbstractTableModel {

	private LinkedList<Gate> gates;
	private int numberOfColumns = 7;
	private String[] columnNames = {"Nome Gate", "Stimato giornaliero", "Effettivo giornaliero", "Stimato settimanale", "Effettivo settimanale", "Stimato mensile", "Effettivo mensile"};
	
	private ViewsController controller;
	
	public TableModelStatistiche (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<Gate> gates) {
		this.gates = gates;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return gates.size();
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
			return Float.class;
		case 2:
			return Float.class;
		case 3:
			return Float.class;
		case 4:
			return Float.class;
		case 5:
			return Float.class;
		case 6:
			return Float.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Gate gate = gates.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return gate.getNomeGate();
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
