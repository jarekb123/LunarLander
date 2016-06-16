package lunarGraphics.scenes;

import java.awt.Color;
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

	private int state=3;
	public Instruction(LPanel parent, Dimension size, Dimension preferredSize) {
		super(parent, size, preferredSize);
		GraphicButton retur = new GraphicButton("img/menu/wroc.png",0.2,0.8);
	     retur.setAction("back");
	     GraphicButton next = new GraphicButton("img/menu/nastepne.png",0.85,0.9);
	     
	     next.setAction("next");
	     
	     graphicObjects.add(retur);
	     graphicObjects.add(next);
	     
	     
	}
	/**
	 * metoda zmieniajaca scene spowrotem na menu
	 */
	public void back()
	{
		parentPanel.setState(GameState.Menu);
        parentPanel.initScene(GameState.Menu);
			
	}
	/**
	 * metoda zmieniajaca stan instrukcji co wplywa na wyswietlany tekst
	 */
	public void next()
	{
		if(state<3)
			state++;
		else
			state=1;
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
	                  case "next":
	                  	next();
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
		drawInstruction(g2d);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}
	/**
	 * metoda wyswietlajaca odpowiedni tekst instrukcji
	 * @param g2d
	 */
	public void drawInstruction(Graphics2D g2d)
	{
		if(state==1)
		{
			g2d.fillRect((int)(0.4*size.width),(int)(0.3*size.height),(int)(0.3*size.width) , (int)(0.4*size.width));
			g2d.setColor(new Color(255,255,255));
			int x=(int)(0.4*size.getWidth());
			int y=(int)(0.4*size.getHeight());
			g2d.drawString("Gra polega na tym, ze gracz ma ", x, y);
		//	x=(int)(0.4*size.getWidth());
			y=(int)(0.5*size.getHeight());
			g2d.drawString("wyladowac statkiem jak najszybciej przy ", x, y);
			y=(int)(0.6*size.getHeight());
			g2d.drawString("jak najmnejszym zuzyciu paliwa ", x, y);
		}
		if(state==2)
		{
			g2d.fillRect((int)(0.4*size.width),(int)(0.3*size.height),(int)(0.3*size.width) , (int)(0.65*size.height));
			
			int x=(int)(0.4*size.getWidth());
			int y=(int)(0.4*size.getHeight());
			
			g2d.setColor(new Color(255,255,255));
			g2d.drawLine(0, (int)(size.height*0.05), x, y);
			g2d.drawString("to jest parametr polozenia w osi X", x, y);
			
			y=(int)(0.5*size.getHeight());
			g2d.drawLine(0, (int)(size.height*0.1), x, y);
			g2d.drawString("to jest parametr polozenia w osi Y", x, y);
			
			y=(int)(0.6*size.getHeight());
			g2d.drawLine(0, (int)(size.height*0.15), x, y);
			g2d.drawString("to jest parametr predkosci w osi X", x, y);
			
			y=(int)(0.7*size.getHeight());
			g2d.drawLine(0, (int)(size.height*0.2), x, y);
			g2d.drawString("to jest parametr predkosci w osi Y", x, y);
			
			y=(int)(0.8*size.getHeight());
			g2d.drawLine((int)(size.width*0.9), (int)(size.height*0.05), x+(int)(0.2*size.width), y);
			g2d.drawString("to jest ile zostalo paliwa", x, y);
			
			y=(int)(0.9*size.getHeight());
			g2d.drawLine((int)(size.width*0.9), (int)(size.height*0.15), x+(int)(0.2*size.width), y);
			g2d.drawString("to jest pozostaly czas", x, y);		
		}
		if(state==3)
		{
			g2d.fillRect((int)(0.4*size.width),(int)(0.3*size.height),(int)(0.4*size.height) , (int)(0.4*size.width));
			g2d.setColor(new Color(255,255,255));
			int x=(int)(0.4*size.getWidth());
			int y=(int)(0.4*size.getHeight());
			g2d.drawString("Punkty rowniez zaleza od wybranego ladowiska ", x, y);
			
			y=(int)(0.5*size.getHeight());
			g2d.drawString("systemu gwiazdek-im wiecej gwiazdek tym", x, y);

			y=(int)(0.6*size.getHeight());
			g2d.drawString(" wiecej punktow ", x, y);
		}
		
	}
	@Override
	public void updateLogic(long dt) {

	}

}
