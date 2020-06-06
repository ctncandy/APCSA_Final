/**
 * The main class, where all methods and classes are called.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JLabel;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;



	public boolean gameOver;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private GameState gameState;
	private MenuState menuState;
	private BattleState battleState;
	
	
	//Input
	private KeyManager keyManager;
	
	//Camera
	private GameCamera gameCamera;
	
	private int counter = 0;
	
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		this.gameOver = false;
	}
	//intialization of the program 
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Asset.init();
		
		gameCamera = new GameCamera(this, 0, 0);
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		battleState = new BattleState(this, display);
		State.setState(gameState);
	}
	
	private void tick(){
		if(gameOver != true)
		{		keyManager.tick();
		
			if(State.getState() != null)
				State.getState().tick();
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		//Draw Here!
		gameState.render(g);
		
		 g.setColor(new Color(0, 32, 48));
	        g.fillRect(50, 5*height / 6 - 30, width - 100, 50);
	        g.setColor(Color.white);
	        g.drawRect(50, 5 * width / 6 - 30,width - 100, 50);

	        
		String start = "Use the Arrow Keys to move";
		Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = display.getFrame().getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(start, (width - metr.stringWidth(start)) / 2, 5*height / 6);
		
		//g.drawImage(Asset.battle, 0, 0, getWidth(), getHeight(), null);
		//State.setState(battleState);
		
	
		if(getKeyManager().q)
		{
			g.drawImage(Asset.battle, 0, 0, getWidth(), getHeight(), null);
			showKillScreen(g, 1);
		}
		
		//if(State.getState() != null)
		//	State.getState().tick(); 
		
		
		//End Drawing!
		bs.show(); 
		g.dispose();
	}
	
	//start the battle
	private void showKillScreen(Graphics g, int state) { 

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, 5*height / 6 - 30, width - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, 5*width / 6 - 30,width - 100, 50);

        String battle = "BATTLE: Press q to beat the evil bear, w to heal, and e to defend";
        String start = "Use the Arrow Keys to move";
        
        String s;
        if (state == 0) s = start; 
        else s = battle;
        
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = display.getFrame().getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(s, (width - metr.stringWidth(s)) / 2, 5*height / 6);
        battleState.setG(g);
        State.setState(battleState);
		
   
    }
	
	//run the program using frames per second and nanotime, did conflict with timer
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				counter++;
				ticks = 0;
				timer = 0;
			}
		}
		g.drawString("Game Over game",getWidth() ,getHeight() );
		System.out.println("Game over game class");
		
		stop();
		
	}
	
	//getter 
	public KeyManager getKeyManager(){
		return keyManager;
	}
	//getter 
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	//getter 
	public int getWidth(){
		return width;
	}
	//getter 
	public int getHeight(){
		return height;
	}
	//start program
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	//stop program 
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//getter 
	public World getWorld() 
	{
		return gameState.getWorld(); 
	}
	
	//start the battle and set the state to battle state
	public void startBattle()
	{
		State.setState(battleState);
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	


}