package Views.Tables;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.CentoKilometri;

@SuppressWarnings("serial")
public class TableModelCentoKilometri extends AbstractTableModel {

	private LinkedList<CentoKilometri> centoKilometri;
	private int numberOfColumns = 4;
	private String[] columnNames = {"ID", "Codice", "Compagnia", "Punti"};
	
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

//	@Override
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		switch (columnIndex) {
//		case 5:
//			return true;
//		default:
//			return false;
//		}
//	}

	
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
			return Integer.class;
		default:
			return null;
		}
	}

//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		if (prenotazioni == null) return;
//		Prenotazione prenotazione = prenotazioni.get(rowIndex);
//		switch (columnIndex) {
//			case 5:
//				prenotazione.setImbarcato((boolean) aValue);
//				controller.updateImbarcatoInDatabase((boolean) aValue, prenotazione.getId());
//				break;
//			default:
//				return;
//		}
//	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CentoKilometri centoKilometriCell = centoKilometri.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return centoKilometriCell.getId();
		case 1:
			return centoKilometriCell.getCodiceCompagnia();
		case 2:
			return centoKilometriCell.getCompagniaAerea().getNomeCompagnia();
		case 3:	
			return centoKilometriCell.getPunti();
		default:
			return null;
		}
	}

}
