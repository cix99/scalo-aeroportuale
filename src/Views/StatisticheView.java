package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StatisticheView extends JFrame{

private JLabel label;
	
	public StatisticheView () {	
		setTitle("Statistiche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));		
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("Hai scelto Statistiche menu");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		
		add(label);
	}
}
