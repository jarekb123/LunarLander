package lunarGraphics;

public class TimeBonus extends Bonus {

	long extraTime=100000;
	public TimeBonus(String imgPath, double x, double y) {
		super(imgPath, x, y);
	}
	
	public long addTime()
	{
		return extraTime;
	}
	

}
