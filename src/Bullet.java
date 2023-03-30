//Louis Harshman - Starfighter
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.Math;

public class Bullet extends Block
{
	private int xVel,yVel;

	public Bullet(int x, int y, int yVelocity, Color c)
	{
		super(x,y,10,20,c);
		xVel = 0;
		yVel = yVelocity;
	}
	public Bullet(int x, int y, int yVelocity, Color c, String t)
	{
		super(x,y,10,20,c,t);
		xVel = 0;
		yVel = yVelocity;
	}
	public Bullet(int x, int y, int xVelocity, int yVelocity, Color c, String t)
	{
		super(x,y,10,20,c,t);
		xVel = xVelocity;
		yVel = yVelocity;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{

		if (y < 0 || y > Application.SCREENHEIGHT)
		{
			removeThis();
		}

		for (int i = sceneObjects.size()-1; i >= 0; i--)
		{
			Block obj = sceneObjects.get(i);
			if (obj.getTag().equals("star") ||
				obj.getTag().equals("particle"))
				continue;

			if (this.isOverlapping(obj) && 
				!getTag().equals(obj.getTag()))
			{
				obj.removeThis(sceneObjects);
				removeThis();
				break;
			}
		}

		x += xVel;
		y += yVel;
	}
}