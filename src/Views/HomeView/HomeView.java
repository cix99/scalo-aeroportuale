package Views.HomeView;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import Controllers.ViewsController;
import Views.TopPanel;

@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel mainPanel;
	private TopPanel topPanel;
	private JPanel sidePanel;
	public JPanel centerPanel;
	public JFrame newCenterPanel;
	private JLabel benvenutoLabel;
	private JLabel menuLabel;
	
	private ViewsController controller;
	
	public HomeView(ViewsController viewsController, String username) {
		controller = viewsController;
		
		setTitle("Scalo Aeroportuale - Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		topPanel = new TopPanel(controller, true);

		sidePanel = new JPanel(new BorderLayout());
		sidePanel.setBackground(new Color(0, 153, 255));
		
		menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuLabel.setForeground(Color.WHITE);
		menuLabel.setBorder(new EmptyBorder(5,0,0,0));
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		sidePanel.add(menuLabel, BorderLayout.NORTH);
		sidePanel.add(new HomeSidePanel(controller, username), BorderLayout.CENTER);
		
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
		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        topPanel.UpdateLogoutButton();
		    }
		});
	}

}
