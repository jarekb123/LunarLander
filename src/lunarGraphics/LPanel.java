package lunarGraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
public class LPanel extends JPanel {

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
	 * funkcja wywoływana za kązdym razem gdy coś się zmienia z oknem lub w oknie
	 * @param g zmienna związana z grafiką
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(level.getMap().getImage(),0,0,getWidth(),getHeight(),null);
		Color c = new Color(0,0,0,0);
		g2d.setColor(c);
		g2d.draw(level.getMap().returnMapPolygon(getSize()));
		g2d.drawImage(player.getImage(), (int)(player.getX()*getWidth()),(int)(player.getY()*getHeight()) ,(int)(0.1*getWidth()),(int )(0.1*getHeight()),null);
		g2d.setColor(Color.white);
		g2d.drawString("x: "+player.getX()*640, 0, (int)(getHeight()*0.05));
		g2d.drawString("y: "+player.getY()*480, 0, (int)(getHeight()*0.1));
		g2d.drawString("vX: "+player.getvX()*640, 0, (int)(getHeight()*0.15));
		g2d.drawString("vY: "+player.getvY()*480, 0, (int)(getHeight()*0.2));
		g2d.drawString("g: "+level.getGravity(), 0, (int)(getHeight()*0.25));
		g2d.drawString("Fuel Level: "+player.getFuelLevel(), (int)(getWidth()-100), (int)(getHeight()*0.05));
		g2d.drawString("Time: 0:00", (int)(getWidth()-100), (int)(getHeight()*0.1));
	}
	
	
}
