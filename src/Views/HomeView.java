package Views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import Controllers.ViewsController;


@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel sidePanel;
	public JPanel centerPanel;
	public JFrame newCenterPanel;
	
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
		
		topPanel = new HomeTopPanel();
		sidePanel = new HomeSidePanel(controller);
		centerPanel = new HomeCenterPanel(username);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
	}

}
