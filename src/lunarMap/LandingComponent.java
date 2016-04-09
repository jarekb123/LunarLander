package lunarMap;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class LandingComponent extends MapComponent {

	double xs;
	double xe;
	double y;
	
	public LandingComponent()
	{
		super();
	}
	public LandingComponent(double xp,double xk,double yp)
	{
		super();
		xs=xp;
		y=yp;
		xe=xk;

	}
	public List<Dimension> draw(Dimension size)
	{
		int xstart=(int)(size.width * xs);
		int ystart=(int)(size.height*y);
		int xend=(int)(size.width * xe);
		int yend=(int)(size.height*y);
		List<Dimension> toRet=new ArrayList<>();
		
			Dimension dim1=new Dimension(xstart,ystart);
			Dimension dim2=new Dimension(xend,yend);
			toRet.add(dim1);
			toRet.add(dim2);
		return toRet;
	}
}
