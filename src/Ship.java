import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.time.*;

public class Ship extends Block
{
	private int xVel;

	private long coolDown;


	public Ship(int x, int y)
	{
		super(x,y,60,125,Color.BLACK,"ship");
		coolDown = 0;

		System.out.print( System.currentTimeMillis());
	}

	private boolean canShoot()
	{
		return  System.currentTimeMillis() - coolDown > 150;
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{

		if (Application.keys[0] && x - 35 - 15 > 0)
		{
			xVel -= 5;
		}
		if (Application.keys[1] && x + 50 + 15 < Application.SCREENWIDTH)
		{
			xVel += 5;
		}
		if (Application.keys[2])
		{
			coolDown =  System.currentTimeMillis();
			sceneObjects.add(new Bullet(x+30, y, 0, -10, Color.GREEN, "ship"));
			for (int i = -10; i < 10; i++)
			{
				sceneObjects.add(new Bullet(x+30, y, i, -10, Color.GREEN, "ship"));
			}
		}

		xVel = (int)clamp(xVel, -10, 10);
		x += xVel;
		if (xVel != 0)
			xVel += xVel > 0 ? -1 : 1;


	}

	@Override
	public void draw(Graphics g)
	{
		int size = 0;

		g.setColor(Color.BLUE);
		g.fillRect(x+5,y, 50,50);
		g.setColor(Color.RED);
		g.fillRect(x+35,y+50, 25,50);//Wings
		g.fillRect(x,y+50, 25,50);
		g.fillRect(x+25,y-50, 10,50);//Canon thing
		g.fillRect(x+25,y+15, 20,20);//Cockpit
		//g.setColor(Color.GREEN);
		//g.drawRect(x,y,width,height);
	}

}