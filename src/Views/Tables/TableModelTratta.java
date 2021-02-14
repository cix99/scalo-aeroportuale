package Views.Tables;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Stato;
import Models.Tratta;

@SuppressWarnings("serial")
public class TableModelTratta extends AbstractTableModel {

	private LinkedList<Tratta> tratte;
	private int numberOfColumns = 10;
	private String[] columnNames = {"ID", "Destinazione", "Compagnia Aerea", "Inizio Imbarco Stimato", "Inizio Imbarco Effettivo", "Fine Imbarco Stimato", "Fine Imbarco Effettivo", "Gate", "Stato Imbarco", "Ritardo"};
	
	private ViewsController controller;
	
	public TableModelTratta (ViewsController controller) {
		this.controller = controller;
	}
	
	public void setData (LinkedList<Tratta> tratte) {
		this.tratte = tratte;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return tratte.size();
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
			return LocalDateTime.class;
		case 4:
			return LocalDateTime.class;
		case 5:
			return LocalDateTime.class;
		case 6:
			return LocalDateTime.class;
		case 7:
			return String.class;
		case 8:
			return Stato.class;
		case 9:
			return Boolean.class;
		default:
			return null;
		}
	}

//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		if (tratte == null) return;
//		Prenotazione prenotazione = tratte.get(rowIndex);
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
		Tratta tratta = tratte.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tratta.getId();
		case 1:
			return tratta.getDestinazione();
		case 2:
			return tratta.getCompagniaAerea().getNomeCompagnia();
		case 3:
			return tratta.getOraInizioImbarcoStimato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		case 4:
			return tratta.getOraInizioImbarcoEffettivo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		case 5:
			return tratta.getOraFineImbarcoStimato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		case 6:
			{
				if (tratta.getOraFineImbarcoEffettivo() != null)
					return tratta.getOraFineImbarcoEffettivo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
				else
					return "-";
			}
		case 7:
			{
				if (tratta.getGate().getNomeGate() != null)
					return tratta.getGate().getNomeGate();
				else
					return "-";
			}
		case 8:
			return tratta.getStatoImbarco();
		case 9:
			return tratta.getRitardo();
		default:
			return null;
		}
	}

}
