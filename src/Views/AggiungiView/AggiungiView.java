package Views.AggiungiView;

import Controllers.ViewsController;
import Views.TopPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class AggiungiView extends JFrame{
	
	private TopPanel topPanel;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private JPanel centerPanel;
	private JLabel aggiungiLabel;

	
	public AggiungiView (ViewsController controller) {
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setTitle("Scalo Aeroportuale - Aggiungi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		mainPanel = new JPanel(new BorderLayout(0,0));
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(mainPanel);
		
		topPanel = new TopPanel(controller, false);
		
		centerPanel = new JPanel(new BorderLayout(0,0));
		centerPanel.setBackground(new Color(0, 0, 153));
		
		sidePanel = new JPanel(new BorderLayout());
		sidePanel.setBackground(new Color (0, 153, 255));
		
		aggiungiLabel = new JLabel("   Aggiungi...");
		aggiungiLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		aggiungiLabel.setForeground(Color.WHITE);
		aggiungiLabel.setBorder(new EmptyBorder(5,0,0,0));
		aggiungiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		sidePanel.add(aggiungiLabel, BorderLayout.NORTH);
		sidePanel.add(new AggiungiSidePanel(controller, centerPanel, AggiungiView.this), BorderLayout.CENTER);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        topPanel.UpdateBackButton();
		    }
		});
	}

}