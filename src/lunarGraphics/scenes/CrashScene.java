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

public class CrashScene extends Scene {

	public CrashScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);        
        GraphicButton title = new GraphicButton("img/menu/katastrofa.png", 0.4, 0.2);
        GraphicButton menu = new GraphicButton("img/menu/menu.png", 0.4, 0.7);
        menu.setAction("menu");
        graphicObjects.add(title);
        graphicObjects.add(menu);

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
	public void menu()
	{
		parentPanel.setState(GameState.Menu);
		parentPanel.initScene(GameState.Menu);
	}
	public void end()
	{
		System.exit(2);
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
	                       
	                       case "menu":
	                           menu();
	                           break;
	                       case "end":
	                    	   end();
	                       default:
	                           break;
	                           
	                   }
	               }
	               
	            }
	        }
	    }

	@Override
	public void keyPressed(KeyEvent arg0) {

		}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void updateScene(Graphics2D g2d) {
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
	public void updateLogic(long dt) {
		
	}

}
