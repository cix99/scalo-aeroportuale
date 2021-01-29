package Views;

import Controllers.LoginController;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	/**
	 * Create the frame.
	 */
	
	public LoginView() {
		setTitle("Login-ScaloAeroportiale.java");
		setIconImage(Toolkit.getDefaultToolkit().getImage(""));
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
				String username = txtEmail.getText();
				String password = passwordField.getText();
				LoginController.login(username, password);
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
		txtEmail.setToolTipText("Inserisci e_mail");
		txtEmail.setBounds(561, 199, 186, 28);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		
		passwordField = new JPasswordField();
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
		FinestraLog.setIcon(new ImageIcon("C:\\Users\\decar\\Desktop\\sfondo login.jpg!d"));
		FinestraLog.setBounds(530, 61, 240, 311);
		contentPane.add(FinestraLog);
		
		JLabel SfondoLabel = new JLabel("");
		SfondoLabel.setIcon(new ImageIcon("C:\\Users\\decar\\Desktop\\aereo.jpeg"));
		SfondoLabel.setBounds(0, 0, 792, 508);
		contentPane.add(SfondoLabel);
	}
}
