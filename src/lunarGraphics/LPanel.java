package lunarGraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lunarMap.GameMap;

import lunarMap.Level;

import lunarPlayer.Player;
/**
 * klasa w której przechowywane są wszystkie elementy związane z grą
 * 
 *
 */
@SuppressWarnings("serial")
public class LPanel extends JPanel implements KeyListener{

	/** Obiekt klasy @class Level która przechowuje wszystkie informacje związane z danym poziomem */
	Level level;
	/** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */

	Player player;
	public LPanel()
	{
		setPreferredSize(new Dimension(640, 480));
		level = new Level();
		player = new Player();
		player.loadPlayer("player.properties");
		level.loadLevel("map.properties");
		
	}
	/**

	 * metoda wywoływana za kazdym razem gdy coś się zmienia z oknem lub w oknie
	 * @param g zmienna związana z grafiką
	 * 	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
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
