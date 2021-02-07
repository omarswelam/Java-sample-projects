import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;

public class BallApplet extends Applet implements Runnable
{
	
	int x1 = 0;
	int y1 = 0;
	int x2 = 100;
	int y2 = 100;
	int ballSize = 20;
	int stepSize = 10;
	int xDirection1 = 1;
	int yDirection1 = 1;
	int xDirection2 = -1;
	int yDirection2 = -1;
	
	// since we can't create an object from the applet we have to include our code to be run in the init
	public void init()
	{
		Thread th = new Thread(this); // we can't create an object from Applet because the applet manager is the one that creates the applets
		// "this" is passed as a runnable where this represents the created instance from this class (which is created via the applet manager)
		th.start();
		// if i called th.run() instead of th.start() the lengthy operation will takeover the main thread and not start the lifecycle correctly
	}
	
	public void updateBall1()
	{
		x1 += (stepSize*xDirection1);
		if (x1 >= getWidth()-ballSize)
		{
			xDirection1=-1;
			x1 = getWidth()-ballSize;
		}
		if (x1 <= 0)
		{
			xDirection1=1;
			x1 = 0;
		}
		
		y1 += (stepSize*yDirection1);
		if (y1 >= getHeight()-ballSize)
		{
			yDirection1=-1;
			y1 = getHeight()-ballSize;
		}
		if (y1 <= 0)
		{
			yDirection1=1;
			y1 = 0;
		}
	}
	
	public void updateBall2()
	{
		x2 += (stepSize*xDirection2);
		if (x2 >= getWidth()-ballSize)
		{
			xDirection2=-1;
			x2 = getWidth()-ballSize;
		}
		if (x2 <= 0)
		{
			xDirection2=1;
			x2 = 0;
		}
		
		y2 += (stepSize*yDirection2);
		if (y2 >= getHeight()-ballSize)
		{
			yDirection2=-1;
			y2 = getHeight()-ballSize;
		}
		if (y2 <= 0)
		{
			yDirection2=1;
			y2 = 0;
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, ballSize,ballSize);
		g.setColor(Color.RED);
		g.fillOval(x2, y2, ballSize,ballSize);
		// in case we inserted this in a while loop this will cause blocking on the browser page that we can't close it
		// so we need to take it out to be in a parallel thread 
		if (x1==x2 && y1==y2)
		{
			x1+= -stepSize*yDirection2;
		}
		
		updateBall1();
		updateBall2();
		
		if ( ( (( ((y1 + ballSize) >= y2) && (y1 <= y2) ) ) || ( ((y2 + ballSize) >= y1) && (y2 <= y1) ) ) &&
			(( ((x1 + ballSize) >= x2) && (x1 <= x2) ) || ( ((x2 + ballSize) >= x1) && (x2 <= x1) ))  )
		{
			if ((yDirection1 != yDirection2) &&  ( x1+ballSize >= x2 ||  x2+ballSize >= x1))
			{	
				yDirection1 *= -1;
				yDirection2 *= -1;
			}
			if ((xDirection1 != xDirection2) &&  ( y1+ballSize >= y2 ||  y2+ballSize >= y1))
			{	
				xDirection1 *= -1;
				xDirection2 *= -1;
			}
		}
		
		
	}
	
	public void run()
	{
		while(true)
		{
			repaint();
			 // since it same class
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		
	}
}