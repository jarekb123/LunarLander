package lunarGraphics;

import javax.swing.JPanel;

public class GraphicLoop implements Runnable {

	boolean isRunning;
	JPanel panel;
	public GraphicLoop(boolean isRunning, JPanel jp)
	{
		this.isRunning = isRunning;
		panel = jp;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			panel.repaint();
		}
	}

}
