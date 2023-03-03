import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Star extends Block
{
	public Star(int x, int y, int w, int h, Color c)
	{
		super(x,y,w,h,c);
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{

		x -= 3;
		if (x < 0)
		{
			sceneObjects.add(new Star(Application.SCREENWIDTH + width, Application.rand.nextInt(Application.SCREENHEIGHT), width, height, color));
			isGone = true;
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x,y,width,height);
	}
}