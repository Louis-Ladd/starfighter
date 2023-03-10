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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Application extends JFrame{
    public static final int SCREENHEIGHT = 1000;
    public static final int SCREENWIDTH = 1200;

    public static Random rand;
    private Image dbImage;
    private Graphics dbg;

    private SwingWorker gameLooper;
    private boolean stop;
    
    private int frameCount;
    private int alienCount;

    private ArrayList<Block> sceneObjects = new ArrayList<Block>();

    public static boolean[] keys = {false, false, false};
    
    public Application() 
    {
        addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keys[2] = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keys[0] = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[1] = true;
                    break;
            }
          }
            public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keys[2] = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keys[0] = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[1] = false;
                    break;
            }
          }
        });


        setSize(SCREENWIDTH, SCREENHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("StarFighter");
        setBackground(Color.BLACK);
        rand = new Random();
        frameCount = 0;
        stop = false;

        sceneObjects.add(new Ship(100,SCREENHEIGHT - 150));
        alienCount = 0;

        //Aliens
        int alienCol = 10;
        int alienRow = 2;
        alienCount = alienCol * alienRow;
        int ALIEN_SPACING_X = (SCREENWIDTH - (15 * alienCol)) / (alienCol + 1);

        for (int col = 1; col <= alienCol; col++)
        {
            for (int row = 1; row <= alienRow; row++)
            {
                sceneObjects.add(new Alien((ALIEN_SPACING_X*col)+row*20, 150*row));
            }
        }

        //Stars
        for (int i = 0; i < 500; i++)
        {
            sceneObjects.add(new Star(rand.nextInt(SCREENWIDTH), rand.nextInt(SCREENHEIGHT), 2, 2, Color.WHITE));
        }
        
        
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

        alienCount = 0;

        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        for (int i = 0; i < sceneObjects.size(); i++)
        {
            Block sceneObject = sceneObjects.get(i);

            if(sceneObject instanceof Alien)
            {
                alienCount++;
            }

            sceneObject.logic(sceneObjects);

            if (sceneObject.isGone)
            {
                sceneObjects.remove(i);
                i --;
                continue;
            }
        }
        g.setColor(Color.PINK);
        


        paintComponent(dbg);
        
        g.drawImage(dbImage, 0, 0, this);

        System.out.println("Objects: " + sceneObjects.size());
        long endFrame = System.nanoTime();
        System.out.println(""+((endFrame - startFrame)/1000000) +" MS");
        
    }

    public void paintComponent(Graphics g)
    {
        for (int i = sceneObjects.size()-1; i >= 0; i--) //Draw from top of list to keep "z distance" constant.
        {
            sceneObjects.get(i).draw(g);
        }
    }

    
    public void update() 
    {
        frameCount++;
    }

    public void keyTyped(KeyEvent e)
    {
        System.out.print(e);
    }
}