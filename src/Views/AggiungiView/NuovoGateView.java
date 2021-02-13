package Views.AggiungiView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;

public class NuovoGateView extends JPanel {
	
	private JLabel nuovaTrattaLabel;
	private JLabel dataLabel;
	private JLabel oraLabel;
	private JLabel codaLabel;
	private JLabel nomeLabel;
	private JTextField compagniaText;
	private JTextField dataText;
	private JTextField oraText;
	private JTextField codaText;
	private JTextField nomeText;
	private JPanel Panel;
		
	public NuovoGateView () {	
		/*setPreferredSize (new Dimension(800,650));
		
		Panel = new JPanel();
		Panel.setLayout(new FlowLayout());
		Panel.setSize(800, 650);
		
		nuovaTrattaLabel = new JLabel("NUOVA TRATTA");
		nuovaTrattaLabel.setForeground(Color.blue);
		nuovaTrattaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		Panel.add(nuovaTrattaLabel);*/
		
		setBackground(new Color(0, 100, 0));
	
		
//		centerPanel = new JPanel();
//		centerPanel.setLayout(new FlowLayout());
//		centerPanel.setSize(800,610);
//		destinazioneLabel = new JLabel("Destinazione");
//		destinazioneLabel.setForeground(Color.black);
//	    centerPanel.add(compagniaText);
		
	}

}
