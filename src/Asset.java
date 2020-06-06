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
	
	private static final int width = 95, height = 95;
	
	public static BufferedImage stone, dirt, standStill, enemy, battle;
	
	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Users/rhea/eclipse-workspace/Sharma_Final/res/textures/spriteSheet.png"));
		stone = sheet.crop(0, 0, width, height);
		standStill = sheet.crop((int)(width * 2.5), 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		enemy = sheet.crop(width * 3, 0, width, height);
		
		battle = (ImageLoader.loadImage("/Users/rhea/eclipse-workspace/Sharma_Final/res/textures/BattleScreen.png"));
	
	}
}
