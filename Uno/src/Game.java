import java.util.Vector;

public class Game {
	private Vector<Player> players;
	private Vector<Player> oPlayers;
	private int playerInTurn;
	private Stack stack;
	private Stack oStack;
	private Startmenu start;
	private PlayerNameChoose playername;
	private Gameboard board;
	//private MenuGui menuGui;
	//private GameBoard gameBoard;
	
	public Game(Stack stack,Vector<Player> players) {
		this.stack = stack;
		this.players = players;
		playerInTurn = players.size() - 1;
	}
	
	public void mainMenuInstance() {
		start = new Startmenu(this);
	}
	
	public void preGameInstance() {
		playername = new PlayerNameChoose(this);
	}
	
	public void gameInstance() {
		stack.distribute(players);
		board = new Gameboard(this, players, stack);
		
		
		
//		for(boolean gameover = false; gameover == false; ) {
//			gameover = turnMechanic(board);
//		}
	}
	
	public void endGameInstance() {
		Abschlussranking rank = new Abschlussranking(ranking(players), this);
	}
	
	public Player getPlayer(int index) {
		return players.elementAt(index);
	}
	
	public boolean nextTurn(Gameboard board) {
		
		if(stack.isStackCloseToEmpty() == true) {
			stack.restock();
		}
		
		//check player 0 cards?
		if(players.elementAt(playerInTurn).getCardAmount() == 0) {
			return true;
		}
		
		//not relevant yet	//check player 1 card? If true -> check if pressed UNO
							//if(players.elementAt(playerInTurn).getHand().size() == 1) {
							//}
		
		if(players.elementAt(playerInTurn).getHand().size() == 1) {
			if(players.elementAt(playerInTurn).pressedUno() != true) {
				players.elementAt(playerInTurn).pickUpCard(playerInTurn, board);
				return false;
			}
		}
		
		//switch to next player
		if(playerInTurn == 3) {
			playerInTurn = 0;
		}
		
		else {
			playerInTurn ++;
		}
		board.playerRecolorGreen(playerInTurn);
		
		//does player have a fitting card? IF NO -> pick up penalty IF This card fits -> lay down
		
		if(players.elementAt(playerInTurn).hasFittingCard() == false) {
			players.elementAt(playerInTurn).pickUpCard(playerInTurn, board);
		}
		
		if(players.elementAt(playerInTurn).hasFittingCard() == false) {
			board.playerRecolorRed(playerInTurn);
			return false;
		}
		return false;
	}
	
	public int getPlayerInTurn() {
		return playerInTurn;
	}
	
	public void reset() {
		players = oPlayers;
		stack = oStack;
	}
	
	public Vector<String> ranking(Vector<Player> players){
		Vector<String> ranking = new Vector<String>();
		
		boolean hasChanged = true;
		while(hasChanged) {
			
			hasChanged = false;
			
			for(int i = 0; i < players.size() - 1; i++) {
				if (players.get(i).getPoints() > players.get(i + 1).getPoints()) {
					Player p = players.get(i + 1);
					players.set(i + 1, players.get(i));
					players.set(i, p);
					hasChanged = true;
				}
			}
		}
		for(Player p : players) {
			ranking.add(p.getName());
		}
		return ranking;
	}
	
}
