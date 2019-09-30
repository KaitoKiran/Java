import java.util.Vector;

public class Player {
	private String name;
	private Vector<CardModel> hand;
	private int points;
	private boolean pressedUno;
	private Stack stack;
	
	public Player(String name, Stack stack) {
		hand = new Vector<CardModel>();
		this.stack = stack;
	}
	
	public boolean hasFittingCard() {
		for(int i = hand.size() - 1; i >= 0; i--) {
			if(hand.elementAt(i).doesFit(stack.getTopCard()) == true) {
				return true;
			}
		}
		return false;
	}
	
	public void pickUpCard(int playerInTurn, Gameboard board){
		CardModel m = stack.topDeck();
		hand.add(m);
		board.addCard(playerInTurn, m);
	}
	
	public void giveCard(CardModel e) {
		hand.add(e);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void playCard(int index, Gameboard board, int playerInTurn) {
		stack.cardPlay(hand.elementAt(index), board);
		board.removeCard(index, playerInTurn);
		System.out.println("Card has been played " + hand.elementAt(index).getNum());
		hand.remove(index);
	}
	
	public int getCardAmount() {
		return hand.size();
	}
	
	public Vector<CardModel> getHand(){
		return hand;
	}
	
	public boolean pressedUno() {
		return pressedUno;
	}
	
	public void uno() {
		pressedUno = true;
	}
	
	public void unoNoMore() {
		pressedUno = false;
	}
	
	public int getPoints() {
		int b = 0;
		for(CardModel c : hand) {
			b = b + c.getNum();
		}
		return b;
	}
	
}
