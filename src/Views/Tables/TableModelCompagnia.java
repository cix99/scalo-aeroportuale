package Views.Tables;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.CompagniaAerea;

@SuppressWarnings("serial")
public class TableModelCompagnia extends AbstractTableModel {

	private LinkedList<CompagniaAerea> compagnie;
	private int numberOfColumns = 1;
	private String[] columnNames = {"Nome Compagnia"};
	
	private ViewsController controller;
	
	public TableModelCompagnia (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<CompagniaAerea> compagnie) {
		this.compagnie = compagnie;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return compagnie.size();
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

	public boolean updateRow(String value, int row, int col) {         
		if (controller.updateNomeCompagnia( value, compagnie.get(row).getNomeCompagnia())) {
			compagnie = controller.getCompagnieList();
			fireTableDataChanged();
			return true;
		}
		return false;
	}
	
	public void removeRow(int row) {       
		if (controller.deleteCompagniaAerea(compagnie.get(row).getNomeCompagnia())) {
			compagnie.remove(row);
			fireTableRowsDeleted(row, row);    
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CompagniaAerea compagnia = compagnie.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return compagnia.getNomeCompagnia();
		default:
			return null;
		}
	}

}
