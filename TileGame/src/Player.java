import java.awt.Graphics;

public class Player extends Creatures{

	public Player(Game game, float x, float y) {
		super(game, x, y, Creatures.DEFAULT_CREATURES_WIDTH, Creatures.DEFAULT_CREATURES_HEIGHT);
	}

	@Override
	public void tick() {
		
		getInput();
		move();
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKEyManager().up) {
			yMove = -speed;
		}
		if(game.getKEyManager().down) {
			yMove = speed;
		}
		if(game.getKEyManager().left) {
			xMove = -speed;
		}
		if(game.getKEyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
		
	}
	
	
	
}
