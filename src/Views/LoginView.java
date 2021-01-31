package Views;

import Controllers.LoginController;

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

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField passwordField;

	/* Create the frame. */
	public LoginView() {
		setTitle("Login-ScaloAeroportiale.java");
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 808, 547);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Accedi = new JButton("Accedi");
		Accedi.setForeground(new Color(51, 255, 0));
		Accedi.setContentAreaFilled(false);
		Accedi.setBorder(null);
		Accedi.setIcon(null);
		Accedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginController.login(txtEmail.getText(), passwordField.getPassword());
			}
		});
		Accedi.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		Accedi.setBounds(607, 302, 80, 28);
		contentPane.add(Accedi);
		
		JLabel username = new JLabel("Username");
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		username.setHorizontalAlignment(SwingConstants.LEFT);
		username.setIcon(null);
		username.setBounds(561, 174, 74, 14);
		contentPane.add(username);
		
		JLabel password = new JLabel("Password");
		password.setForeground(new Color(255, 255, 255));
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		password.setBounds(561, 238, 74, 14);
		contentPane.add(password);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					LoginController.login(txtEmail.getText(), passwordField.getPassword());
				   }
				
			}
		});
		txtEmail.setToolTipText("Inserisci e_mail");
		txtEmail.setBounds(561, 199, 186, 28);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					LoginController.login(txtEmail.getText(), passwordField.getPassword());
				   }
			}
		});
		passwordField.setBounds(560, 263, 187, 28);
		contentPane.add(passwordField);
		passwordField.setBorder(null);
		
		JLabel LoginLabel = new JLabel("LOGIN");
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLabel.setFont(new Font("Maiandra GD", Font.BOLD, 26));
		LoginLabel.setForeground(new Color(51, 255, 0));
		LoginLabel.setBounds(588, 100, 118, 28);
		contentPane.add(LoginLabel);
		
		JLabel FinestraLog = new JLabel("");
		Image logingImage = new ImageIcon (this.getClass().getResource("/login_background.jpg")).getImage();
		FinestraLog.setIcon(new ImageIcon(logingImage));
		FinestraLog.setBounds(530, 61, 240, 311);
		contentPane.add(FinestraLog);
		
		JLabel SfondoLabel = new JLabel("");
		Image sfondoImage = new ImageIcon (this.getClass().getResource("/sfondo_login.png")).getImage();
		SfondoLabel.setIcon(new ImageIcon(sfondoImage));
		SfondoLabel.setBounds(0, 0, 804, 519);
		contentPane.add(SfondoLabel);
	}
}
