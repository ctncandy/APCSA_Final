import java.awt.Graphics;

public class GameState extends State
{

	private Player player;
	private World world;
	
	public GameState(Game game)
	{
		super(game);
		player = new Player(game, 100,100);
		world = new World ("/Users/rhea/eclipse-workspace/Sharma_Final/res/worlds/world1.txt");
	}
	
	@Override
	public void tick() 
	{
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		world.render(g);
		player.render(g);
		Tile.tiles[1].render(g, 0,0);
	}

}

