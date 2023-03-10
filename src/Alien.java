import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Alien extends Block
{
	private long coolDown, shootDelay;

	public Alien(int x, int y)
	{
		super(x,y,30,30,Color.BLUE,"alien");
		shootDelay = Application.rand.nextInt(500,3000);
		coolDown = System.currentTimeMillis();
	}

	private boolean canShoot()
	{
		return  System.currentTimeMillis() - coolDown > shootDelay;
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{
		if (canShoot())
		{
			sceneObjects.add(new Bullet(x+15, y, Application.rand.nextInt(5,15), Color.RED, "alien"));
			shootDelay = Application.rand.nextInt(500,3000);
			coolDown = System.currentTimeMillis();
		}
		
	}
}