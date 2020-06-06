//draws the world 
import java.awt.Graphics;

public class World 
{
	private Game game;
	private int width, height;
	private int spawnX,spawnY;
	private int[][] tiles;
	
	public World(Game game, String path)
	{
		this.game = game;
		loadWorld(path);
	}
	
	public void tick()
	{
		
	}
	
	//here is where the camera is moved with the player
	public void render(Graphics g) 
	{
		int xStart = 0;
		int xEnd = width;
		int yStart = 0;		
		int yEnd = height;
		for(int y = yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++)
			{
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()));
			}
		}
	}
	
	//collision dedection 
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height)
		{	
			return Tile.grassTile;
		}
		
		
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
		{
			return Tile.grassTile;
		}
		return t;
	}
	
	//load the world1.txt file with tiles
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		
		tiles = new int[width][height];
		
		for(int y = 0;y < height;y++)
		{
			for(int x = 0;x < width;x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
}
	