import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bullet extends Block
{
	private int yVel;

	public Bullet(int x, int y, int yVelocity, Color c)
	{
		super(x,y,5,10,c);
		yVel = yVelocity;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
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

			if (this.isOverlapping(obj) && 
				!obj.equals(this) &&
				obj instanceof Alien) // Alien
			{

				for (int j = 0; j < 20; j++)
				{
					sceneObjects.add(new Particle(x,y,10,10));
				}
				obj.removeThis(sceneObjects);
				removeThis();
			}
		}
		y += yVel;

	}
}