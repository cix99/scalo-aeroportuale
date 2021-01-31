package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class HomeTopPanel extends JPanel {
	
	private JLabel OraLabel;
	private JLabel OrarioLabel;
	private JLabel DataLabel;
	private JLabel DataCompletaLabel;
	private GroupLayout gl_TimePanel;
	
	public HomeTopPanel () {
		setPreferredSize(new Dimension (1150, 50));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		OraLabel = new JLabel("ORA");
		OraLabel.setEnabled(false);
		OraLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		OrarioLabel = new JLabel("1 2 : 4 5");
		OrarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		DataLabel = new JLabel("DATA");
		DataLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		DataLabel.setEnabled(false);
		
		DataCompletaLabel = new JLabel("M a r  2 6 / 0 1 / 2 0 2 0");
		DataCompletaLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		add(OraLabel);
		add(OrarioLabel);
		add(DataLabel);
		add(DataCompletaLabel);
		
//		gl_TimePanel = new GroupLayout(this);
//		gl_TimePanel.setHorizontalGroup(
//			gl_TimePanel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_TimePanel.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(OraLabel)
//					.addGap(16)
//					.addComponent(OrarioLabel)
//					.addGap(18)
//					.addComponent(DataLabel)
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(DataCompletaLabel)
//					.addContainerGap(633, Short.MAX_VALUE))
//		);
//		gl_TimePanel.setVerticalGroup(
//			gl_TimePanel.createParallelGroup(Alignment.LEADING)
//				.addGroup(Alignment.TRAILING, gl_TimePanel.createSequentialGroup()
//					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//					.addGroup(gl_TimePanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(OraLabel)
//						.addComponent(OrarioLabel)
//						.addComponent(DataLabel)
//						.addComponent(DataCompletaLabel))
//					.addContainerGap())
//		);
//		setLayout(gl_TimePanel);
	}
}
