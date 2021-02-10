package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class HomeTopPanel extends JPanel {
	
	private JLabel OraLabel;
	private JLabel OrarioLabel;
	private JLabel DataLabel;
	private JLabel DataCompletaLabel;
	private LocalDate date;
	private LocalTime hour;
	private LocalDateTime hourIta;
	private String hourString;
	private String dateString;
	
	public HomeTopPanel () {
		setPreferredSize(new Dimension (1150, 35));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		OraLabel = new JLabel("ORA");
		OraLabel.setEnabled(false);
		OraLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		LocalDateTime time = LocalDateTime.now();
		date = LocalDate.now();
		hour = LocalTime.now();
		String minute;
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        //String formattedHour = hour.format(formatter);
//        if (time.getMinute() < 10)
//        	minute = "0" + time.getMinute();
//        else
//        	minute = "" + time.getMinute();

		hourString = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(hour);
        //String hourString = ""+ time.getDayOfWeek() + " " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(time);

		OrarioLabel = new JLabel(hourString);
		OrarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		OrarioLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		DataLabel = new JLabel("DATA");
		DataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		DataLabel.setEnabled(false);
		DataLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		dateString =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
		//String dateString = "" + time.getDayOfWeek().name() + " " + time.getDayOfMonth() + ", " + time.getMonth().name() + ", " + time.getYear();
		DataCompletaLabel = new JLabel(dateString);
		DataCompletaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		DataCompletaLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		add(OraLabel);
		add(OrarioLabel);
		add(DataLabel);
		add(DataCompletaLabel);

		Timer timer = new Timer ();
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	UpdateTime();
                OrarioLabel.repaint();
            }
        }, 1000, 1000);
	}
	
	public void UpdateTime () {
		date = LocalDate.now();
		hour = LocalTime.now();
		//hourIta = LocalDateTime.now();
		hourString = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(hour);
		//hourString = "" + hourIta.getHour() + ":" + hourIta.getMinute() + ":" + hourIta.getSecond();
		dateString =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
		OrarioLabel.setText(hourString);
		DataCompletaLabel.setText(dateString);
	}
 }
