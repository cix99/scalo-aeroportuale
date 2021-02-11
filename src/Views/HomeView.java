package Views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import Controllers.ViewsController;
import Views.HomePanels.HomeSidePanel;


@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel sidePanel;
	public JPanel centerPanel;
	public JFrame newCenterPanel;
	private JLabel benvenutoLabel;
	
	public HomeView(ViewsController controller, String username) {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		
//		Border border = new LineBorder(Color.WHITE, 5, true);
//		mainPanel.setBorder(border);
		
		topPanel = new TopPanel(controller, true);
		sidePanel = new HomeSidePanel(controller);
		centerPanel = new JPanel();
		
		centerPanel.setPreferredSize (new Dimension(800,650));
		centerPanel.setBackground(new Color(0, 0, 153));
		centerPanel.setLayout(new CardLayout());
		
		benvenutoLabel = new JLabel("Benvenuto " + username);
		benvenutoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		benvenutoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		benvenutoLabel.setForeground(Color.WHITE);
		
		centerPanel.add(benvenutoLabel);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
	}

}
