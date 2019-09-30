import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Uno Gameboard
 *
 * @author Cédric Feuz, Kiran Kratz
 * @since 2019-06-15
 * @version 1.0
 *
 */

public class Gameboard extends JFrame {

	private Vector<Player> players;
	private Game game;
	private Stack stack;
	private JFrame f;
	private int temp;
	private Gameboard board = this;
	
	private Image image = null;
	private StartmenuBackGround mainPanel;
	
	private Vector<JPanel> playercardsPanel = new Vector<JPanel>();
	private Vector<Vector<JButton>> playercards = new Vector<Vector<JButton>>();
	
	private JScrollPane playerNorthPanel = new JScrollPane();
	private JPanel playerNameCardsPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JPanel playernameNorthPanel = new JPanel();
	private JScrollPane playerEastPanel = new JScrollPane();
	private JScrollPane playerWestPanel = new JScrollPane();
	private JScrollPane playerSouthPanel = new JScrollPane();
	private JPanel playernameSouthPanel = new JPanel();
	private JPanel boardCenterPanel = new JPanel();
	private JPanel boardCenterCenterPanel = new JPanel();
	private JPanel boardCenterNorthPanel = new JPanel();
	private JPanel boardCenterSouthPanel = new JPanel();
	private JPanel boardCenterSouthCenterPanel = new JPanel();
	private JPanel boardCenterEastPanel = new JPanel();
	private JPanel boardCenterWestPanel = new JPanel();
	
	private PlayerNameChoose player;
	
	private JButton menu = new JButton("   Menu   ");
	private JButton unoButton = new JButton("UNO");
	private JButton doubtButton = new JButton("Doubt");
	private JButton endButton = new JButton("Nächster Spieler");
	
	private Vector<JLabel> playerNames = new Vector<JLabel>();
	
	private ImageIcon centerIcon2 = new ImageIcon("cardstack.png");
	private JLabel visibleCardstack;
	private JLabel hiddenCardstack = new JLabel(centerIcon2);
	
	private Vector<JButton> playercardsNorth = new Vector<JButton>();
	private Vector<JButton> playercardsSouth = new Vector<JButton>();
	private Vector<JButton> playercardsEast = new Vector<JButton>();
	private Vector<JButton> playercardsWest = new Vector<JButton>();
	
	private JTextArea placewasteNorth = new JTextArea("                                                                                                               ");
	private JTextArea placewasteNorth2 = new JTextArea("                                                                                                 ");
	private JTextArea placewasteCeenter = new JTextArea("\n\n\n\n\n\n");
	private JTextArea placewasteCeenter2 = new JTextArea("");
	private JTextArea placewasteCeenter3 = new JTextArea("                                                                                                                                                                                                      ");
	private JTextArea placewasteCeenter4 = new JTextArea("");
	private JTextArea placewasteCeenter5 = new JTextArea("                                                    ");
	
	public Gameboard(Game game, Vector<Player> players, Stack stack) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		f = new JFrame("GUI Design");
		this.players = players;
		this.stack  = stack;
		this.game = game;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(size.width * 100 / 100, size.height * 100 / 100);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(true);
		f.add(createMainPanel());
		nextTurn();
		f.setVisible(true);
	}

	public JPanel createMainPanel() {
		image = Toolkit.getDefaultToolkit().getImage("grüner Hintergrund.PNG");
		mainPanel = new StartmenuBackGround(new BorderLayout());
		mainPanel.setImage(image);
		componentsSettings();
		boardCenterPanel = new JPanel(new FlowLayout());
		boardCenterPanel.setOpaque(false);
		boardCenterPanel.add(createBoardCenterPanel(boardCenterPanel.isOpaque()));
		mainPanel.add(boardCenterPanel, BorderLayout.CENTER);
		playerSouthPanel = new JScrollPane();
		playerSouthPanel.setBorder(new EmptyBorder(0,0,0,0));
		playerSouthPanel.setOpaque(false);
		playerSouthPanel.setViewportView(createPlayerSouthPanel(playerSouthPanel.isOpaque()));
		playerSouthPanel.getViewport().setOpaque(false);
		mainPanel.add(playerSouthPanel, BorderLayout.SOUTH);
		playerNorthPanel = new JScrollPane();
		playerNorthPanel.setBorder(new EmptyBorder(0,0,0,0));
		playerNorthPanel.setOpaque(false);
		playerNorthPanel.setViewportView(createPlayerNorthPanel(playerNorthPanel.isOpaque()));
		playerNorthPanel.getViewport().setOpaque(false);
		mainPanel.add(playerNorthPanel, BorderLayout.NORTH);
		playerWestPanel = new JScrollPane();
		playerWestPanel.setBorder(new EmptyBorder(0,0,0,0));
		playerWestPanel.setOpaque(false);
		playerWestPanel.setViewportView(createPlayerWestPanel(playerWestPanel.isOpaque()));
		playerWestPanel.getViewport().setOpaque(false);
		mainPanel.add(playerWestPanel, BorderLayout.WEST);
		playerEastPanel = new JScrollPane();
		playerEastPanel.setBorder(new EmptyBorder(0,0,0,0));
		playerEastPanel.setOpaque(false);
		playerEastPanel.setViewportView(createPlayerEastPanel(playerEastPanel.isOpaque()));
		playerEastPanel.getViewport().setOpaque(false);
		playerEastPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(playerEastPanel, BorderLayout.EAST);
		return  mainPanel;
	}

	private void componentsSettings() {
		
		playercards.addElement(playercardsNorth);// 0 = north
		playercards.addElement(playercardsEast);// 1 = east
		playercards.addElement(playercardsSouth);// 2 = south
		playercards.addElement(playercardsWest);// 3 = west
		
		playercardsPanel.addElement(new JPanel());// 0 = north
		playercardsPanel.addElement(new JPanel());// 1 = east
		playercardsPanel.addElement(new JPanel());// 2 = south
		playercardsPanel.addElement(new JPanel());// 3 = west
		
		playerNames.add(new JLabel(players.elementAt(0).getName()));
		playerNames.add(new JLabel(players.elementAt(1).getName()));
		playerNames.add(new JLabel(players.elementAt(2).getName()));
		playerNames.add(new JLabel(players.elementAt(3).getName()));
		
		playerNames.elementAt(0).setFont(new Font("Dialog", Font.BOLD, 25));
		playerNames.elementAt(0).setForeground(Color.white);
		playerNames.elementAt(1).setFont(new Font("Dialog", Font.BOLD, 25));
		playerNames.elementAt(1).setForeground(Color.white);
		playerNames.elementAt(2).setFont(new Font("Dialog", Font.BOLD, 25));
		playerNames.elementAt(2).setForeground(Color.white);
		playerNames.elementAt(3).setFont(new Font("Dialog", Font.BOLD, 25));
		playerNames.elementAt(3).setForeground(Color.white);
		menu.setBackground(new Color(0xC0C0C0));
		menu.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1));
		menu.setFont(new Font("Dialog", Font.BOLD, 30));
		unoButton.setFont(new Font("Arial", Font.BOLD, 25));
		doubtButton.setFont(new Font("Arial", Font.BOLD, 25));
		endButton.setFont(new Font("Arial", Font.BOLD, 25));
		boardCenterPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 50));
		
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopupMenu popup = new PopupMenu(board, game);
			}
		});
	}

	private JPanel createPlayerNorthPanel(boolean opaque) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		playerNameCardsPanel.setLayout(new BoxLayout(playerNameCardsPanel, BoxLayout.Y_AXIS));
		panel.setOpaque(opaque);
		panel.add(playerNameCardsPanel, BorderLayout.CENTER);
		panel.add(menuPanel, BorderLayout.EAST);
		playerNameCardsPanel.add(playernameNorthPanel);
		playerNameCardsPanel.add(playercardsPanel.elementAt(0));
		menuPanel.add(menu);
		playernameNorthPanel.setOpaque(opaque);
		playerNameCardsPanel.setOpaque(opaque);
		menuPanel.setOpaque(opaque);
		playernameNorthPanel.add(playerNames.elementAt(0));
		playercardsPanel.elementAt(0).setOpaque(opaque);
		playernameNorthPanel.setOpaque(opaque);
		placewasteNorth.setOpaque(false);
		placewasteNorth.setEditable(false);
		
		for(int i = 0; i < players.elementAt(0).getHand().size(); i++) {
			playercards.elementAt(0).addElement(new JButton(players.elementAt(0).getHand().elementAt(i).getImg()));
			playercards.elementAt(0).elementAt(i).setActionCommand(i + "");
		}
		
		for(int i = 0; i < playercards.elementAt(0).size(); i++) {
			System.out.println("im created");
			playercards.elementAt(0).elementAt(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 0) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 0);
						}
					}
				}
			});
			playercardsPanel.elementAt(0).add(playercards.elementAt(0).elementAt(i));
		}

		placewasteNorth2.setOpaque(false);
		placewasteNorth2.setEditable(false);
		return panel;
	}

	private JPanel createPlayerSouthPanel(boolean opaque) {
		JPanel playernamecards = new JPanel();
		JPanel panel = new JPanel();
		panel.setOpaque(opaque);
		panel.setLayout(new BorderLayout());
		panel.add(playernamecards, BorderLayout.CENTER);
		panel.add(placewasteCeenter5, BorderLayout.EAST);
		playernamecards.setLayout(new BoxLayout(playernamecards, BoxLayout.Y_AXIS));
		playernamecards.setOpaque(opaque);
		playernamecards.add(playercardsPanel.elementAt(2));
		playernamecards.add(playernameSouthPanel);
		playercardsPanel.elementAt(2).setOpaque(opaque);
		playernameSouthPanel.setOpaque(opaque);
		playercardsPanel.elementAt(2).setLayout(new FlowLayout());
		playernameSouthPanel.add(playerNames.elementAt(2));
		placewasteCeenter5.setOpaque(opaque);
		
		for(int i = 0; i < players.elementAt(2).getHand().size(); i++) {
			playercards.elementAt(2).addElement(new JButton(players.elementAt(2).getHand().elementAt(i).getImg()));
			playercards.elementAt(2).elementAt(i).setActionCommand(i + "");
		}
		
		for(int i = 0; i < playercards.elementAt(2).size(); i++) {
			System.out.println("im creater");
			playercards.elementAt(2).elementAt(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 2) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 2);
						}
					}
				}
			});
			playercardsPanel.elementAt(2).add(playercards.elementAt(2).elementAt(i));
		}
		return panel;
	}

	private JPanel createPlayerEastPanel(boolean opaque) {
		playercardsPanel.remove(1);
		playercardsPanel.add(1, new JPanel());
		JPanel panel = new JPanel();
		panel.setOpaque(opaque);
		placewasteCeenter4.setOpaque(opaque);
		placewasteCeenter4.setEditable(false);
		panel.add(playercardsPanel.elementAt(1));
		playercardsPanel.elementAt(1).setOpaque(opaque);
		playercardsPanel.elementAt(1).setLayout(new GridLayout(9, 1));
		playercardsPanel.elementAt(1).add(playerNames.elementAt(1));
		playercards.get(1).removeAllElements();
		for(int i = 0; i < players.elementAt(1).getHand().size(); i++) {
			playercards.elementAt(1).addElement(new JButton(players.elementAt(1).getHand().elementAt(i).getImg()));
			playercards.elementAt(1).elementAt(i).setActionCommand(i + "");
		}
		
		for(int i = 0; i < playercards.elementAt(1).size(); i++) {
			System.out.println("im creater");
			playercards.elementAt(1).elementAt(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 1) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 1);
						}
					}
				}
			});
			playercardsPanel.elementAt(1).add(playercards.elementAt(1).elementAt(i));
		}
		

		return panel;
	}

	private JPanel createPlayerWestPanel(boolean opaque) {
		playercardsPanel.remove(3);
		playercardsPanel.add(3, new JPanel());
		JPanel panel = new JPanel();
		panel.setOpaque(opaque);
		panel.add(playercardsPanel.elementAt(3));
		playercardsPanel.elementAt(3).setOpaque(opaque);
		playercardsPanel.elementAt(3).setLayout(new GridLayout(13, 1));
		playercardsPanel.elementAt(3).add(playerNames.elementAt(3));
		playercards.get(3).removeAllElements();
		for(int i = 0; i < players.elementAt(3).getHand().size(); i++) {
			playercards.elementAt(3).addElement(new JButton(players.elementAt(3).getHand().elementAt(i).getImg()));
			playercards.elementAt(3).elementAt(i).setActionCommand(i + "");
		}
		
		for(int i = 0; i < playercards.elementAt(3).size(); i++) {
			System.out.println("im creater");
			playercards.elementAt(3).elementAt(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 3) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 3);
						}
					}
				}
			});
			playercardsPanel.elementAt(3).add(playercards.elementAt(3).elementAt(i));
		}
		return panel;
	}

	private JPanel createBoardCenterPanel(boolean opaque) {
		
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn();
				if(game.getPlayerInTurn() == 2) {
					playerEastPanel.getVerticalScrollBar().setValue(0);
				}else if(game.getPlayerInTurn() == 3) {
					playerSouthPanel.getVerticalScrollBar().setValue(0);
				}else if(game.getPlayerInTurn() == 0) {
					playerWestPanel.getVerticalScrollBar().setValue(0);
				}else if(game.getPlayerInTurn() == 1) {
					playerNorthPanel.getVerticalScrollBar().setValue(0);
				}
			}
		});
		
		unoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(players.elementAt(game.getPlayerInTurn()).getHand().size() == 1) {
					players.elementAt(game.getPlayerInTurn()).uno();
				}
			}
		});
		
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		boardCenterCenterPanel.setLayout(new FlowLayout());
		boardCenterSouthPanel.setLayout(new BorderLayout());
		boardCenterSouthCenterPanel.setLayout(new FlowLayout());
		panel.setOpaque(opaque);
		boardCenterCenterPanel.setOpaque(opaque);
		panel.add(boardCenterCenterPanel, BorderLayout.CENTER);
		panel.add(boardCenterNorthPanel, BorderLayout.NORTH);
		panel.add(boardCenterSouthPanel, BorderLayout.SOUTH);
		panel.add(boardCenterWestPanel, BorderLayout.WEST);
		panel.add(boardCenterEastPanel, BorderLayout.EAST);
		boardCenterNorthPanel.add(placewasteCeenter);
		boardCenterNorthPanel.setOpaque(opaque);
		boardCenterSouthPanel.setOpaque(opaque);
		boardCenterEastPanel.setOpaque(opaque);
		boardCenterWestPanel.setOpaque(opaque);
		placewasteCeenter.setOpaque(opaque);
		boardCenterSouthCenterPanel.setOpaque(opaque);
		placewasteCeenter.setEditable(false);
		boardCenterCenterPanel.add(hiddenCardstack);
		boardCenterCenterPanel.add(placewasteCeenter2);
		visibleCardstack = new JLabel(stack.getTopCard().getImg());
		boardCenterCenterPanel.add(visibleCardstack);
		boardCenterCenterPanel.add(placewasteCeenter3);
		placewasteCeenter2.setOpaque(opaque);
		placewasteCeenter2.setEditable(false);
		placewasteCeenter3.setOpaque(opaque);
		placewasteCeenter3.setEditable(false);
		boardCenterSouthPanel.add(boardCenterSouthCenterPanel, BorderLayout.SOUTH);
		//boardCenterSouthCenterPanel.add(doubtButton);
		boardCenterSouthCenterPanel.add(unoButton);
		boardCenterSouthCenterPanel.add(endButton);
		return panel;
	}
	
	public void playerRecolorGreen(int playerInTurn) {
		for(JLabel l:playerNames) {
			l.setForeground(Color.white);
		}
		playerNames.elementAt(playerInTurn).setForeground(Color.green);
		f.invalidate();
		f.validate();
		f.repaint();
	}
	
	public void playerRecolorRed(int playerInTurn) {
		for(JLabel l:playerNames) {
			l.setForeground(Color.white);
		}
		playerNames.elementAt(playerInTurn).setForeground(Color.red);
		f.invalidate();
		f.validate();
		f.repaint();
	}
	
	public void updateIndexes() {
		System.out.println("PlayerDeck: " + game.getPlayer(game.getPlayerInTurn()).getHand());
		for (Vector<JButton> p : playercards) {
			for (int i = 0; i < p.size(); i++) {
				p.get(i).setActionCommand(i + "");
			}
		}
	}
	
	public void nextTurn() {
		updateIndexes();
		for (JButton b : playercards.get(game.getPlayerInTurn())) {
			b.setEnabled(true);
		}
		if(game.nextTurn(this) == true) {
			game.endGameInstance();
			f.dispose();
		}
	}
	

	
	public void removeCard(int index, int playerInTurn) {
		playercardsPanel.elementAt(playerInTurn).remove(playercards.elementAt(playerInTurn).elementAt(index));
		playercards.elementAt(playerInTurn).remove(index);
		
		
		for (JButton b : playercards.get(playerInTurn)) {
			b.setEnabled(false);
		}
		
		updateIndexes();
		f.invalidate();
		f.validate();
		f.repaint();
		System.out.println("deleted");
	}
	
	public void addCard(int playerInTurn, CardModel e) {
		playercards.elementAt(playerInTurn).add(new JButton(e.getImg()));
		if(playerInTurn == 1) {
			playercards.elementAt(playerInTurn).elementAt(playercards.elementAt(playerInTurn).size() - 1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 1) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 1);
						}
					}
				}
			});
		}
		if(playerInTurn == 2) {
			playercards.elementAt(playerInTurn).elementAt(playercards.elementAt(playerInTurn).size() - 1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 2) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 2);
						}
					}
				}
			});
		}
		if(playerInTurn == 3) {
			playercards.elementAt(playerInTurn).elementAt(playercards.elementAt(playerInTurn).size() - 1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 3) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 3);
						}
					}
				}
			});
		}
		else {
			playercards.elementAt(playerInTurn).elementAt(playercards.elementAt(playerInTurn).size() - 1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					System.out.println("imMouseEvent");
					System.out.println("color "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getColor()+" num "+players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).getNum());
					System.out.println(game.getPlayerInTurn());
					if(game.getPlayerInTurn() == 0) {
						if(players.elementAt(game.getPlayerInTurn()).getHand().elementAt(Integer.parseInt(action)).doesFit(stack.getTopCard()) == true) {
							System.out.println("confirm");
							players.elementAt(game.getPlayerInTurn()).playCard(Integer.parseInt(action), board, 0);
						}
					}
				}
			});
		}
		updateIndexes();
		playercardsPanel.elementAt(playerInTurn).add(playercards.elementAt(playerInTurn).elementAt(playercards.elementAt(playerInTurn).size() - 1));
		f.invalidate();
		f.validate();
		f.repaint();
		System.out.println("created");
	}
	
	public void layCard(CardModel e) {
		boardCenterCenterPanel.removeAll();
		visibleCardstack = new JLabel(e.getImg());
		boardCenterCenterPanel.add(hiddenCardstack);
		boardCenterCenterPanel.add(placewasteCeenter2);
		boardCenterCenterPanel.add(visibleCardstack);
		boardCenterCenterPanel.add(placewasteCeenter3);
		f.invalidate();
		f.validate();
		f.repaint();
		System.out.println("laid");
	}
}