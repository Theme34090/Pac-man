package game.engine;

import javax.swing.JFrame;

import game.listener.KeyboardListener;
import game.listener.MousepadListener;

public class Game {

	private final JFrame frame = new JFrame();
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final KeyboardListener keyboardListener;
	private final MousepadListener mousepadListener;
	
	private Thread thread;

	public Game(int window_x, int window_y, String title) {
		frame.setSize(window_x, window_x);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close JFrame and process
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null); // Open window in the center of the screen
		frame.setTitle(title);
		frame.setVisible(true);
		
		this.screenFactory = new ScreenFactory(this);
		this.gameThread = new GameThread(this);
		this.keyboardListener = new KeyboardListener();
		this.mousepadListener = new MousepadListener();

		frame.add(gameThread); // Mean adding JPanel to window
		frame.addKeyListener(keyboardListener);
		frame.addMouseListener(mousepadListener);

		gameThread.start();
	}
	

	public MousepadListener getMouseListener() {
		return mousepadListener;
	}

	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}

	public ScreenFactory getScreenFactory() {
		return this.screenFactory;
	}

	public GameThread getGameThread() {
		return this.gameThread;
	}

	public JFrame getJFrame() {
		return this.frame;
	}

	public JFrame getWindow() {
		return frame;
	}
}