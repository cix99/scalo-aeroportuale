package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {
	
	private JLabel oraLabel;
	private JLabel clockLabel;
	private JLabel dataLabel;
	private JLabel calendarLabel;

	private LocalTime hour;
	private LocalDate date;

	private String hourString;
	private String dateString;
	
	public CalendarPanel () {
		setPreferredSize(new Dimension (1150, 35));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		//setBackground(new Color(255, 255, 255));
		
		oraLabel = new JLabel("ORA");
		oraLabel.setEnabled(false);
		oraLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		hour = LocalTime.now();
		hourString = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(hour);

		clockLabel = new JLabel(hourString);
		clockLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		clockLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		dataLabel = new JLabel("DATA");
		dataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		dataLabel.setEnabled(false);
		dataLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		date = LocalDate.now();
		dateString =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);

		calendarLabel = new JLabel(dateString);
		calendarLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		calendarLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		add(oraLabel);
		add(clockLabel);
		add(dataLabel);
		add(calendarLabel);

		Timer timer = new Timer ();
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	UpdateTime();
                clockLabel.repaint();
            }
        }, 1000, 1000);
	}
	
	public void UpdateTime () {
		date = LocalDate.now();
		hour = LocalTime.now();

		hourString = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(hour);
		dateString =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
		
		clockLabel.setText(hourString);
		calendarLabel.setText(dateString);
	}
 }
