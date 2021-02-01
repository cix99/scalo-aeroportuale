package Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ImbarcoView extends JPanel{

	private JLabel label;
	
	public ImbarcoView () {	
		setPreferredSize (new Dimension(800,650));
		setBackground(new Color(0, 0, 0));
		setLayout(new CardLayout());
		
		label = new JLabel("Hai scelto Imbarco menu");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		
		add(label);
	}
}
