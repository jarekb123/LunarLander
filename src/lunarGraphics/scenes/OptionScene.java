package lunarGraphics.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel;
import lunarGraphics.LPanel.GameState;

public class OptionScene extends Scene {
	//TODO: jeszcze poprawic aby bylo sciagane z LPANEL
	int state=1;
	String difficultyLevel="latwy";
	public OptionScene(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		state=parentPanel.getDifficultyLevel();
		changeDifficultyLevel();
		GraphicButton sign = new GraphicButton("img/menu/opcje.png", 0.5, 0.3);
	    GraphicButton retur = new GraphicButton("img/menu/wroc.png",0.5,0.8);
	    retur.setAction("back");
	    GraphicButton more = new GraphicButton("img/menu/wiecej.png",0.8,0.5);
	    more.setAction("more");
	    GraphicButton less = new GraphicButton("img/menu/mniej.png",0.3,0.5);
	    less.setAction("less");
	    	    
	    graphicObjects.add(sign);
	    graphicObjects.add(retur);
	    graphicObjects.add(more);
	    graphicObjects.add(less);
	}
	/**
	 * metoda ustawiajaca wybrany poziom trudnosci i wracajaca do menu
	 */
	public void back()
	{
		parentPanel.setDifficultyLevel(state);
		parentPanel.setState(GameState.Menu);
        parentPanel.initScene(GameState.Menu);
			
	}
	/**
	 * metoda zwiekszajaca poziom trudnosci
	 */
	public void more()
	{
		state++;
		changeDifficultyLevel();
	}
	/**
	 * metoda zmniejszajaca poziom trudnosci
	 */
	public void less()
	{
		state--;
		changeDifficultyLevel();
	}
	/**
	 * metoda zmieniajaca poziom trudnosci int na string
	 */
	public void changeDifficultyLevel()
	{
		//state--;
		if(state<1)
		{
			state=3;
		}
		if(state>3)
		{
			state=1;
		}
		if(state==1)
		{
			difficultyLevel="latwy";
		}
		if(state==2)
		{
			difficultyLevel="sredni";
		}
		if(state==3)
		{
			difficultyLevel="trudny";
		}
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
                  case "back":
                	  back();
                	  break;
                  case "more":
                	  more();
                	  break;
                  case "less":
                	  less();
                	  break;
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
	    g2d.setColor(new Color(255,255,0));
	    Font f = new Font("Comic Sans MS", Font.BOLD, 30);
        g2d.setFont(f);
	    g2d.drawString(difficultyLevel, (int)(0.5*size.getWidth()), (int)(0.5*size.getHeight()));
	}

	@Override
	public void updateLogic(long dt) {

	}

}
