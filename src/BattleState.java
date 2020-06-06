//State for the Battle 
import java.awt.Graphics;

public class BattleState extends State 
{

	private Battle battle;
	private Graphics g;

	public BattleState(Game game, Display display) 
	{
		super(game);
		battle = new Battle(game, display);
	}

	@Override
	public void tick() 
	{
		battle.tick(g);
	}
	
	public void setG(Graphics g) {
		this.g = g;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

		//battle.tick(g);
	}
}