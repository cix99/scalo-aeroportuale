package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		//setLayout(null);
		setBorder(new EmptyBorder(10, 0, 0, 0));
		setLayout(new GridLayout(5, 1, 0, 10));
		
		/* Imbarco option */
		
		ImbarcoPanel = new JPanel();
		ImbarcoPanel.setBackground(new Color(0, 204, 255));
		ImbarcoPanel.setBounds(0, 10, 200, 85);
		ImbarcoPanel.setLayout(new GridBagLayout());
		add(ImbarcoPanel);
		ImbarcoLabel = new JLabel("Imbarco");
		ImbarcoLabel.setForeground(Color.WHITE);
		ImbarcoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		ImbarcoPanel.add(ImbarcoLabel, gc);
		
		ImbarcoPanel.addMouseListener(new MouseAdapter() {
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
		AggiungiPanel.setLayout(new GridBagLayout());
		add(AggiungiPanel);
		
		AggiungiLabel = new JLabel("Aggiungi");
		AggiungiLabel.setForeground(Color.WHITE);
		AggiungiLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		AggiungiPanel.add(AggiungiLabel, gc);
		
		AggiungiPanel.addMouseListener(new MouseAdapter() {
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
		CercaPanel.setLayout(new GridBagLayout());
		add(CercaPanel);
		
		CercaLabel = new JLabel("Cerca");
		CercaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CercaLabel.setForeground(Color.WHITE);
		CercaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		CercaPanel.add(CercaLabel, gc);
		
		CercaPanel.addMouseListener(new MouseAdapter() {
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
		EliminaPanel.setLayout(new GridBagLayout());
		add(EliminaPanel);
		
		EliminaLabel = new JLabel("Elimina");
		EliminaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EliminaLabel.setForeground(Color.WHITE);
		EliminaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		EliminaPanel.add(EliminaLabel, gc);
		
		EliminaPanel.addMouseListener(new MouseAdapter() {
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
