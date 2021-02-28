package Views.Tables;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.CentoKilometri;

@SuppressWarnings("serial")
public class TableModelCentoKilometri extends AbstractTableModel {

	private LinkedList<CentoKilometri> centoKilometri;
	private ArrayList<String> clientiCKList;
	private int numberOfColumns = 6;
	private String[] columnNames = {"ID", "Compagnia", "Codice", "Nome", "Cognome", "Punti"};
	
	private ViewsController controller;
	
	public TableModelCentoKilometri (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<CentoKilometri> centoKilometri, ArrayList<String> clientiCKList) {
		this.centoKilometri = centoKilometri;
		this.clientiCKList = clientiCKList;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return centoKilometri.size();
	}

	@Override
	public int getColumnCount() {
		return numberOfColumns;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			return true;
		case 2:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}

	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return Integer.class;
		default:
			return null;
		}
	}

	public boolean updateRow(String codice, String nomeCompagnia, String punti, int row) {    
		int puntiValore;
    	try {
    	   puntiValore = Integer.parseInt(punti);
    	} catch (NumberFormatException e) {
    	   puntiValore = 0;
    	}
		if (controller.updateCentoKilometri(codice, nomeCompagnia, puntiValore, centoKilometri.get(row).getId())) {
			controller.loadCercaCenterPanel("Cento Kilometri");
			return true;
		}
		return false;
	}
	
	public void removeRow(int row) {          //removes a row based on number from the data
		if (controller.deleteCentoKilometri(centoKilometri.get(row).getId())) {
			centoKilometri.remove(row);
			fireTableRowsDeleted(row, row);    //updates the table
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CentoKilometri centoKilometriCell = centoKilometri.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return centoKilometriCell.getId();
		case 1:
			return centoKilometriCell.getCompagniaAerea().getNomeCompagnia();
		case 2:
			return centoKilometriCell.getCodiceCompagnia();
		case 3:
			return clientiCKList.get(2*rowIndex);
		case 4:
			return clientiCKList.get(2*rowIndex+1);
		case 5:	
			return centoKilometriCell.getPunti();
		default:
			return null;
		}
	}

}
