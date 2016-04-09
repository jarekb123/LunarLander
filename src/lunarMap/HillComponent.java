package lunarMap;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillComponent extends MapComponent {

	Random rnd = new Random();
	double[] x;
	double[] y;
	
	double ys,ye;
	int noOfPointsX;
	public HillComponent()
	{
		super();
		
	}
	public HillComponent(double[] a, double y1,double y2)
	{
		super();
		x=a;
		ys=y1;
		ye=y2;
				
		double miny=Math.min(ye,ys);
		y = new double[x.length];
		y[0] = ys;
		for(int i=1;i<x.length-1;i++)
		{
			//double y[i] = miny+rnd.nextInt((int)(Math.abs(ye-ys) * size.height));
			y[i] = ys + rnd.nextDouble()*Math.abs(ye-ys);
		}
		y[x.length-1] = ye;

	}
	public List<Dimension> draw(Dimension size)
	{
		List<Dimension> dimensions = new ArrayList<Dimension>();
		for(int i=0;i<x.length;i++)
		{
			int xd=(int)(x[i]*size.width);
			
			Dimension dim = new Dimension(xd,(int)(y[i]*size.height));
			dimensions.add(dim);
		}
		return dimensions;
	}
	
}
