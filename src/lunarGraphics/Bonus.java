package lunarGraphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Bonus extends GraphicButton {

	boolean touched=false;
	public Bonus(String imgPath, double x, double y) {
		super(imgPath, x, y);

	}
	/**
	 * metoda zwracajaca polozenie x bonusu
	 * @return polozenie w osi x
	 */
	public double getX()
	{
		return this.x;
	}
	/**
	 * metoda zwracajaca polozenie y bonusu
	 * @return polozenie w osi y
	 */
	public double getY()
	{
		return this.y;
	}
	/**
	 * metoda ustawiajaca wspolrzedna X bonusu
	 * @param X
	 */
	public void setX(double X)
	{
		x=X;
	}
	/**
	 * metoda ustawiajaca wsporzedna Y bonusu
	 * @param Y
	 */
	public void setY(double Y)
	{
		y=Y;
	}
	/**
	 * metoda ustawiajaca,ze dany bonus zostal pochloniety
	 */
	public void touched()
	{
		touched=true;
		
	}
	/**
	 * metoda sprawdzajaca wartosc pola touched, czyli czy bonus zostal pochloniety
	 * @return
	 */
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
