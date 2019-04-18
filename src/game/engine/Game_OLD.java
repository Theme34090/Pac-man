package game.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

<<<<<<< Updated upstream:src/game/engine/Game_OLD.java
import game.display.Display;
=======
import display.Display;
import game.graphics.Assets;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;
import input.KeyManager;
>>>>>>> Stashed changes:src/game/Game.java

public class Game_OLD implements Runnable {

	private final double ONE_BILLION = 1000000000;

	private Display display;

	public int width, height;
	public String title;

	private BufferStrategy bs;
	private Graphics graphics;

	private boolean running;
	private Thread thread;

<<<<<<< Updated upstream:src/game/engine/Game_OLD.java
	public Game_OLD(String title, int width, int height) {
=======
	private State gameState;
	private State menuState;

	// Input
	private KeyManager keyManager;

	public Game(String title, int width, int height) {
>>>>>>> Stashed changes:src/game/Game.java
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		
		Assets.init();

		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}

	private void update() {
<<<<<<< Updated upstream:src/game/engine/Game_OLD.java

=======
		keyManager.update();
		if (State.getState() != null)
			State.getState().update();
>>>>>>> Stashed changes:src/game/Game.java
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3); // should be no more than 3
			return;
		}
		graphics = bs.getDrawGraphics();
		// Clear screen
		graphics.clearRect(0, 0, width, height);

<<<<<<< Updated upstream:src/game/engine/Game_OLD.java
=======
		if (State.getState() != null)
			State.getState().render(graphics);

>>>>>>> Stashed changes:src/game/Game.java
//		graphics.setColor(Color.red); everything draws after this will be colored red
//		graphics.fillRect(x, y, width, height);
//		graphics.drawRect(x, y, width, height);

		bs.show(); // tell bs that we finish drawing
		graphics.dispose(); // clear memory
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		double timePerTick = ONE_BILLION / fps;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			timer += currentTime - lastTime;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= ONE_BILLION) {
				System.out.println("FPS = " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public synchronized void start() {
<<<<<<< Updated upstream:src/game/engine/Game_OLD.java
		if (running) {
			return;
		}
=======
		if (running)
			return;
>>>>>>> Stashed changes:src/game/Game.java
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
<<<<<<< Updated upstream:src/game/engine/Game_OLD.java
		if (!running) {
			return;
		}
=======
		if (!running)
			return;
>>>>>>> Stashed changes:src/game/Game.java
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
}
