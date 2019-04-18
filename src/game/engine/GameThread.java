package game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.graphics.Assets;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;

public class GameThread implements Runnable /*implements JPanel */ {

	// Static stuff
	private static final double ONE_BILLION = 1000000000;

	private final Game game;
	private int currentFPS;

	// Thread stuff
	private boolean running;
	private Thread thread;
	
	// States
	private GameState gameState;
	private MenuState menuState;

	public GameThread(Game game) {
		this.game = game;
		System.out.println("Thread init");
	}

	@Override
	public void run() {
		while (running) {
			init();
			
			int fps = 60;
			double timePerTick = ONE_BILLION / fps;
			double delta = 0;
			long currentTime;
			long lastTime = System.nanoTime();
			long timer = 0;
			int ticks = 0;
			Screen screen = game.getScreenFactory().getCurrentScreen();

			while (running) {
				currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / timePerTick;
				timer += currentTime - lastTime;
				lastTime = currentTime;

				if (delta >= 1) {
					screen.onUpdate(); // TODO rename onUpdate to render? avoiding confuse with this.update()
					update();
					ticks++;
					delta--;
				}

				if (timer >= ONE_BILLION) {
					System.out.println("FPS = " + ticks);
					this.currentFPS = ticks;
					ticks = 0;
					timer = 0;
				}
			}
			
//			float fps = getFPS();
//			if (fps != -1) {
//				System.out.println("FPS = " + fps);
//			}
//
//			try {
//				if (game.getScreenFactory().getCurrentScreen() != null) { // Check if the screen is set
//					game.getScreenFactory().getCurrentScreen().onUpdate(); // Update the screen
//				}
//				Thread.sleep(10);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
	public void init() {
		Assets.init();
		System.out.println("Thread started");
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}

	// update gameThread status each frame
	public void update() {
		game.getKeyboardListener().update();
		gameState.update();
	}
	
	// Starting gameThread properly
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

//	@Override
//	public void paint(Graphics g) {
//		System.out.println("Painting");
//		super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Keep game from
//																									// lagging
//		if (game.getScreenFactory().getCurrentScreen() != null) {
//			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
//		}
//		System.out.println("done Painting");
//		repaint();
//	}

	// Getters & Setters
	
	public Game getGame() {
		return game;
	}
	
	public int getCurrentFPS() {
		return currentFPS;
	}

	public GameState getGameState() {
		return gameState;
	}
}
