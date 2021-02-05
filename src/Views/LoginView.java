package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Controllers.ViewsController;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton accedi;
	private JLabel username;
	private JLabel password;
	private JLabel loginLabel;
	private JLabel finestraLog;
	private JLabel sfondoLabel;
	
	/* Create the frame. */
	public LoginView() {

		ViewsController homeController = new ViewsController();
		setResizable(false);
		setTitle("Login-ScaloAeroportiale.java");
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 547);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		accedi = new JButton("Accedi");
		accedi.setForeground(new Color(51, 255, 0));
		accedi.setContentAreaFilled(false);
		accedi.setBorder(null);
		accedi.setIcon(null);
		
		accedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				homeController.login(usernameField.getText(), passwordField.getPassword());
				
			}
		});
		accedi.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		accedi.setBounds(607, 302, 80, 28);
		contentPane.add(accedi);
		
		username = new JLabel("Username");
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		username.setHorizontalAlignment(SwingConstants.LEFT);
		username.setIcon(null);
		username.setBounds(561, 174, 74, 14);
		contentPane.add(username);
		
		password = new JLabel("Password");
		password.setForeground(new Color(255, 255, 255));
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		password.setBounds(561, 238, 74, 14);
		contentPane.add(password);
		
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					homeController.login(usernameField.getText(), passwordField.getPassword());
				   }
				
			}
		});
		usernameField.setToolTipText("Inserisci e_mail");
		usernameField.setBounds(561, 199, 186, 28);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.setBorder(null);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					homeController.login(usernameField.getText(), passwordField.getPassword());
				   }
			}
		});
		passwordField.setBounds(560, 263, 187, 28);
		contentPane.add(passwordField);
		passwordField.setBorder(null);
		
		loginLabel = new JLabel("LOGIN");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Maiandra GD", Font.BOLD, 26));
		loginLabel.setForeground(new Color(51, 255, 0));
		loginLabel.setBounds(588, 100, 118, 28);
		contentPane.add(loginLabel);
		
		finestraLog = new JLabel("");
		Image logingImage = new ImageIcon (this.getClass().getResource("/login_background.jpg")).getImage();
		finestraLog.setIcon(new ImageIcon(logingImage));
		finestraLog.setBounds(530, 61, 240, 311);
		contentPane.add(finestraLog);
		
		sfondoLabel = new JLabel("");
		Image sfondoImage = new ImageIcon (this.getClass().getResource("/sfondo_login.png")).getImage();
		sfondoLabel.setIcon(new ImageIcon(sfondoImage));
		sfondoLabel.setBounds(0, 0, 804, 519);
		contentPane.add(sfondoLabel);
		
	}
	
	public JTextField getUsername () {
		return usernameField;
	}
	
	public JPasswordField getPassword () {
		return passwordField;
	}
	

}
