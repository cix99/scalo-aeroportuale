package Views.Tables;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Prenotazione;

@SuppressWarnings("serial")
public class TableModelPrenotazione extends AbstractTableModel {

	private LinkedList<Prenotazione> prenotazioni;
	private int numberOfColumns = 9;
	private String[] columnNames = {"ID", "Codice", "Compagnia Aerea", "ID Tratta", "Nome", "Cognome", "Cento Kilometri", "Coda", "Imbarcato"};
	
	private ViewsController controller;
	
	public TableModelPrenotazione (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<Prenotazione> prenotati) {
		this.prenotazioni = prenotati;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return prenotazioni.size();
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
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return Boolean.class;
		default:
			return null;
		}
	}

	public boolean updateRow(String coda, int row) {         
		if (controller.updatePrenotazione( coda, prenotazioni.get(row).getIdTratta(), prenotazioni.get(row).getId())) {
			prenotazioni = controller.getPrenotazioni();
			fireTableDataChanged();
			return true;
		}
		return false;
	}
	
	public void removeRow(int row) {         
		if (controller.deletePrenotazione(prenotazioni.get(row).getId())) {
			prenotazioni.remove(row);
			fireTableRowsDeleted(row, row);   
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prenotazione prenotazione = prenotazioni.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return prenotazione.getId();
		case 1:
			return prenotazione.getCodicePrenotazione();
		case 2:
			return prenotazione.getCompagniaAerea().getNomeCompagnia();
		case 3:
			return prenotazione.getIdTratta();
		case 4:
			return prenotazione.getNomePasseggero();
		case 5:
			return prenotazione.getCognomePasseggero();
		case 6:
		{
			if (prenotazione.getCentoKilometri().getCodiceCompagnia() != null)
				return prenotazione.getCentoKilometri().getCodiceCompagnia() + " (" + prenotazione.getCentoKilometri().getCompagniaAerea().getNomeCompagnia() + ")";
			else
				return "-";
		}
		case 7:
			return prenotazione.getCoda().getNomeCoda();
		case 8:
			return prenotazione.getImbarcato();
		default:
			return null;
		}
	}

}
