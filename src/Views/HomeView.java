package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel sidePanel;
	private JPanel centerPanel;
	
	public HomeView() {
		/* Frame settings */
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/aereo_logo.png"));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		/* Main Panel settings */
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		topPanel = new HomeTopPanel();
		sidePanel = new HomeSidePanel();
		centerPanel = new HomeCenterPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	}
	
	public void ImbarcoView () {
		JPanel panel = new ImbarcoView();
		mainPanel.add(panel);
	}
	
	public void AggiungiView () {
		JPanel panel = new AggiungiView();
		mainPanel.add(panel);
	}
	
	public void CercaView () {
		JPanel panel = new CercaView();
		mainPanel.add(panel);
	}
	
	public void EliminaView () {
		JPanel panel = new EliminaView();
		mainPanel.add(panel);
	}

}
