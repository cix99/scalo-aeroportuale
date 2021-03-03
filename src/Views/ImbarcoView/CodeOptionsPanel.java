package Views.ImbarcoView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;
import Models.Coda;
import Views.TopPanel;

@SuppressWarnings("serial")
public class CodeOptionsPanel extends JPanel{
	
	private JComboBox<String> codaComboBox;

	public CodeOptionsPanel (LinkedList<Coda> codeList, ViewsController viewsController, TopPanel topPanel) {

		setBackground(new Color(0,0,153));
		setBorder(new EmptyBorder(5,5,0,0));
		
		codaComboBox = new JComboBox<String>();
		codaComboBox.addItem("Tutte");
		ListIterator<Coda> cursor = codeList.listIterator();
		while (cursor.hasNext()) {
			Coda current = cursor.next();
			codaComboBox.addItem(current.getNomeCoda());
		}
		codaComboBox.setEditable(false);
		codaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		codaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewsController.loadPrenotatiImbarcoPerCoda(codaComboBox.getSelectedItem().toString());
				
			}
		});
		add(codaComboBox);
	}
	
	public String getSelectedCoda () {
		return codaComboBox.getSelectedItem().toString();
	}
}
