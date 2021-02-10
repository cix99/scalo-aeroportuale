package Views;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Models.Prenotazione;

public class TableModel extends AbstractTableModel {

	private LinkedList<Prenotazione> prenotati;
	private int numberOfColumns = 6;
	private String[] columnNames = {"Coda", "Nome", "Cognome", "Codice", "Cento Kilometri", "Imbarcato"};
	
	public TableModel () {
	}
	
	public void setData (LinkedList<Prenotazione> prenotati) {
		this.prenotati = prenotati;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return prenotati.size();
	}

	@Override
	public int getColumnCount() {
		return numberOfColumns;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
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
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return Integer.class;
		case 5:
			return Boolean.class;
		case 6:
			return JButton.class;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (prenotati == null) return;
		Prenotazione prenotazione = prenotati.get(rowIndex);
		switch (columnIndex) {
			case 1:
				break;
			case 5:
				//save the checkmark in Imbarcato
			default:
				return;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prenotazione prenotazione = prenotati.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return prenotazione.getCoda().getNomeCoda();
		case 1:
			return prenotazione.getNomePasseggero();
		case 2:
			return prenotazione.getCognomePasseggero();
		case 3:
			return prenotazione.getCodicePrenotazione();
		case 4:
			{
				if (prenotazione.getCentoKilometri().getCodiceCompagnia() != null)
					return prenotazione.getCentoKilometri().getCodiceCompagnia();
				else
					return "-";
			}
			
		case 5:
			return false;
		default:
			return null;
		}
	}

}
