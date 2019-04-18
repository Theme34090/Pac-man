package game.display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.engine.Game;
import game.engine.GameThread;
import game.engine.Screen;
import game.engine.ScreenFactory;
import game.listener.KeyboardListener;
import game.tile.Tile;

public class GameScreen extends Screen {

	private int x = 0, y = 0;
	private String fps;
	private KeyboardListener keyboardListener;
	private Game game;
	private GameThread gameThread;

	public GameScreen(ScreenFactory screenFactory) {
		super(screenFactory);
		keyboardListener = screenFactory.getGame().getKeyboardListener();
		game = screenFactory.getGame();
		gameThread = screenFactory.getGame().getGameThread();
	}

	@Override
	public void onCreate() {
		System.out.println("Creating !");
	}

	@Override
	public void onUpdate() {
		if (keyboardListener.isKeyPressed(KeyEvent.VK_A)) {
			x -= 2;
		}
		if (keyboardListener.isKeyPressed(KeyEvent.VK_D)) {
			x += 2;
		}
		if (keyboardListener.isKeyPressed(KeyEvent.VK_W)) {
			y -= 2;
		}
		if (keyboardListener.isKeyPressed(KeyEvent.VK_S)) {
			y += 2;
		}
		if (x >= getScreenFactory().getGame().getJFrame().getWidth() - 64) {
			x = getScreenFactory().getGame().getJFrame().getWidth() - 64;
		}
		if (x <= 0) {
			x = 0;
		}
		if (y >= getScreenFactory().getGame().getJFrame().getHeight() - 64) {
			y = getScreenFactory().getGame().getJFrame().getHeight() - 64;
		}
		if (y <= 0) {
			y = 0;
		}
		// }
		// x++;
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		// draw current status
		String fps = String.format("%d", getScreenFactory().getGame().getGameThread().getCurrentFPS());
		g2d.drawString(fps, 10, 20);
		g2d.drawString("x = " + x, 10, 40);
		g2d.drawString("y = " + y, 50, 40);

		// draw game
		gameThread.getGameState().render(g2d); // draw player
		
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				Tile.tiles[0].render(g2d, i, j);
//			}
//		}
		
		g2d.setColor(Color.black);
		g2d.fill3DRect(x, y, 64, 64, true);
		

	}

}
