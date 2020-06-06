//Stone tile class, returns that it is a solid object. 
public class Stone extends Tile
{

	public Stone(int id) 
	{
		super(Asset.stone, id);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}
	
}
