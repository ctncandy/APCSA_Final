/**
 * Purpose: the battle between enemy and player with player input
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Battle 
{

	
	private boolean running = false;


	private Display display;
	private Game game;

	
	private int PLAYER_HEALTH = 100;
	private int BOSS_HEALTH = 100;
	
	private boolean playerDefend = false;
	private boolean bossDefend = false;
	
	private boolean ready = true;
	private String win = "You Win !";
	private String lose = "You Lose !";
	private int counter =0;


	
	int battleState = 0; 
	public Battle(Game game, Display display)
	{
		this.game = game;
		this.display = display;
	}
	
	public void tick(Graphics g)
	{
		
			//if you beat the boss, display you win
			if (BOSS_HEALTH <= 0)
			{
//				g.setColor(Color.BLUE);
//		        g.fillRect(50, 5* game.getHeight() / 6 - 30, game.getWidth() - 100, 50);
//		        g.setColor(Color.white);
//		        g.drawRect(50, 5 * game.getWidth() / 6 - 30, game.getHeight() - 100, 50);
//				
//				Font small = new Font("Helvetica", Font.BOLD, 14);
//		        FontMetrics metr = display.getFrame().getFontMetrics(small);
//
//		        g.setColor(Color.white);
//		        g.setFont(small);
//		        this.game.setGameOver(true);
//		        g.drawString(win, (game.getWidth() - metr.stringWidth(win)) / 2, 5 * game.getHeight() / 6);
//		        
		        
		        
				//this.game.stop();
		        System.out.println("Game Over battle won");
		        this.game.setGameOver(true);
		        g.setColor(Color.WHITE);
				g.fillRect(0, 0, game.getWidth(),game.getHeight());
				//Draw Here!
				
				
						        

			}
			
			//otherwise display u lose in given time
			else if (counter >= 70000000 && BOSS_HEALTH >= 0)
			{

//				Font small = new Font("Helvetica", Font.BOLD, 14);
//		        FontMetrics metr = display.getFrame().getFontMetrics(small);
//
//		        g.setColor(Color.white);
//		        g.setFont(small);
//		        this.game.setGameOver(true);
//		        g.drawString(lose, (game.getWidth() - metr.stringWidth(lose)) / 2, 5 * game.getHeight() / 6);
				//this.game.stop();

				System.out.println("Game Over batlle lost");
				this.game.setGameOver(true);
				
		        g.setColor(Color.WHITE);
				g.fillRect(0, 0, 100,100);
				//Draw Here!
				
//				
//				 g.setColor(new Color(0, 32, 48));
//			        g.fillRect(50, 5* game.getHeight() / 6 - 30, game.getWidth() - 100, 50);
//			        g.setColor(Color.white);
//			        g.drawRect(50, 5* game.getWidth()/ 6 - 30, game.getWidth() - 100, 50);
//
//			        
//				String start = "LOST ";
//				Font small = new Font("Helvetica", Font.BOLD, 14);
//		        FontMetrics metr = display.getFrame().getFontMetrics(small);
//
//		        g.setColor(Color.white);
//		        g.setFont(small);
//		        g.drawString(start, (game.getWidth() - metr.stringWidth(start)) / 2, 5* game.getHeight() / 6);
//		        
			}
			getInput();
			counter ++;
			System.out.println(BOSS_HEALTH);
			System.out.println(counter);
			
				
		
	}
		
	
	//Player attacks, only q is used, rest are not used due to issues with collision and timer :(
	private void getInput()
	{ 
		//System.out.println("In get Input" + game.getKeyManager().q);
		//ATtack random
		if(game.getKeyManager().w)		
		{
			int attackStrat = (int) (Math.random()*3);
			int playerdamageDealt = (int) (Math.random() * 10);
				
				if(attackStrat == 0)
				{
					BOSS_HEALTH -= playerdamageDealt;
				}
				else if(attackStrat == 1)
				{
					BOSS_HEALTH -= playerdamageDealt;
				}
				else
				{
					BOSS_HEALTH -= playerdamageDealt;
				}

		}
		
		//Heal
		if(game.getKeyManager().w)
		{
			int amountHealed = (int) (Math.random() * 80);
					
			PLAYER_HEALTH += amountHealed;
			if(PLAYER_HEALTH >= 100)
			{
				PLAYER_HEALTH = 100;
			}
			
		}
		
		//Defend
		
		if(game.getKeyManager().e)
		{
			playerDefend = true;
		}
		
	}
	

	
	
	//Method for when the CPU Attacks not used due to issue with collision and timer, would have been used :(
	public void bossAttacks()
	{
		int choice = (int) (Math.random() * 2);
		if (choice == 0)
		{
			int bossamountHealed = (int)(Math.random() * 80);
			BOSS_HEALTH += bossamountHealed;
			if(BOSS_HEALTH >= 100)
			{
				BOSS_HEALTH = 100;
			}
		}
		else if(choice == 1)
		{
			if(playerDefend == false)
			{
				int bossdamageDealt = (int) (Math.random() * 80);
				PLAYER_HEALTH -= bossdamageDealt;
			}
			else 
			{
				return;
			}
		}
	}
}
