package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;

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
		
		LocalDateTime time = LocalDateTime.now();
		String hourString = "" + time.getHour() + " : " + time.getMinute();
		OrarioLabel = new JLabel(hourString);
		OrarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		OrarioLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		DataLabel = new JLabel("DATA");
		DataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		DataLabel.setEnabled(false);
		DataLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		String dateString = "" + time.getDayOfWeek().name() + " " + time.getDayOfMonth() + " / " + time.getMonth().getValue() + " / " + time.getYear();
		DataCompletaLabel = new JLabel(dateString);
		DataCompletaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		DataCompletaLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		add(OraLabel);
		add(OrarioLabel);
		add(DataLabel);
		add(DataCompletaLabel);

	}
}
