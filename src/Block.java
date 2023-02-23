import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Block
{
	protected int x,y,width,height;
	protected Color color;

	public Block(int x, int y, int w, int h, Color c)
	{
		this.color = c;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public boolean getIsGone()
	{
		return false;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}

	public void invertXVel()
	{
	}
	public void invertYVel()
	{
	}

	public static float clamp(float val, float min, float max) 
	{
    	return Math.max(min, Math.min(max, val));
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x,y,width,height);
	}

	public void logic()
	{
	}
	public void logic(ArrayList<Block> sceneObjects)
	{
	}
	public void logic(ArrayList<Block> sceneObjects, Point p)
	{
	}

	public String toString()
	{
		return "block at x: " + x + " y: " + y;
	}

	protected boolean isOverlapping(Block obj2)
	{
		return (x < obj2.getX() + obj2.getWidth() && x + width > obj2.getX() && y < obj2.getY() + obj2.getHeight() && y + height > obj2.getY());
	}
}