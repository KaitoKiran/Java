import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;

/**
 * Startmenu GUI
 *
 * @author Cédric Feuz
 * @since 2019-06-21
 * @version 1.0
 *
 */
public class Startmenu {

	private Image image = null;

	private JButton startButton = new JButton("    Start     ");
	private JButton regelnButton = new JButton("    Regeln    ");
	private JButton beendenButton = new JButton("    Beenden    ");
	private JTextArea platzverschieber = new JTextArea("");
	private StartmenuBackGround mainPanel;
	private JPanel buttonPanel;
	private Game game;
	JFrame f;

	/**
	 * default constructor
	 */
	public Startmenu(Game game) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		f = new JFrame("Startmenu");
		this.game = game;
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(createMainPanel());
		f.setSize(size.width * 100 / 100, size.height * 100 / 100);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(true);
		f.setVisible(true);
	}

	/**
	 * erzeugt das Main-Panel
	 * 
	 * @return das Main-Panel
	 */
	public JPanel createMainPanel() {
		image = Toolkit.getDefaultToolkit().getImage("Startmenu.png");
		mainPanel = new StartmenuBackGround(new BorderLayout());
		mainPanel.setImage(image);
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.add(createButtonPanel(buttonPanel.isOpaque()));
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		return mainPanel;
	}
	/**
	 * erzeugt das Button-Panel
	 * 
	 * @param  bei Übergabe von false wird das Panel durchsichtig
	 * @return das Button-Panel
	 */
	public JPanel createButtonPanel(boolean opaque) {
		JPanel panel = new JPanel(new GridLayout(4, 0, 5, 20));
		beendenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});
		regelnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().edit(new File("spielregeln.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.preGameInstance();
				f.dispose();
			}
		});
		panel.setOpaque(opaque);
		panel.add(startButton);
		panel.add(regelnButton);
		panel.add(beendenButton);
		panel.add(platzverschieber);
		platzverschieber.setOpaque(false);
		startButton.setBackground(new Color(0xFFC800));
		startButton.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.RED));
		startButton.setFont(new Font("Arial", Font.PLAIN, 70));
		startButton.setForeground(Color.RED);
		regelnButton.setBackground(new Color(0xFFC800));
		regelnButton.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.RED));
		regelnButton.setFont(new Font("Arial", Font.PLAIN, 70));
		regelnButton.setForeground(Color.RED);
		beendenButton.setBackground(new Color(0xFFC800));
		beendenButton.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.RED));
		beendenButton.setFont(new Font("Arial", Font.PLAIN, 70));
		beendenButton.setForeground(Color.RED);
		return panel;
	}
	
}