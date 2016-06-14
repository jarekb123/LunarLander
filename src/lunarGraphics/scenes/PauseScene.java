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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel;
import lunarGraphics.LPanel.GameState;

/**
 *
 * @author jarek
 */
public class PauseScene extends Scene {


    /**
     *
     * @param parent
     * @param size
     * @param preferredSize
     */

    
    

    public PauseScene(LPanel parent, Dimension size, Dimension preferredSize)
    {
        super(parent, size, preferredSize);
        
        GraphicButton resumeBtn = new GraphicButton("img/menu/dalej.png", "img/menu/dalej_onmouse.png", 0.5, 0.3);
        resumeBtn.setAction("resume");
        GraphicButton settingsBtn = new GraphicButton("img/menu/settings.png", "img/menu/settings_onmouse.png", 0.5, 0.45);
        settingsBtn.setAction("settings");
        GraphicButton exitBtn = new GraphicButton("img/menu/koniec.png", "img/menu/koniec_onmouse.png", 0.5, 0.6);
        exitBtn.setAction("exit");
        GraphicButton title = new GraphicButton("img/menu/pauza.png", 0.5, 0.1);
        graphicObjects.add(title);
        graphicObjects.add(resumeBtn);
        graphicObjects.add(settingsBtn);
        graphicObjects.add(exitBtn);
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


    /**
     * Powrót do gry
     */


    public void resume()
    {
        parentPanel.setState(GameState.Play);
        parentPanel.initScene(GameState.Play);
    }


    /**
     * Koniec gry
     */


    public void exit()
    {
        System.exit(2);
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

    public void settings()
    {
    	parentPanel.setState(GameState.Options);
        parentPanel.initScene(GameState.Options);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();
        boolean clicked = false;
        for(int i = 0; i<graphicObjects.size() && !clicked; i++)
        {
            if(graphicObjects.get(i) instanceof GraphicButton)
            {
               GraphicButton gBtn = (GraphicButton)graphicObjects.get(i);
               Rectangle rect = gBtn.getButtonRect(size, preferredSize);
               if(rect.contains(p))
               {
                   clicked = true;
                   String btnAction = gBtn.getAction();
                   switch(btnAction)
                   {
                       
                       case "resume":
                           resume();
                           break;
                       case "exit":
                           exit();
                           break;
                       case "settings":
                       		settings();
                       		break;
                       default:
                           break;
                           
                   }
               }
               
            }
        }
    }
        
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}


    /**
     *
     * @param dt
     */
    @Override
    public void updateLogic(long dt) {
		
	}
    
}
