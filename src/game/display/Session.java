package game.display;

import game.engine.Game;

public class Session {

	private Game game;

	public Session() {
		this.game = new Game(800, 600, "My Game");
		this.game.getScreenFactory().showScreen(new GameScreen(game.getScreenFactory()));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Session();
	}

}
