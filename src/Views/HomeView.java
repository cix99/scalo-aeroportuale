package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel MainPanel;
	
	public HomeView() {
		/* Frame settings */
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/aereo_logo.png"));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		/* Main Panel settings */
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		MainPanel.add(new HomeCenterPanel(), BorderLayout.CENTER);
		MainPanel.add(new HomeSidePanel(), BorderLayout.WEST);
		MainPanel.add(new HomeTopPanel(), BorderLayout.NORTH);
		
		
	}
}
