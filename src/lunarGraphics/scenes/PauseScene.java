/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lunarGraphics.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import lunarGraphics.GraphicButton;
import lunarGraphics.GraphicObject;

/**
 *
 * @author jarek
 */
public class PauseScene extends Scene {

    public PauseScene(Dimension size, Dimension preferredSize)
    {
        super(size, preferredSize);
        GraphicButton startBtn = new GraphicButton("img/menu/start.png", "img/menu/start_onmouse.png", 0.5, 0.5);
        graphicObjects.add(startBtn);
    }
    @Override
    public void updateScene(Graphics2D g2d) 
    {
        Color transparentBlack = new Color(0f, 0f, 0f, 0.5f);
        g2d.setBackground(transparentBlack);
        g2d.setPaint(transparentBlack);
        g2d.fillRect(0, 0, size.width, size.height);
        for(int i=0; i<graphicObjects.size(); i++)
        {
            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e)
    {
        Point p = e.getPoint();
        for(int i = 0; i<graphicObjects.size(); i++)
        {
            if(graphicObjects.get(i) instanceof GraphicButton)
            {
               GraphicButton gBtn = (GraphicButton)graphicObjects.get(i);
               Rectangle rect = gBtn.getButtonRect(size, preferredSize);
               if(rect.contains(p))
               {
                   gBtn.mouseEntered();
               }
               else
               {
                    gBtn.mouseExited();
               }
            }
        }
    }
    
}
