package lunarGraphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;


import lunarMap.Level;

import lunarPlayer.Player;
/**
 * klasa w której przechowywane są wszystkie elementy związane z grą
 * 
 *
 */
@SuppressWarnings("serial")
public class LPanel extends Canvas implements KeyListener{

	/** Obiekt klasy @class Level która przechowuje wszystkie informacje związane z danym poziomem */
	Level level;
	/** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */

	Player player;
        JPanel panel;
      //  Canvas gameCanvas = new Canvas();
        BufferStrategy bufferStrategy;
	public LPanel()
	{
               
                
		level = new Level();
		player = new Player();
		player.loadPlayer("player.properties");
		level.loadLevel("map.properties");
		setPreferredSize(new Dimension(640,480));
                
               // add(gameCanvas);
            Thread graphicThread = new Thread(new GraphicLoop(true, this));
            graphicThread.start();
               
	}
	/**

	 * metoda wywoływana za kazdym razem gdy coś się zmienia z oknem lub w oknie
	 * @param g zmienna związana z grafiką
	 * 	 */
	public void paintCanvas() {
		// TODO Auto-generated method stub
                Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
		
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
                
                        createBufferStrategy(2);
                        bufferStrategy = getBufferStrategy();
		while(isRunning)
		{
                    paintCanvas();
		}
	}

}
	
}
