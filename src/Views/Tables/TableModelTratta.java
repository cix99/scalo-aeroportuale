package Views.Tables;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

import Controllers.ViewsController;
import Models.Stato;
import Models.Tratta;

@SuppressWarnings("serial")
public class TableModelTratta extends AbstractTableModel {

	private LinkedList<Tratta> tratte;
	private int numberOfColumns = 11;
	private String[] columnNames = {"ID", "Destinazione", "Compagnia Aerea", "Inizio Imbarco Stimato", "Inizio Imbarco Effettivo", "Fine Imbarco Stimato", "Fine Imbarco Effettivo", "Gate", "Stato Imbarco", "Ritardo", "Max Prenotazioni"};
	
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
//		if (tratte.get(rowIndex).getStatoImbarco() == Stato.IN_ATTESA) {
//			switch (columnIndex) {
//			case 3:
//				return true;
//			case 5:
//				return true;
//			case 7:
//				return true;
//			case 10:
//				return true;
//			default:
//				return false;
//			}
//		}
//		else
//			return false;
//		
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
			return String.class;
		case 10:
			return Integer.class;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (tratte == null) return;
		Tratta tratta = tratte.get(rowIndex);
		switch (columnIndex) {
			case 5: {
				tratta.setOraInizioImbarcoStimato((LocalDateTime) aValue);
				//controller.updateInizioImbarcoStimato((LocalDateTime) aValue, tratta.getId());
				break;
			}
			default:
				return;
		}
	}
	
	public void removeRow(int row) {        
		if (controller.deleteTratta(tratte.get(row).getId())) {
			tratte.remove(row);
			fireTableRowsDeleted(row, row);    
		}
	}
	

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
		case 4: {
			if (tratta.getOraInizioImbarcoEffettivo() != null)
				return tratta.getOraInizioImbarcoEffettivo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			else
				return "-";
		}
		case 5: {
			if (tratta.getOraFineImbarcoStimato() != null)
				return tratta.getOraFineImbarcoStimato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			else
				return "-";
		}
		case 6: {
				if (tratta.getOraFineImbarcoEffettivo() != null)
					return tratta.getOraFineImbarcoEffettivo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
				else
					return "-";
			}
		case 7: {
				if (tratta.getGate().getNomeGate() != null)
					return tratta.getGate().getNomeGate();
				else
					return "-";
			}
		case 8:
			return tratta.getStatoImbarco();
		case 9:
			return tratta.getRitardo();
		case 10:
			return tratta.getMaxPrenotazioni();
		default:
			return null;
		}
	}

}