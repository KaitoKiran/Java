import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, wood, player2, grass, dirt, enemy1, tree, stone;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		player = sheet.crop(0, 0, width, height);
		wood = sheet.crop(width * 2, 0, width, height);
		player2 = sheet.crop(width , 0, width, height);
		grass = sheet.crop(width * 3, 0, width, height);
		dirt = sheet.crop(0, height, width, height);
		enemy1 = sheet.crop(width, height, width, height);
		stone = sheet.crop(width * 2, height, width, height);
		tree = sheet.crop(width * 3, height, width, height);
	}
	
}
