import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Abschlussranking extends JFrame{

	private Image image = null;
	private StartmenuBackGround mainPanel;
	private JPanel winnerPanel;
	private JPanel buttonPanel;
	private JPanel loserPanel;
	private JLabel winner = new JLabel("           WIN");
	private JLabel second = new JLabel("      2nd Place");
	private JLabel third = new JLabel("      3rd Place");
	private JLabel viert = new JLabel("      4th Place");
	private Vector<JTextArea> name = new Vector<JTextArea>();
	private JButton zurückButton = new JButton("Zurück");
	private JTextArea placewasteNorth = new JTextArea("\n\n\n\n");
	private JTextArea placewasteSouth = new JTextArea("\n\n\n");
	private Vector<String> ranking = new Vector<String>();
	private JFrame f = new JFrame();
	private Game game;
	
	public Abschlussranking(Vector<String> ranking, Game game) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		f = new JFrame("Startmenu");
		this.game = game;
		this.ranking = ranking;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(createMainPanel());
		f.setSize(size.width * 100 / 100, size.height * 100 / 100);
		f.setVisible(true);
	}

	private JPanel createMainPanel() {
		image = Toolkit.getDefaultToolkit().getImage("grüner Hintergrund.PNG");
		mainPanel = new StartmenuBackGround(new BorderLayout());
		mainPanel.setImage(image);
		componentsSettings();
		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.add(createButtonPanel(buttonPanel.isOpaque()), BorderLayout.EAST);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		winnerPanel = new JPanel(new FlowLayout());
		winnerPanel.setOpaque(false);
		winnerPanel.add(createPlayerNorthPanel(winnerPanel.isOpaque()));
		mainPanel.add(winnerPanel, BorderLayout.CENTER);
		loserPanel = new JPanel(new FlowLayout());
		loserPanel.setOpaque(false);
		loserPanel.add(createPlayerSouthPanel(loserPanel.isOpaque()));
		mainPanel.add(loserPanel, BorderLayout.SOUTH);
		return mainPanel;
	}

	private void componentsSettings() {
		
		name.add(new JTextArea(ranking.elementAt(0)));
		name.add(new JTextArea(ranking.elementAt(1)));
		name.add(new JTextArea(ranking.elementAt(2)));
		name.add(new JTextArea(ranking.elementAt(3)));
		
		name.elementAt(0).setFont(new Font("Arial Narrow", Font.BOLD, 58));
		name.elementAt(0).setForeground(Color.WHITE);
		name.elementAt(0).setBackground(new Color(0x404040));
		name.elementAt(0).setColumns(13);
		name.elementAt(0).setEditable(false);
		name.elementAt(1).setFont(new Font("Arial Narrow", Font.BOLD, 42));
		name.elementAt(1).setForeground(Color.WHITE);
		name.elementAt(1).setBackground(new Color(0x404040));
		name.elementAt(1).setColumns(13);
		name.elementAt(1).setEditable(false);
		name.elementAt(2).setFont(new Font("Arial Narrow", Font.BOLD, 42));
		name.elementAt(2).setForeground(Color.WHITE);
		name.elementAt(2).setBackground(new Color(0x404040));
		name.elementAt(2).setColumns(13);
		name.elementAt(2).setEditable(false);
		name.elementAt(3).setFont(new Font("Arial Narrow", Font.BOLD, 42));
		name.elementAt(3).setForeground(Color.WHITE);
		name.elementAt(3).setBackground(new Color(0x404040));
		name.elementAt(3).setColumns(13);
		name.elementAt(3).setEditable(false);
	    zurückButton.setFont(new Font("Arial Narrow", Font.BOLD, 35));
	}

	private JPanel createButtonPanel(boolean opaque) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		zurückButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				game.reset();
				game.mainMenuInstance();
			}
		});
		
		panel.add(zurückButton, BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createPlayerSouthPanel(boolean opaque) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1, 10, 0));
		panel.add(second);
		panel.add(name.elementAt(1));
		panel.add(third);
		panel.add(name.elementAt(2));
		panel.add(viert);
		panel.add(name.elementAt(3));
		panel.add(placewasteSouth);
		panel.setOpaque(opaque);
		second.setOpaque(opaque);
		second.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 42));
		second.setForeground(Color.ORANGE);
		third.setOpaque(opaque);
		third.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 42));
		third.setForeground(Color.ORANGE);
		viert.setOpaque(opaque);
		viert.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 42));
		viert.setForeground(Color.ORANGE);
		placewasteSouth.setOpaque(opaque);
		placewasteSouth.setEditable(opaque);
		return panel;
	}

	private JPanel createPlayerNorthPanel(boolean opaque) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 10, 0));
		panel.add(placewasteNorth);
		panel.add(winner);
		panel.setOpaque(opaque);
		placewasteNorth.setOpaque(opaque);
		placewasteNorth.setEditable(opaque);
		winner.setOpaque(opaque);
		winner.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 58));
		winner.setForeground(Color.ORANGE);
		panel.add(name.elementAt(0));
		return panel;
	}
}
