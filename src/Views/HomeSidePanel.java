package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class HomeSidePanel extends JPanel {

	private JPanel ImbarcoPanel;
	private JLabel ImbarcoLabel;
	private JPanel AggiungiPanel;
	private JLabel AggiungiLabel;
	private JPanel CercaPanel;
	private JLabel CercaLabel;
	private JPanel EliminaPanel;
	private JLabel EliminaLabel;
	
	public HomeSidePanel () {
		setPreferredSize(new Dimension (200,650));
		setBackground(new Color(0, 153, 255));
		setLayout(null);
	
		/* Imbarco option */
		ImbarcoPanel = new JPanel();
		ImbarcoPanel.setBackground(new Color(0, 204, 255));
		ImbarcoPanel.setBounds(0, 10, 200, 85);
		add(ImbarcoPanel);
		ImbarcoLabel = new JLabel("Imbarco");
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
		AggiungiPanel = new JPanel();
		AggiungiPanel.setBackground(new Color(0, 204, 255));
		AggiungiPanel.setBounds(0, 105, 200, 85);
		add(AggiungiPanel);
		
		AggiungiLabel = new JLabel("Aggiungi");
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
				//Chiama aggiungi view
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
		CercaPanel = new JPanel();
		CercaPanel.setBackground(new Color(0, 204, 255));
		CercaPanel.setBounds(0, 200, 200, 85);
		add(CercaPanel);
		
		CercaLabel = new JLabel("Cerca");
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
				//Chiama cerca view
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
		EliminaPanel = new JPanel();
		
		EliminaPanel.setBackground(new Color(0, 204, 255));
		EliminaPanel.setBounds(0, 295, 200, 85);
		add(EliminaPanel);
		
		EliminaLabel = new JLabel("Elimina");
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
			//Chiama elimina view
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
	}
}
