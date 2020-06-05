import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature
{

	private Game game;
	
	public Player(Game game, float x, float y) 
	{
		super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
	}

	@Override
	public void tick() 
	{
		getInput(); 
		move();
		game.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		if(game.getKeyManager().up)
		{
			yMove -= speed;
		}
		if(game.getKeyManager().down)
		{
			yMove = speed;
		}
		if(game.getKeyManager().left)
		{
			xMove = -speed;
		}
		if(game.getKeyManager().right)
		{
			xMove = speed;
		}
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Asset.standStill, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - game.getGameCamera().getxOffset()),
				(int) (y + bounds.y - game.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	
	}

}
