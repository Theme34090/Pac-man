package game.entity.creature;

import java.awt.Graphics;
import java.awt.Graphics2D;

import game.engine.Game;
import game.engine.GameThread;
import game.graphics.Assets;

public class Player extends Creatures {

	private GameThread gameThread;
	private Game game;

	public Player(GameThread gameThread, float x, float y) {
		super(x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);
		this.gameThread = gameThread;
		this.game = gameThread.getGame();
		System.out.println("Player init");
	}

	@Override
	public void update() {
		getKeyboardInput();
		move();
	}

	private void getKeyboardInput() {
		xMove = 0;
		yMove = 0;

		if (game.getKeyboardListener().up) {
			yMove = -speed;
		}
		if (game.getKeyboardListener().down) {
			yMove = speed;
		}
		if (game.getKeyboardListener().left) {
			xMove = -speed;
		}
		if (game.getKeyboardListener().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(Assets.player, (int) x, (int) y, width, height, null);
	}

}
