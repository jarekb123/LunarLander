package lunarMap;

import java.awt.Dimension;
import java.util.List;

public abstract class MapComponent {

	
	public MapComponent()
	{
		
	}
	public abstract List<Dimension> draw(Dimension size);
}
