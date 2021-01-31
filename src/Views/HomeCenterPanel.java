package Views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomeCenterPanel extends JPanel {

	private JButton btnNewButton_1;
	
	public HomeCenterPanel() {
		
		setPreferredSize (new Dimension(800,650));
		setBackground(new Color(0, 0, 153));
		btnNewButton_1 = new JButton("New Button");
		add(btnNewButton_1);
	}
}
