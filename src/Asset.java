/**
 * Creator: Rhea Sharma
 * Date: 5/29/20
 * Period: 5
 * Purpose: Create Sprite Sheet to help reduce the amount of Images loaded into the game
 *          helps to reduce reduncacy by using a variable for the width and height between images
 */

import java.awt.image.BufferedImage;

public class Asset 
{
	
	private static final int width = 86, height = 135;
	
	public static BufferedImage walkLeft, walkRight, tired, standStill, gameWon, walkBack, walkForward;
	
	public static void init()
	{
		
		//Note: Dictory for the sheet should be changed, it was the only way this image would load, its annoying.
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Users/rhea/eclipse-workspace/Sharma_Final/res/textures/spriteSheet.png"));
		tired = sheet.crop(width * 2, 0, width, height);
		standStill = sheet.crop(width * 3, 0, width, height);
		gameWon = sheet.crop(width * 4, 0, width, height);
		
		
	}
}
