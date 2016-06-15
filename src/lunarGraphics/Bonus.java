package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Bonus extends GraphicButton {

	boolean touched=false;
	public Bonus(String imgPath, double x, double y) {
		super(imgPath, x, y);

	}
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public void setX(double X)
	{
		x=X;
	}
	public void setY(double Y)
	{
		y=Y;
	}
	public void touched()
	{
		touched=true;
		
	}
	public boolean iftouched()
	{
		return touched;
	}
	@Override
	public void paintImage(Graphics2D g2d,Dimension size, Dimension preferredSize)
	{
		if(!touched)
		{
			super.paintImage(g2d, size, preferredSize);
		}
	}
	

}
