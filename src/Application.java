import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;


public class Application extends JFrame {
    public static final int SCREENHEIGHT = 600;
    public static final int SCREENWIDTH = 1200;

    public static Random rand;
    private Image dbImage;
    private Graphics dbg;

    private SwingWorker gameLooper;
    private boolean stop;
    
    private int frameCount;

    private ArrayList<Block> sceneObjects = new ArrayList<Block>();
    
    public Application() 
    {
        setSize(SCREENWIDTH, SCREENHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("StarFighter");
        setBackground(Color.BLACK);
        rand = new Random();
        frameCount = 0;
        stop = false;

        for (int i = 0; i < 500; i++)
        {
            sceneObjects.add(new Star(rand.nextInt(SCREENWIDTH), rand.nextInt(SCREENHEIGHT), 2, 2, Color.WHITE));
        }
        sceneObjects.add(new Ship(200,200));

        
        gameLooper = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while(!stop) {
                    update();
                    repaint();
                    Thread.sleep(17); //~60 FPS
                }
                return null;
            }
        };
        
        gameLooper.execute();
    }

    @Override
    public void paint(Graphics g) {
        //Double Buffered image
        long startFrame = System.nanoTime();

        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        for (int i = 0; i < sceneObjects.size(); i++)
        {
            Block sceneObject = sceneObjects.get(i);
            sceneObject.logic(sceneObjects);
            if (sceneObject.isGone)
            {
                sceneObjects.remove(i);
                i --;
                continue;
            }
        }

        paintComponent(dbg);
        
        g.drawImage(dbImage, 0, 0, this);

        long endFrame = System.nanoTime();
        System.out.println(""+((endFrame - startFrame)/1000000) +" MS");
        
    }

    public void paintComponent(Graphics g)
    {
        for (Block sceneObject : sceneObjects)
        {
            sceneObject.draw(g);
        }
    }

    
    public void update() 
    {
        frameCount++;
    }
}