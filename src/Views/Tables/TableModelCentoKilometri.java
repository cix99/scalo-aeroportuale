package Views.Tables;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.CentoKilometri;

@SuppressWarnings("serial")
public class TableModelCentoKilometri extends AbstractTableModel {

	private LinkedList<CentoKilometri> centoKilometri;
	private int numberOfColumns = 6;
	private String[] columnNames = {"ID", "Compagnia", "Codice", "Nome", "Cognome", "Punti"};
	
	private ViewsController controller;
	
	public TableModelCentoKilometri (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<CentoKilometri> centoKilometri) {
		this.centoKilometri = centoKilometri;
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

	public boolean updateRow(String codice, String nomeCompagnia, String nome, String cognome, String punti, int row) {    
		int puntiValore;
    	try {
    	   puntiValore = Integer.parseInt(punti);
    	} catch (NumberFormatException e) {
    	   puntiValore = 0;
    	}
		if (controller.updateCentoKilometri(codice, nomeCompagnia, nome, cognome, puntiValore, centoKilometri.get(row).getId())) {
			centoKilometri = controller.getCentoKilometri();
			fireTableDataChanged();
			return true;
		}
		return false;
	}
	
	public void removeRow(int row) {        
		if (controller.deleteCentoKilometri(centoKilometri.get(row).getId())) {
			centoKilometri.remove(row);
			fireTableRowsDeleted(row, row);    
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
			return centoKilometriCell.getNome();
		case 4:
			return centoKilometriCell.getCognome();
		case 5:	
			return centoKilometriCell.getPunti();
		default:
			return null;
		}
	}

}
