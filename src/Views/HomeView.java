package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controllers.HomeController;

@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JPanel MainPanel;

	/* Create the frame. */
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
		
		/* Center Panel settings */
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBackground(new Color(0, 0, 153));
		MainPanel.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setPreferredSize (new Dimension(800,650));
		
		JButton btnNewButton_1 = new JButton("New button");
		CenterPanel.add(btnNewButton_1);
		
		/* Side Panel settings (Main options) */
		JPanel SidePanel = new JPanel();
		SidePanel.setBackground(new Color(0, 153, 255));
		MainPanel.add(SidePanel, BorderLayout.WEST);
		SidePanel.setPreferredSize(new Dimension (200,650));
		SidePanel.setLayout(null);
		
		/* Imbarco option */
		JPanel ImbarcoPanel = new JPanel();
		ImbarcoPanel.setBackground(new Color(0, 204, 255));
		ImbarcoPanel.setBounds(0, 10, 200, 85);
		SidePanel.add(ImbarcoPanel);
		JLabel ImbarcoLabel = new JLabel("Imbarco");
		ImbarcoLabel.setForeground(Color.WHITE);
		ImbarcoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ImbarcoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_ImbarcoPanel = new GroupLayout(ImbarcoPanel);
		gl_ImbarcoPanel.setHorizontalGroup(
			gl_ImbarcoPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(ImbarcoLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		gl_ImbarcoPanel.setVerticalGroup(
			gl_ImbarcoPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(ImbarcoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		ImbarcoPanel.setLayout(gl_ImbarcoPanel);
		
		ImbarcoLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama imbarco view
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ImbarcoLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImbarcoLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ImbarcoLabel.setForeground(new Color (150, 150, 150));
				ImbarcoPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				ImbarcoLabel.setForeground(Color.WHITE);
				ImbarcoPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Aggiungi option */
		JPanel AggiungiPanel = new JPanel();
		AggiungiPanel.setBackground(new Color(0, 204, 255));
		AggiungiPanel.setBounds(0, 105, 200, 85);
		SidePanel.add(AggiungiPanel);
		
		JLabel AggiungiLabel = new JLabel("Aggiungi");
		AggiungiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AggiungiLabel.setForeground(Color.WHITE);
		AggiungiLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_AggiungiPanel = new GroupLayout(AggiungiPanel);
		gl_AggiungiPanel.setHorizontalGroup(
			gl_AggiungiPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(AggiungiLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		gl_AggiungiPanel.setVerticalGroup(
			gl_AggiungiPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(AggiungiLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		AggiungiPanel.setLayout(gl_AggiungiPanel);
		
		AggiungiLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama imbarco view
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AggiungiLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AggiungiLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				AggiungiLabel.setForeground(new Color (150, 150, 150));
				AggiungiPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				AggiungiLabel.setForeground(Color.WHITE);
				AggiungiPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Cerca option */
		JPanel CercaPanel = new JPanel();
		CercaPanel.setBackground(new Color(0, 204, 255));
		CercaPanel.setBounds(0, 200, 200, 85);
		SidePanel.add(CercaPanel);
		
		JLabel CercaLabel = new JLabel("Cerca");
		CercaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CercaLabel.setForeground(Color.WHITE);
		CercaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_CercaPanel = new GroupLayout(CercaPanel);
		gl_CercaPanel.setHorizontalGroup(
			gl_CercaPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(CercaLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		gl_CercaPanel.setVerticalGroup(
			gl_CercaPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(CercaLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		CercaPanel.setLayout(gl_CercaPanel);
		
		CercaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama imbarco view
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CercaLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CercaLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				CercaLabel.setForeground(new Color (150, 150, 150));
				CercaPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				CercaLabel.setForeground(Color.WHITE);
				CercaPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Elimina option */
		JPanel EliminaPanel = new JPanel();
		
		EliminaPanel.setBackground(new Color(0, 204, 255));
		EliminaPanel.setBounds(0, 295, 200, 85);
		SidePanel.add(EliminaPanel);
		
		JLabel EliminaLabel = new JLabel("Elimina");
		EliminaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EliminaLabel.setForeground(Color.WHITE);
		EliminaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_EliminaPanel = new GroupLayout(EliminaPanel);
		gl_EliminaPanel.setHorizontalGroup(
			gl_EliminaPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(EliminaLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		gl_EliminaPanel.setVerticalGroup(
			gl_EliminaPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(EliminaLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		EliminaPanel.setLayout(gl_EliminaPanel);
		
		EliminaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chiama imbarco view
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				EliminaLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				EliminaLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				EliminaLabel.setForeground(new Color (150, 150, 150));
				EliminaPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				EliminaLabel.setForeground(Color.WHITE);
				EliminaPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Top Panel settings */
		JPanel TimePanel = new JPanel();
		MainPanel.add(TimePanel, BorderLayout.NORTH);
		
		TimePanel.setPreferredSize(new Dimension (1150, 50));
		JLabel OraLabel = new JLabel("ORA");
		OraLabel.setEnabled(false);
		OraLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		JLabel OrarioLabel = new JLabel("1 2 : 4 5");
		OrarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		JLabel DataLabel = new JLabel("DATA");
		DataLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		DataLabel.setEnabled(false);
		
		JLabel DataCompletaLabel = new JLabel("M a r  2 6 / 0 1 / 2 0 2 0");
		DataCompletaLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		GroupLayout gl_TimePanel = new GroupLayout(TimePanel);
		gl_TimePanel.setHorizontalGroup(
			gl_TimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TimePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(OraLabel)
					.addGap(16)
					.addComponent(OrarioLabel)
					.addGap(18)
					.addComponent(DataLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(DataCompletaLabel)
					.addContainerGap(633, Short.MAX_VALUE))
		);
		gl_TimePanel.setVerticalGroup(
			gl_TimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_TimePanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_TimePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(OraLabel)
						.addComponent(OrarioLabel)
						.addComponent(DataLabel)
						.addComponent(DataCompletaLabel))
					.addContainerGap())
		);
		TimePanel.setLayout(gl_TimePanel);
	}
}
