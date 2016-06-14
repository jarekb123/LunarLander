package lunarGraphics;

public class Bonus extends GraphicButton {

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

}
