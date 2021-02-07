import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Font;

public class StringApplet extends Applet implements Runnable
{
	
	int x = 0;
	int size;
	String s = "JAVA WORLD";
	
	// since we can't create an object from the applet we have to include our code to be run in the init
	public void init()
	{
		Thread th = new Thread(this); // we can't create an object from Applet because the applet manager is the one that creates the applets
		// "this" is passed as a runnable where this represents the created instance from this class (which is created via the applet manager)
		// actually Thread constructor accepts a reference to a "Runnable" interface 
		th.start();
		// if i called th.run() instead of th.start() the lengthy operation will takeover the main thread and not start the lifecycle correctly
	}
	
	public void paint(Graphics g)
	{
		g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		size = g.getFontMetrics().stringWidth(s);
		g.drawString(s, x, getHeight()/2);
		x+=15;
		if (x >= getWidth()- size)
		{
			if(x == getWidth())
				x=0;
			else
				g.drawString(s, x-getWidth(), getHeight()/2);
			
		}
			
	}

	
	public void run()
	{
		while(true)
		{
			repaint(); // since it same class
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
}