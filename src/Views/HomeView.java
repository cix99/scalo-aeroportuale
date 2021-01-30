package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.HomeController;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeView extends JFrame {

	private JPanel contentPanel;
	private JPanel MainPanel;
	public JLayeredPane layeredPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Home frame = new Home();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\decar\\Desktop\\aereo-150x150.png"));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1450, 750);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 11, 1426, 750);
		contentPanel.add(layeredPane);
		
		JButton tratteButton = new JButton("Crea Tratta");
		tratteButton.setFont(new Font("Cambria", Font.PLAIN, 20));
		tratteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tratteButton.setBounds(70, 65, 270, 151);
		layeredPane.add(tratteButton);
		
		JButton btnNewButton_1 = new JButton("Cerca...");
		btnNewButton_1.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnNewButton_1.setBounds(70, 281, 263, 151);
		layeredPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Imbarco");
		btnNewButton_2.setFont(new Font("Cambria", Font.PLAIN, 20));
		btnNewButton_2.setBounds(70, 482, 270, 151);
		layeredPane.add(btnNewButton_2);
		
		JButton LogoutBotton = new JButton("Logout ");
		LogoutBotton.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		LogoutBotton.setBounds(1346, 0, 80, 18);
		layeredPane.add(LogoutBotton);
		
		MainPanel = new JPanel();
		MainPanel.setBounds(381, 65, 1045, 573);
		layeredPane.add(MainPanel);
		
		tratteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//MainPanel = HomeController.openCreaTratta();
				//MainPanel.setBounds(381, 65, 1045, 573);
				//layeredPane.add(MainPanel);
			}
		});
	}
}
