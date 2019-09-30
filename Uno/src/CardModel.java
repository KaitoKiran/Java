
import javax.swing.ImageIcon;

public abstract class CardModel {
	protected int color;
	protected ImageIcon img;
	protected int num;
	
	public CardModel(int num, int color, ImageIcon img) {
		this.img = img;
		this.color = color;
		this.num = num;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getNum() {
		return num;
	}
	
	public boolean doesFit(CardModel e) {
		
		System.out.println(color + " " + e.getColor() + " : " + num + " " + e.getNum());
		
		if(color == e.getColor()) {
			System.out.println("works");
			return true;
		}
		else if(num == e.getNum()) {
			System.out.println("works");
			return true;
		}
		return false;
	}
	
	public ImageIcon getImg() {
		return img;
	}
	
	public String toString() {
		return "Color: " + color + " - Num: " + num;
	}
	
	public abstract void effect();
	
}
