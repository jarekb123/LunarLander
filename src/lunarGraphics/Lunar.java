import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JFrame;
import lunarMap.Level;
import lunarPlayer.Player;

public class Lunar extends Canvas implements Runnable, KeyListener
{
    
    Dimension minDim, preferredDim;
    BufferStrategy bs;
    Thread thread;
    /** Obiekt klasy @class Level która przechowuje wszystkie informacje związane z danym poziomem */
    Level level;
	/** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */

    Player player;
    Lunar(String propFile)
    {
        level = new Level();
        level.loadLevel("map.properties");
        player = new Player();
        player.loadPlayer("player.properties");
        loadProperties(propFile);
        setPreferredSize(preferredDim);
        setMinimumSize(minDim);
        addKeyListener(this);
    }
    @Override
    public void addNotify()
    {
        super.addNotify();
        createBufferStrategy(2);
        bs = getBufferStrategy();
        
    }
    private void updateGraphics(Graphics2D g2d)
    {
        level.getMap().paintMap(g2d, getSize());
		
	double scaleX = (double)getWidth()/(double)getPreferredSize().getWidth();
	double scaleY = (double)getHeight()/(double)getPreferredSize().getHeight();
	
	g2d.drawImage(player.getImage(), (int)(player.getX()*getWidth()),(int)(player.getY()*getHeight()) ,(int)(player.getImage().getWidth()*scaleX),(int)(player.getImage().getHeight()*scaleY),null);
	g2d.setColor(Color.white);
	g2d.drawString("x: "+player.getX()*640, 0, (int)(getHeight()*0.05));
	g2d.drawString("y: "+player.getY()*480, 0, (int)(getHeight()*0.1));
	g2d.drawString("vX: "+player.getvX()*640, 0, (int)(getHeight()*0.15));
	g2d.drawString("vY: "+player.getvY()*480, 0, (int)(getHeight()*0.2));
	g2d.drawString("g: "+level.getGravity(), 0, (int)(getHeight()*0.25));
	g2d.drawString("Fuel Level: "+player.getFuelLevel(), (int)(getWidth()-100), (int)(getHeight()*0.05));
	g2d.drawString("Time: 0:00", (int)(getWidth()-100), (int)(getHeight()*0.1));
    }
    @Override
    public void run() {
        while (thread == Thread.currentThread()) {
    	    // Prepare for rendering the next frame
            //modifyLocation();
            // Render single frame
            do {
            	// The following loop ensures that the contents of the drawing buffer
            	// are consistent in case the underlying surface was recreated
            	do {
            		// Get a new graphics context every time through the loop
            		// to make sure the strategy is validated
            		Graphics2D graphics = (Graphics2D)bs.getDrawGraphics();
            		updateGraphics(graphics);// Render to graphics
            		graphics.dispose(); // Dispose the graphics

            		// Repeat the rendering if the drawing buffer contents were restored
    	         } while (bs.contentsRestored());
            	// Display the buffer
            	bs.show();
            	// Repeat the rendering if the drawing buffer was lost
	     	} while (bs.contentsLost());
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lunar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    	 }
    }
    private void loadProperties(String filename)
	{
		try 
		{
			InputStream is = new FileInputStream(filename);
			Properties properties = new Properties();
			
			properties.load(is);
			int minResX = Integer.parseInt(properties.getProperty("minimumResX"));
			int minResY = Integer.parseInt(properties.getProperty("minimumResY"));
			minDim = new Dimension(minResX, minResY);
			
			int preferredResX = Integer.parseInt(properties.getProperty("preferredResX"));
			int preferredResY = Integer.parseInt(properties.getProperty("preferredResY"));
			preferredDim = new Dimension(preferredResX, preferredResY);
				
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Blad wczytywania pliku - ustawienia okna: " + filename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
    public static void main(String[] args)
    {
        
        final Lunar lunar = new Lunar("window.properties");
        
        JFrame frame = new JFrame("Lunar v2");
        
        frame.addWindowListener(new WindowAdapter() {

            public void windowIconified(WindowEvent we) {
        		lunar.thread = null;
        	}
            public void windowDeiconified(WindowEvent we) {
        		(lunar.thread = new Thread(lunar)).start(); 
        	}
            public void windowClosing(WindowEvent we) {
            	System.out.println("closing");
            	lunar.thread = null;
            	frame.dispose();
            }
            public void windowClosed(WindowEvent we) {
            	System.out.println("closed");
            	System.exit(1);
            }
        });
        frame.add(lunar);
        frame.pack();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
        (lunar.thread = new Thread(lunar)).start();
    }
    @Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player.goUp();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player.goDown();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN)
			player.stop();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}