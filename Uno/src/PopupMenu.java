import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopupMenu extends JFrame{

	private JButton hauptmenuButton = new JButton("    Hauptmenü     ");
	private JButton neustartButton = new JButton("    Neustart    ");
	private JButton fortsetzenButton = new JButton("    Fortsetzen    ");
	private JPanel mainPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JFrame f;
	private Gameboard board;
	private Game game;
	
	public PopupMenu(Gameboard board, Game game) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		f = new JFrame("Startmenu");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.game = game;
		this.board = board;
		f.add(createMainPanel());
		f.setSize(size.width * 30 / 100, size.height * 30 / 100);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	private JPanel createMainPanel() {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.BLUE);
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.add(createButtonPanel(buttonPanel.isOpaque()));
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		return mainPanel;
	}

	private JPanel createButtonPanel(boolean opaque) {
		JPanel panel = new JPanel();
		
		hauptmenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.dispose();
				f.dispose();
				game.reset();
				game.mainMenuInstance();
				
			}
		});
		
		neustartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				board.dispose();
				game.reset();
				game.preGameInstance();
				
			}
		});
		
		fortsetzenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		
		
		panel.setLayout(new GridLayout(3, 1, 0 , 10));
		panel.setOpaque(opaque);
		panel.add(hauptmenuButton);
		panel.add(neustartButton);
		panel.add(fortsetzenButton);
		hauptmenuButton.setBackground(Color.blue);
		hauptmenuButton.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		hauptmenuButton.setForeground(Color.white);
		neustartButton.setBackground(Color.blue);
		neustartButton.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		neustartButton.setForeground(Color.white);
		fortsetzenButton.setBackground(Color.blue);
		fortsetzenButton.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		fortsetzenButton.setForeground(Color.white);
		return panel;
	}
}
