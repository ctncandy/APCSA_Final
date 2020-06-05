
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
