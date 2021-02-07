import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BallStartPause extends Applet
{
	
	int x1 = 0;
	int y1 = 0;
	int ballSize = 20;
	int stepSize = 10;
	int xDirection1 = 1;
	int yDirection1 = 1;
	
	Button btnInc;
	Button btnDec;
	boolean started = false;
	
	public void init()
	{
		ThreadingBall th = new ThreadingBall();
 		btnInc = new Button("Start");
		btnDec = new Button("Pause");
		
		
		btnInc.addActionListener(new ActionListener(){
	
		 public void actionPerformed(ActionEvent e) // because by default it's public abstract
		 {
			if (!started)
			{
				started = true;
				th.start();
			}
 			else 
				th.resume(); 
			
		 }
		});
		
		add(btnInc);
		
		
		btnDec.addActionListener(new ActionListener(){
	
		 public void actionPerformed(ActionEvent e) // because by default it's public abstract
		 {
			 th.suspend();
		 }
		}
		);
	
		add(btnDec);  

	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, ballSize,ballSize);
	}
	
	class ThreadingBall extends Thread{ 
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

		public void run()
		{
			while(true)
			{
				updateBall1();
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
}