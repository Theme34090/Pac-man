package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;

public class Game implements Runnable {

	private final double ONE_BILLION = 1000000000;
	
	private Display display;

	public int width, height;
	public String title;
	
	private BufferStrategy bs;
	private Graphics graphics;
	
	private boolean running;
	private Thread thread;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		display = new Display(title, width, height);
	}
	
	private void update() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3); // should be no more than 3
			return ;
		}
		graphics = bs.getDrawGraphics();
		// Clear screen
		graphics.clearRect(0, 0, width, height);
		
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
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= ONE_BILLION) {
				System.out.println("FPS = " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
