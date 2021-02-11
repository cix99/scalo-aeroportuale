package Views.HomePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class HomeSidePanel extends JPanel {

	private JPanel imbarcoPanel;
	private JLabel imbarcoLabel;
	private JPanel aggiungiPanel;
	private JLabel aggiungiLabel;
	private JPanel cercaPanel;
	private JLabel cercaLabel;
	private JPanel statistichePanel;
	private JLabel statisticheLabel;
	private JPanel userPanel;
	
	public HomeSidePanel (ViewsController controller, String username) {
		
		setPreferredSize(new Dimension (200,650));
		setBackground(new Color(0, 153, 255));
		setBorder(new EmptyBorder(10, 0, 0, 0));
		setLayout(new GridLayout(5, 1, 0, 10));
		
		/* Imbarco option */
		
		imbarcoPanel = new JPanel();
		imbarcoPanel.setBackground(new Color(0, 204, 255));
		imbarcoPanel.setBounds(0, 10, 200, 85);
		imbarcoPanel.setLayout(new GridBagLayout());
		add(imbarcoPanel);
		imbarcoLabel = new JLabel("Imbarco");
		imbarcoLabel.setForeground(Color.WHITE);
		imbarcoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		imbarcoPanel.add(imbarcoLabel, gc);
		
		imbarcoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama imbarco view
				controller.imbarcoView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				imbarcoLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imbarcoLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				imbarcoLabel.setForeground(new Color (150, 150, 150));
				imbarcoPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				imbarcoLabel.setForeground(Color.WHITE);
				imbarcoPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Aggiungi option */
		aggiungiPanel = new JPanel();
		aggiungiPanel.setBackground(new Color(0, 204, 255));
		aggiungiPanel.setBounds(0, 105, 200, 85);
		aggiungiPanel.setLayout(new GridBagLayout());
		add(aggiungiPanel);
		
		aggiungiLabel = new JLabel("Aggiungi");
		aggiungiLabel.setForeground(Color.WHITE);
		aggiungiLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		aggiungiPanel.add(aggiungiLabel, gc);
		
		aggiungiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama aggiungi view
				controller.aggiungiView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				aggiungiLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				aggiungiLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				aggiungiLabel.setForeground(new Color (150, 150, 150));
				aggiungiPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				aggiungiLabel.setForeground(Color.WHITE);
				aggiungiPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Cerca option */
		cercaPanel = new JPanel();
		cercaPanel.setBackground(new Color(0, 204, 255));
		cercaPanel.setBounds(0, 200, 200, 85);
		cercaPanel.setLayout(new GridBagLayout());
		add(cercaPanel);
		
		cercaLabel = new JLabel("Cerca");
		cercaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cercaLabel.setForeground(Color.WHITE);
		cercaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		cercaPanel.add(cercaLabel, gc);
		
		cercaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama cerca view
				controller.cercaView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cercaLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cercaLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				cercaLabel.setForeground(new Color (150, 150, 150));
				cercaPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				cercaLabel.setForeground(Color.WHITE);
				cercaPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Elimina option */
		statistichePanel = new JPanel();
		
		statistichePanel.setBackground(new Color(0, 204, 255));
		statistichePanel.setBounds(0, 295, 200, 85);
		statistichePanel.setLayout(new GridBagLayout());
		add(statistichePanel);
		
		statisticheLabel = new JLabel("Statistiche");
		statisticheLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statisticheLabel.setForeground(Color.WHITE);
		statisticheLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		statistichePanel.add(statisticheLabel, gc);
		
		statistichePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//Chiama elimina view
				controller.statisticheView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				statisticheLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				statisticheLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				statisticheLabel.setForeground(new Color (150, 150, 150));
				statistichePanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				statisticheLabel.setForeground(Color.WHITE);
				statistichePanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		userPanel = new JPanel();
		userPanel.setBackground(new Color(0, 153, 255));
		userPanel.setBounds(0, 105, 200, 85);
		userPanel.setLayout(new BorderLayout(0,0));
		userPanel.setBorder(new EmptyBorder(0,5,5,0));
		
		ImageIcon userImage = new ImageIcon(new ImageIcon("img/user.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel userLabel = new JLabel();
		userLabel.setIcon(userImage);
		userLabel.setText(username);
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		userPanel.add(userLabel, BorderLayout.SOUTH);
		add(userPanel);
	}
}
