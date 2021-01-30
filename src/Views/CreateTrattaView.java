package Views;

import javax.swing.JPanel;

import Controllers.HomeController;
import Controllers.TratteController;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateTrattaView extends JPanel {

	/**
	 * Create the panel.
	 */
	public CreateTrattaView() {
		setLayout(null);
		
		JButton goToIndex = new JButton("Vai a Index");
		goToIndex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel indexTratte = TratteController.viewIndex();
				indexTratte.setBounds(381, 65, 1045, 573);
				HomeController.getFrame().layeredPane.add(indexTratte);
			}
		});
		goToIndex.setBounds(45, 133, 77, 19);
		add(goToIndex);
		
	}
}
