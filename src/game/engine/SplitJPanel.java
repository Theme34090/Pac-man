package game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SplitJPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Game game;
	
	public SplitJPanel(Game game) {
		super();
		this.game = game;
		setFocusable(true);	// Let JPanel get input from the keyboard
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("Painting");
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Keep game from
																									// lagging
		if (game.getScreenFactory().getCurrentScreen() != null) {
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
		System.out.println("done Painting");
		repaint();
	}

	
}
