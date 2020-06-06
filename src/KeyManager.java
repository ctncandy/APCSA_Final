/**
 * Arrow Key Input
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{

	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean q, w, e;
	
	
	
	public KeyManager()
	{
		keys = new boolean[256];
		
	}
	
	
	public void tick()
	{
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		q = keys[KeyEvent.VK_Q];
		w = keys[KeyEvent.VK_W];
		e = keys[KeyEvent.VK_E];

	}


	@Override
	public void keyPressed(KeyEvent e) 
	{
		keys[e.getKeyCode()] = true;
		System.out.println("Key Pressed: " + e.getID());
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	
}
