import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Alien extends Block
{
	public Alien(int x, int y)
	{
		super(x,y,30,30,Color.BLUE);
	}
	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{
	}
}