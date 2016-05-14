package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class GraphicObject {
	protected String imgPath;
	protected double x,y;
	
	public GraphicObject(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public GraphicObject(String imgPath)
	{
		this.imgPath = imgPath;
	}
        public GraphicObject(String imgPath, double x, double y)
        {
            this(x,y);
            this.imgPath = imgPath;
        }
        public void setImgPath(String path)
        {
            imgPath = path;
        }
        abstract public void paintImage(Graphics2D g2d, Dimension size, Dimension preferredSize);
}
