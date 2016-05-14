/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics.scenes;

import java.awt.*;
import lunarMap.Level;
import lunarPlayer.Player;

/**
 *
 * @author jarek
 */
public class GameScene extends Scene
{
    /** Obiekt @class Level, który przechowuje stan poziomu (w tym mapę) */
    Level level;
   /** Obiekt @class Player, która przechowuje wszystkie informacje związane z danym graczem */
    Player player;
    public GameScene(Dimension size, Dimension preferredSize)
    {
        super(size, preferredSize);
        level = new Level();
        level.loadLevel("map.properties");
        
        player = new Player();
        player.loadPlayer("player.properties");
        
        graphicObjects.add(player);
    }

    @Override
    public void updateScene(Graphics2D g2d)
    {
        if(isResized)
            level.getMap().paintMap(g2d, size);
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
    }

}
