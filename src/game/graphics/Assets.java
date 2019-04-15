package game.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 32, SPRITESHEET_HEIGHT = 32; 
	
	public static BufferedImage player;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/dummy.png"));
		
		player = ImageLoader.loadImage("/texture/dummy.png");
	}
	
}
