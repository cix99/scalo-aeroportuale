package Views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class TopPanel extends JPanel{

	private JPanel backButtonPanel;
	private JButton backButton;
	private JButton logoutButton;
	
	public TopPanel (ViewsController viewController, boolean isInHomeFrame) {
		setLayout(new BorderLayout());

		add(new CalendarPanel(), BorderLayout.WEST);
		backButtonPanel = new JPanel();
		if (isInHomeFrame == false) { 
			ImageIcon backImage = new ImageIcon(new ImageIcon("img/back_arrow.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
			backButton = new JButton(backImage);
			backButton.setFocusPainted(false);
			backButton.setSize(40, 20);
			backButton.setText("Back");
			backButtonPanel.add(backButton);
			backButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					viewController.backToHomeView();
				}
			});
		}
		else { 
			logoutButton = new JButton("Logout"); 
			logoutButton.setFocusPainted(false);
			backButtonPanel.add(logoutButton);
			logoutButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					viewController.logout();
				}
			});
		}
		add(backButtonPanel, BorderLayout.EAST);
	}
	
	public void UpdateBackButton () {
		backButton.repaint();
	}
	
	public void UpdateLogoutButton () {
		logoutButton.repaint();
	}
}
