package lunarGraphics.scenes;

import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;

import lunarGraphics.LPanel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import lunarGraphics.GraphicButton;
import lunarGraphics.LPanel.GameState;

public class Instruction extends Scene {

	public Instruction(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		GraphicButton retur = new GraphicButton("img/menu/wroc.png",0.5,0.8);
	     retur.setAction("back");
	     graphicObjects.add(retur);
	     
	     
	}
	public void back()
	{
		parentPanel.setState(GameState.Menu);
        parentPanel.initScene(GameState.Menu);
			
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

	@Override
	public void updateScene(Graphics2D g2d) {
		
	try
	{
	
		BufferedImage img=ImageIO.read(new FileInputStream("img/Instruction.png"));
		g2d.drawImage(img,0,0,(int)size.width,(int)size.height,null);
		 for(int i=0; i<graphicObjects.size(); i++)
	        {
	            graphicObjects.get(i).paintImage(g2d, size, preferredSize);
	        }
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}

	@Override
	public void updateLogic(long dt) {

	}

}
