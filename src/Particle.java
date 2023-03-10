import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Particle extends Block
{
	private int xVel, yVel;

	public Particle(int x, int y, int w, int h)
	{
		super(x,y,w,h, Color.YELLOW, "particle");
		xVel = Application.rand.nextInt(-10,10);
		yVel = Application.rand.nextInt(-10,10);

		if (xVel == 0)
			xVel ++;
		if (yVel == 0)
			yVel ++;

		//System.out.println("" + xVel + "  " + yVel);
	}
	public Particle(int x, int y, int w, int h, int xV, int yV)
	{
		super(x,y,w,h, Color.YELLOW, "particle");
		xVel = xV;
		yVel = yV;
		//System.out.println("" + xVel + "  " + yVel);
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{
		x += xVel;
		y += yVel;

		if (x < 0 ||
			y < 0 ||
			x > Application.SCREENWIDTH ||
			y > Application.SCREENHEIGHT)
		{
			removeThis();
		}
	}
}