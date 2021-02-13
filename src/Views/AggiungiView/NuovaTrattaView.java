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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class NuovaTrattaView extends JPanel {
	
//	private JLabel destinazioneLabel;
//	private JLabel nuovaTrattaLabel;
//	private JLabel dataLabel;
//	private JLabel oraLabel;
//	private JLabel codaLabel;
//	private JLabel nomeLabel;
//	private JLabel compagniaLabel;
//	private JTextField destinazioneText;
//	private JTextField compagniaText;
//	private JTextField dataText;
//	private JTextField oraText;
//	private JTextField codaText;
//	private JTextField nomeText;
//	private JPanel panel;
	private JTextField destinazioneText;
	private JTextField compagniaText;
	private JLabel compagniaLabel;
	private JTextField nomeText;
	private JTextField dataText;
	private JTextField oraText;
		
	public NuovaTrattaView () {	
		
		setBackground(new Color(0, 0, 153));
		
		destinazioneText = new JTextField();
		destinazioneText.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		destinazioneText.setColumns(10);
		
		JLabel destinazioneLabel = new JLabel("Destinazione");
		destinazioneLabel.setForeground(Color.WHITE);
		destinazioneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		compagniaText = new JTextField();
		compagniaText.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaText.setColumns(10);
		
		compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JButton salvaButton = new JButton("Salva");
		salvaButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton annullaButton = new JButton("Annulla");
		annullaButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel codaLabel = new JLabel("Coda");
		codaLabel.setForeground(Color.WHITE);
		codaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JComboBox<String> codaComboBox = new JComboBox<String>();
		codaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codaComboBox.setEditable(true);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		nomeText = new JTextField();
		nomeText.setColumns(10);
		
		dataText = new JTextField();
		dataText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		dataText.setColumns(10);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setForeground(Color.WHITE);
		dataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JLabel oraLabel = new JLabel("Ora");
		oraLabel.setForeground(Color.WHITE);
		oraLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		oraText = new JTextField();
		oraText.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(codaComboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(codaLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(24)
											.addComponent(nomeText, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(annullaButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addGap(18)))
									.addComponent(salvaButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(77))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(nomeLabel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(664, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(dataLabel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(dataText, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
									.addGap(39)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(oraText, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addComponent(oraLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
								.addComponent(compagniaText, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
								.addComponent(compagniaLabel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(destinazioneText, 444, 444, 444)
								.addComponent(destinazioneLabel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(419, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addComponent(destinazioneLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(destinazioneText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(compagniaLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(compagniaText, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(codaLabel)
						.addComponent(nomeLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nomeText, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
						.addComponent(codaComboBox, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dataLabel)
						.addComponent(oraLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(oraText)
						.addComponent(dataText))
					.addGap(124)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(salvaButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(annullaButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(56))
		);
		setLayout(groupLayout);
		
		
		
		
		
		
		
		
		
		
		
		//panel = new JPanel(new GroupLayout(panel));
		
//		GroupLayout layout = new GroupLayout(panel);
//		 panel.setLayout(layout);
//		 
//		 layout.setAutoCreateGaps(true);
//		 layout.setAutoCreateContainerGaps(true);
//		 
//		 layout.setHorizontalGroup(
//				   layout.createSequentialGroup()
//				      .addComponent(destinazioneLabel)
//				      .addComponent(destinazioneText)
//				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//				           .addComponent(compagniaLabel)
//				           .addComponent(compagniaText))
//				);
//				layout.setVerticalGroup(
//				   layout.createSequentialGroup()
//				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//				           .addComponent(destinazioneLabel)
//				           .addComponent(destinazioneText)
//				           .addComponent(compagniaLabel))
//				      .addComponent(compagniaText)
//				);
	
			
	}
}
