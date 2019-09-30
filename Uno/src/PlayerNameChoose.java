import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerNameChoose extends JFrame {
	private Image image = null;
	private String[] name = new String[4];
	private JTextField player1 = new JTextField("Spieler1");
	private JTextField player2 = new JTextField("Spieler2");
	private JTextField player3 = new JTextField("Spieler3");
	private JTextField player4 = new JTextField("Spieler4");
	private JTextArea platzverschieber = new JTextArea("\n\n\n\n\n\n\n\n\n\n\n\n");
	private JTextArea platzverschieber2 = new JTextArea("\n\n");
	private JButton weiter = new JButton("weiter");
	private Game game;

	private StartmenuBackGround mainPanel;
	private JPanel namePanel;
	private JFrame f;
	
	public PlayerNameChoose(Game game) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		f = new JFrame("Spielername auswählen");
		this.game = game;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(createMainPanel());
		f.setSize(size.width * 100 / 100, size.height * 100 / 100);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(true);
		f.setVisible(true);
	}

	public JPanel createMainPanel() {
		image = Toolkit.getDefaultToolkit().getImage("NameChoos.PNG");
		mainPanel = new StartmenuBackGround(new BorderLayout());
		mainPanel.setImage(image);
		namePanel = new JPanel(new FlowLayout());
		namePanel.setOpaque(false);
		namePanel.add(createButtonPanel(namePanel.isOpaque()));
		mainPanel.add(platzverschieber, BorderLayout.NORTH);
		mainPanel.add(namePanel, BorderLayout.CENTER);
		return mainPanel;
	}

	private JPanel createButtonPanel(boolean opaque) {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(6, 1, 1, 30);
		panel.setLayout(layout);
		panel.setOpaque(opaque);
		platzverschieber.setOpaque(false);
		platzverschieber.setEditable(false);
		platzverschieber2.setOpaque(false);
		platzverschieber2.setEditable(false);
		panel.add(player1);
		panel.add(player2);
		panel.add(player3);
		panel.add(player4);
		panel.add(platzverschieber2);
		
		weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getPlayer(0).setName(player1.getText());
				game.getPlayer(1).setName(player2.getText());
				game.getPlayer(2).setName(player3.getText());
				game.getPlayer(3).setName(player4.getText());
				
				f.dispose();
				
				game.gameInstance();
			}
		});
		
		panel.add(weiter);
		player1.requestFocus();
		player1.setFont(new Font("Arial Narrow", Font.BOLD, 32));
	    player1.setForeground(Color.WHITE);
	    player1.setHorizontalAlignment(SwingConstants.CENTER);
	    player1.setBackground(new Color(0x404040));
	    player1.setColumns(15);
	    player2.setFont(new Font("Arial Narrow", Font.BOLD, 32));
	    player2.setForeground(Color.WHITE);
	    player2.setHorizontalAlignment(SwingConstants.CENTER);
	    player2.setBackground(new Color(0x404040));
	    player2.setColumns(15);
	    player3.setFont(new Font("Arial Narrow", Font.BOLD, 32));
	    player3.setForeground(Color.WHITE);
	    player3.setHorizontalAlignment(SwingConstants.CENTER);
	    player3.setBackground(new Color(0x404040));
	    player3.setColumns(15);
	    player4.setFont(new Font("Arial Narrow", Font.BOLD, 32));
	    player4.setForeground(Color.WHITE);
	    player4.setHorizontalAlignment(SwingConstants.CENTER);
	    player4.setBackground(new Color(0x404040));
	    player4.setColumns(15);
	    weiter.setBackground(Color.GREEN);
	    weiter.setFont(new Font("Arial Narrow", Font.BOLD, 32));
	    weiter.setBorder(BorderFactory.createEtchedBorder(1, Color.white, Color.white));
	    weiter.setForeground(Color.white);
		return panel;
	}

	public String getName(int index) {
		return name[index];
	}

	public void setName(String name, int index) {
		this.name[index] = name;
	}
}
