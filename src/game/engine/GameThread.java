package game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double ONE_BILLION = 1000000000;

	private final Game game;

	private boolean running;

	public GameThread(Game game) {
		this.game = game;
		setFocusable(true); // Let JPanel get input from the keyboard
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (running) {
			float fps = getFPS();
			if (fps != -1) {
				System.out.println("FPS = " + fps);
			}

			try {
				if (game.getScreenFactory().getCurrentScreen() != null) { // Check if the screen is set
					game.getScreenFactory().getCurrentScreen().onUpdate(); // Update the screen
				}
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public float getFPS() {
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		currentTime = System.nanoTime();
		timer += currentTime - lastTime;
		lastTime = currentTime;

		if (timer >= ONE_BILLION) {
			return ticks;
		}
		return -1;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Keep game from
																									// lagging
		if (game.getScreenFactory().getCurrentScreen() != null) {
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
		repaint();
	}

}
