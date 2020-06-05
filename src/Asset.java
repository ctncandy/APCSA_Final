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
	
	private static final int width = 100, height = 100;
	
	public static BufferedImage stone, dirt, standStill;
	
	public static void init()
	{
		
		//Note: Dictory for the sheet should be changed, it was the only way this image would load, its annoying.
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Users/rhea/eclipse-workspace/Sharma_Final/res/textures/spriteSheet.png"));
		stone = sheet.crop(width, 0, width, height);
		standStill = sheet.crop(width * 2, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		
	}
}
