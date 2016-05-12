import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
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
import javax.swing.JPanel;
import lunarGraphics.Scene;
import lunarMap.Level;
import lunarPlayer.Player;

public class Lunar extends JPanel implements Runnable//, KeyListener
{
    
    Dimension minDim, preferredDim;
    BufferStrategy bs;
    Thread thread;
    Scene scene;
     Lunar(String propFile)
    {
        scene = new Scene();
        loadProperties(propFile);
        setPreferredSize(preferredDim);
        setMinimumSize(minDim);
        //addKeyListener(this);
    }
//    @Override
//    public void addNotify()
//    {
//        super.addNotify();
//        createBufferStrategy(2);
//        bs = getBufferStrategy();
//        
//    }
    @Override
    public void paintComponent(Graphics g)
    {
        scene.updateScene((Graphics2D)g, getSize(), getPreferredSize());
    }
    @Override
    public void run() {
        while (thread == Thread.currentThread()) {
    	    // Prepare for rendering the next frame
            //modifyLocation();
            // Render single frame
            while(true)
            {
                repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lunar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
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
//    @Override
//	public void keyPressed(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_UP)
//		{
//			player.goUp();
//		}
//		if(e.getKeyCode() == KeyEvent.VK_DOWN)
//		{
//			player.goDown();
//		}
//	}
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN)
//			player.stop();
//	}
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
}