package lunarGraphics;

public class TimeBonus extends Bonus {

	long extraTime=100000;
	public TimeBonus(String imgPath, double x, double y) {
		super(imgPath, x, y);
	}
	
	/**
	 * metoda dodajaca czas playerowi po tym jak bonus zostanie wchloniety
	 * @return dodatkowy czas do dodania
	 */
	public long addTime()
	{
		return extraTime;
	}
	

}
