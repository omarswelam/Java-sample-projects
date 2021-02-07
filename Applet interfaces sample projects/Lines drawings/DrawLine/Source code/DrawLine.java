import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.Font;


public class DrawLine extends Applet
{
	
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	
	
	boolean started = false;
	
	public void init()
	{
		new Thread(){ 

			public void run()
			{
				while(true)
				{
					repaint();
					try{
						Thread.sleep(100);
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
				}	
			}
		}.start();
		
		this.addMouseMotionListener(new MouseAdapter(){
	
		 public void mouseDragged(MouseEvent e) 
		 {
			if (!started)
			{
				started = true;
				x1 = e.getX();
				y1 = e.getY();
				
			}
 			else 
			{	
				x2 = e.getX();
				y2 = e.getY();
			}
		 }
		 }
		 );
		this.addMouseListener(new MouseAdapter(){

		 public void mouseReleased(MouseEvent e) 
		 {
			x2 = e.getX();
			y2 = e.getY();
			started = false;
		 }
		});
	 

	}
	
	
	public void paint(Graphics g)
	{
		//g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2,y2);
		
	}
	
	/* class ThreadingBall extends Thread{ 

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
	} */
}