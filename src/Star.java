import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Star extends Block
{
	public Star(int x, int y, int w, int h, Color c)
	{
		super(x,y,w,h,c,"star");
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{

		y -= 1;
		if (y < 0)
		{
			x = Application.rand.nextInt(Application.SCREENWIDTH);
			y = Application.rand.nextInt(Application.SCREENHEIGHT) + Application.SCREENHEIGHT;
		}
	}

	@Override
	public void removeThis(ArrayList<Block> sceneObjects)
	{
		sceneObjects.add(new Star(Application.rand.nextInt(Application.SCREENWIDTH), Application.SCREENHEIGHT, width, height, color));
		isGone = true;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x,y,width,height);
	}
}