package game.display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.engine.Screen;
import game.engine.ScreenFactory;

public class GameScreen extends Screen {

	private int x = 0, y = 0;
	private String fps = new String();

	public GameScreen(ScreenFactory screenFactory) {
		super(screenFactory);

	}

	@Override
	public void onCreate() {
		System.out.println("Creating !");
	}

	@Override
	public void onUpdate() {
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_A)) {
			x -= 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_D)) {
			x += 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_W)) {
			y -= 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_S)) {
			y += 2;
		}
		if (x >= super.getScreenFactory().getGame().getJFrame().getWidth() - 64) {
			x = super.getScreenFactory().getGame().getJFrame().getWidth() - 64;
		}
		if (x <= 0) {
			x = 0;
		}
		if (y >= super.getScreenFactory().getGame().getJFrame().getHeight() - 64) {
			y = super.getScreenFactory().getGame().getJFrame().getHeight() - 64;
		}
		if (y <= 0) {
			y = 0;
		}
		// }
		// x++;
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		String fps = String.format("%.2f", super.getScreenFactory().getGame().getGameThread().getFPS());
		g2d.setColor(Color.black);
		// g2d.draw3DRect(x, y, 64, 64, true);
		g2d.fill3DRect(x, y, 64, 64, true);
		g2d.drawString(fps, 10, 20);
		g2d.drawString("x = " + x, 10, 40);
		g2d.drawString("y = " + y, 50, 40);
		// g2d.fillRect(x, y, 64, 64);
	}

}
