package Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HomeCenterPanel extends JPanel {

	private JLabel benvenutoLabel;
	
	public HomeCenterPanel(String username) {
		
		setPreferredSize (new Dimension(800,650));
		setBackground(new Color(0, 0, 153));
		setLayout(new CardLayout());
		
		benvenutoLabel = new JLabel("Benvenuto " + username);
		benvenutoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		benvenutoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		benvenutoLabel.setForeground(Color.WHITE);
		
		add(benvenutoLabel);
		
	}
	
}
