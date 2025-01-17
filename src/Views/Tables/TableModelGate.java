package Views.Tables;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Gate;

@SuppressWarnings("serial")
public class TableModelGate extends AbstractTableModel {

	private LinkedList<Gate> gates;
	private int numberOfColumns = 1;
	private String[] columnNames = {"Nome Gate"};
	
	private ViewsController controller;
	
	public TableModelGate (ViewsController controller) {
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
		default:
			return null;
		}
	}
	
	public boolean updateRow(String value, int row) {         
		if (controller.updateNomeGate(value, gates.get(row).getNomeGate())) {
			gates = controller.getGatesList();
			fireTableDataChanged();
			return true;
		}
		return false;
	}
	
	public void removeRow(int row) {         
		if (controller.deleteGate(gates.get(row).getNomeGate())) {
			gates.remove(row);
			fireTableRowsDeleted(row, row);    
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Gate gate = gates.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return gate.getNomeGate();
		default:
			return null;
		}
	}

}
