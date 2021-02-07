import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;

public class TimeApplet extends Applet implements Runnable
{

	
	// since we can't create an object from the applet we have to include our code to be run in the init
	public void init()
	{
		Thread th = new Thread(this); // we can't create an object from Applet because the applet manager is the one that creates the applets
		// "this" is passed as a runnable where this represents the created instance from this class (which is created via the applet manager)
		th.start();
		// if i called th.run() instead of th.start() the lengthy operation will takeover the main thread and not start the lifecycle correctly
	}
	
	
	public void paint(Graphics g)
	{

		Date d = new Date();
		g.drawString(d.toString(), getWidth()/2, getHeight()/2);
		// in case we inserted this in a while loop this will cause blocking on the browser page that we can't close it
		// so we need to take it out to be in a parallel thread 
		
	}
	
	public void run()
	{
		while(true)
		{
			repaint(); // since it same class
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
}