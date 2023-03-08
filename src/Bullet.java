import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bullet extends Block
{

	public Bullet(int x, int y, Color c)
	{
		super(x,y,5,10,c);
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
		if (y < 0)
		{
			removeThis();
		}
		y -= 10;
	}
}