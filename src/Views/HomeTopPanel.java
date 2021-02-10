package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class HomeTopPanel extends JPanel {
	
	private JLabel OraLabel;
	private JLabel OrarioLabel;
	private JLabel DataLabel;
	private JLabel DataCompletaLabel;
	
	public HomeTopPanel () {
		setPreferredSize(new Dimension (1150, 35));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		OraLabel = new JLabel("ORA");
		OraLabel.setEnabled(false);
		OraLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		OrarioLabel = new JLabel("1 2 : 4 5");
		OrarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		OrarioLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		DataLabel = new JLabel("DATA");
		DataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		DataLabel.setEnabled(false);
		DataLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		DataCompletaLabel = new JLabel("M a r   2 6 / 0 1 / 2 0 2 0");
		DataCompletaLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		DataCompletaLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		add(OraLabel);
		add(OrarioLabel);
		add(DataLabel);
		add(DataCompletaLabel);

	}
}
