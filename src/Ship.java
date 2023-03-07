import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Ship extends Block
{
	public Ship(int x, int y)
	{
		super(x,y,5,5,Color.BLACK);
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{

	}

	@Override
	public void draw(Graphics g)
	{
		int size = 0;

		g.setColor(Color.BLUE);
		g.fillRect(x-25,y-25, 50,50);
		g.setColor(Color.RED);
		g.fillRect(x-35,y+15, 25,50);//Wings
		g.fillRect(x+15,y+15, 25,50);
		g.fillRect(x-5,y-100, 10,50);//Canon thing
		g.fillRect(x-10,y-25, 20,20);//Cockpit
	}
}