/**
 * Creator: Rhea Sharma
 * Date: 5/29/20
 * Period: 5
 * Purpose: APCSA Final Project
 */


import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
	//variables
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//states
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	
	//Creates the display of the game
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
	}
	
	
	private void init() 
	{
		display = new Display (title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Asset.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	
	
	
	private void tick()
	{
		keyManager.tick();
		
		if(State.getState() != null)
		{
			State.getState().tick();
		}
	}
	
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//clear screen
		g.clearRect(0, 0, width, height);
		
		//Draws the Action on the Screen
		if(State.getState() != null)
		{
			State.getState().render(g);
		}
		
		//ending drawing
		bs.show();
		g.dispose();
		
	}
	//where the Game is run
	public void run()
	{
		init();
		
		//Help to Reduce lag, kinda used stack overflow because my game was lagging really badly and was almost unplayable
		//Tries to Maintain the game at a 60 frames per second, if computer is slow, it increases, if its fast, it slows the game down 
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) 
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
				
			}
			
			if (timer >= 1000000000)
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	//starts the game
	public synchronized void start() 
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//stops the game
	public synchronized void stop() 
	{
		if(!running)
			return;
		running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
