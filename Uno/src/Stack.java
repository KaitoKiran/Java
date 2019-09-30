import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;


public class Stack {
	private Vector<CardModel> cardStack;
	private Vector<CardModel> reshuffleStack;
	private CardModel tempCard;
	private CardModel topCard;
	
	public Stack(Vector<CardModel> cardStack) {
		
		reshuffleStack = new Vector<CardModel>();
		this.cardStack = cardStack;
	}
	
	public void shuffle() {
		Collections.shuffle(cardStack);
	}
	
	public void distribute(Vector<Player> players) {
		shuffle();

		for(int i = players.size() - 1; i >= 0;i--) {
			for(int e = 7; e > 0; e--) {
				tempCard = cardStack.firstElement();
				cardStack.remove(tempCard);
				players.elementAt(i).giveCard(tempCard);
			}
		}
		reshuffleStack.addElement(cardStack.elementAt(cardStack.size() - 1));
		System.out.println(cardStack.get(0).color);
		System.out.println(cardStack.get(0).getNum());
		cardStack.remove(reshuffleStack.elementAt(reshuffleStack.size() - 1));
	}
	
	public void restock() {
		Collections.shuffle(reshuffleStack);
		cardStack.addAll(reshuffleStack);
	}
	
	public CardModel getTopCard() {
		return reshuffleStack.get(reshuffleStack.size() - 1);
	}
	
	public CardModel topDeck() {
		tempCard = cardStack.elementAt(cardStack.size() - 1);
		cardStack.remove(tempCard);
		return tempCard;
	}
	
	public void cardPlay(CardModel e, Gameboard board) {
		reshuffleStack.addElement(e);
		board.layCard(e);
	}
	
	public boolean isStackCloseToEmpty() {
		if(cardStack.size() <= 3) {
			return true;
		}
		return false;
	}
}
