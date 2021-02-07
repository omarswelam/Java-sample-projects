import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class MyApplet extends Applet implements Runnable{
	boolean turnOn = true;
	
	public void init()
	{
		Thread th = new Thread(this); // we can't create an object from Applet because the applet manager is the one that creates the applets
		// "this" is passed as a runnable where this represents the created instance from this class (which is created via the applet manager)
		th.start();
		// if i called th.run() instead of th.start() the lengthy operation will takeover the main thread and not start the lifecycle correctly
	}
	
	public void paint(Graphics g)
	{
		int x = 110;
		g.fillOval(50+x,10+x,200,50);
		g.fillOval(52+x,85+x,40,55);
		g.fillOval(210+x,85+x,40,55);
		g.fillOval(125+x,70+x,50,100);
		
		g.drawLine(50+x,35+x,35+x,150+x);
		g.drawLine(250+x,35+x,265+x,150+x);
		g.drawArc(35+x, 110+x, 230, 80, 180,180);
		
		g.drawLine(140+x,189+x,125+x,270+x);
		g.drawLine(160+x,189+x,175+x,270+x);
		g.drawRect(60+x,270+x,180,30);
		
		while(true)
		{	try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			if (turnOn)
			{	
				g.setColor(Color.YELLOW);

			}
			else
			{
				g.setColor(Color.BLACK);
			}
			g.fillOval(50+1+x,10+1+x,200-2,50-2);
			g.fillOval(52+1+x,85+1+x,40-2,55-2);
			g.fillOval(210+1+x,85+1+x,40-2,55-2);
			g.fillOval(125+1+x,70+1+x,50-2,100-2);
			turnOn = !turnOn;
		}
	}
	
	public void run()
	{
		while(true)
		{
			repaint(); // since it same class
		}
	}
	
}