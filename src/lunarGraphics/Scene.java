/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;
import lunarMap.Level;
import lunarPlayer.Player;

/**
 *
 * @author jarek
 */
public class Scene extends Canvas {
    JFrame frame = new JFrame("LunarLander");
    JPanel panel;
    Dimension minDim, preferredDim;
    BufferStrategy bufferStrategy;
    
    
	/** Obiekt klasy @class Level która przechowuje wszystkie informacje związane z danym poziomem */
	Level level;
	/** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */

	Player player;
    public Scene()
    {
        loadProperties("window.properties");
        frame.setMinimumSize(minDim);
        frame.setPreferredSize(preferredDim);
        
        panel = (JPanel)frame.getContentPane();
        panel.add(this);
        panel.setLayout(null);
        Thread graphicThread = new Thread(new GraphicLoop(true, this));
            graphicThread.start();
        frame.pack();
        frame.setVisible(true);
    }
    public void bufferedPaint()
    {
        
    }
    public void addNotify()
    {
        super.addNotify();
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)bufferStrategy.getDrawGraphics();
        super.paint(g2d);
        
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
                
        bufferStrategy.show();
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
    private class GraphicLoop implements Runnable {

	boolean isRunning;
	Canvas panel;
	public GraphicLoop(boolean isRunning, Canvas canvas)
	{
		this.isRunning = isRunning;
		panel = canvas;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
                    repaint();
		}
	}

        }
}
