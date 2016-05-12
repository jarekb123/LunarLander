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
public class Scene
{
    /**  Kontekst graficzny */
    Graphics2D g2d; 
       /** Obiekt klasy @class Level która przechowuje wszystkie informacje związane z danym poziomem */
    Level level;
	/** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */

    Player player;

    public Scene()
    {
        level = new Level();
        level.loadLevel("map.properties");
        player = new Player();
        player.loadPlayer("player.properties");
    }

       public void updateScene(Graphics2D g2d, Dimension size, Dimension preferredSize)
       {
        level.getMap().paintMap(g2d, size);
		
	double scaleX = (double)size.width/(double)preferredSize.getWidth();
	double scaleY = (double)size.height/(double)preferredSize.getHeight();
	
	g2d.drawImage(player.getImage(), (int)(player.getX()*size.width),(int)(player.getY()*size.height) ,(int)(player.getImage().getWidth()*scaleX),(int)(player.getImage().getHeight()*scaleY),null);
	g2d.setColor(Color.white);
	g2d.drawString("x: "+player.getX()*640, 0, (int)(size.height*0.05));
	g2d.drawString("y: "+player.getY()*480, 0, (int)(size.height*0.1));
	g2d.drawString("vX: "+player.getvX()*640, 0, (int)(size.height*0.15));
	g2d.drawString("vY: "+player.getvY()*480, 0, (int)(size.height*0.2));
	g2d.drawString("g: "+level.getGravity(), 0, (int)(size.height*0.25));
	g2d.drawString("Fuel Level: "+player.getFuelLevel(), (int)(size.width-100), (int)(size.height*0.05));
	g2d.drawString("Time: 0:00", (int)(size.width-100), (int)(size.height*0.1));
    }
}
