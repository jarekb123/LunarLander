package lunarGraphics;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import lunarGraphics.scenes.GameScene;
import lunarGraphics.scenes.*;
public class LPanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    
    Dimension minDim, preferredDim;
    BufferStrategy bs;
    Thread thread;
    Scene scene;
    GameScene gameScene;
    long time=System.currentTimeMillis();



 

    public enum GameState
    {
        Play, Pause
    }
    GameState state;
    
    LPanel(String propFile)
    {
        state = GameState.Play;
        scene = null;
  
        loadProperties(propFile);
        setPreferredSize(preferredDim);
        setMinimumSize(minDim);
        addKeyListener(this);
        gameScene = new GameScene(this, getSize(), preferredDim);
        grabFocus();
        initScene(state);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
//    @Override
//    public void addNotify()
//    {
//        super.addNotify();
//        createBufferStrategy(2);
//        bs = getBufferStrategy();
//        
//    }
   
    public void initScene(GameState gs)
    {
        if(gs == GameState.Play)
            scene = gameScene;
        else if(gs == GameState.Pause)
            scene = new PauseScene(this, getSize(), preferredDim);
    } 
    @Override
    public void paintComponent(Graphics g)
    {
        scene.updateScene((Graphics2D)g);
    }
    @Override
    public void run() {
        long currTime;
        long dt;
        time=System.currentTimeMillis();
    	while (thread == Thread.currentThread()) {
    	    // Prepare for rendering the next frame
            //modifyLocation();
            // Render single frame
            while(true)
            {
                currTime=System.currentTimeMillis();
                dt=currTime-time;
            	scene.updateLogic(dt);
            	time=currTime;
            	repaint();
                
               
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(LPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    public void init(String[] args)
    {
        
        final LPanel lunar = new LPanel("window.properties");
        
        JFrame frame = new JFrame("Lunar v2");
        frame.addKeyListener(lunar);
        frame.addMouseListener(lunar.scene);
        frame.addMouseMotionListener(lunar);
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
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                lunar.scene.resized(lunar.getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
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
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            initScene(GameState.Pause);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
       scene.keyPressed(e); 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if(state == GameState.Play)
                initScene(setState(GameState.Pause));
            else if(state == GameState.Pause)
                initScene(setState(GameState.Play));    
        }
        else scene.keyReleased(e);
    }
    public GameState setState(GameState gs)
    {
        return state = gs;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(scene != null)
            scene.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(scene != null)
            scene.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(scene != null)
            scene.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(scene != null)
            scene.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(scene != null)
            scene.mouseExited(e);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(scene!=null)
            scene.mouseMoved(e);
    }
}