package lunarGraphics;

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
}
