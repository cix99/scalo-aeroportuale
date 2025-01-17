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
	private JLabel sfondoLabel;

	private ViewsController controller;
	
	public LoginView(ViewsController viewsController) {
		controller = viewsController;
		
		setResizable(false);
		setTitle("Scalo Aeroportuale - Login");
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 547);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		accedi = new JButton("Accedi");
		accedi.setHorizontalAlignment(SwingConstants.LEFT);
		accedi.setForeground(new Color(255, 255, 255));
		accedi.setContentAreaFilled(false);
		accedi.setBorder(null);
		accedi.setIcon(null);
		accedi.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		accedi.setBounds(600, 351, 105, 28);
		accedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			controller.login(usernameField.getText(), passwordField.getPassword());
			}
		});
		contentPane.add(accedi);
		
		username = new JLabel("Username");
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		username.setHorizontalAlignment(SwingConstants.LEFT);
		username.setIcon(null);
		username.setBounds(540, 199, 100, 14);
		contentPane.add(username);
		
		password = new JLabel("Password");
		password.setForeground(new Color(255, 255, 255));
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		password.setBounds(540, 265, 100, 14);
		contentPane.add(password);
		
		usernameField = new JTextField();
		usernameField.setBounds(540, 226, 207, 26);
		usernameField.setColumns(10);
		usernameField.setBorder(null);
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.login(usernameField.getText(), passwordField.getPassword());
				}
			}
		});
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(540, 290, 207, 26);
		passwordField.setBorder(null);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				controller.login(usernameField.getText(), passwordField.getPassword());
			}
			}
		});
		contentPane.add(passwordField);
		
		loginLabel = new JLabel("LOGIN");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 26));
		loginLabel.setForeground(new Color(255, 255, 255));
		loginLabel.setBounds(575, 135, 118, 28);
		contentPane.add(loginLabel);

		sfondoLabel = new JLabel("");
		Image sfondoImage = new ImageIcon (this.getClass().getResource("/background.png")).getImage();
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
