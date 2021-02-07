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


public class DrawMultLines extends Applet
{
	
	int index = 0;
	boolean started = false;
	boolean dragged = false;
	
	public class Line{
		public int x1 = 0;
		public int y1 = 0;
		public int x2 = 0;
		public int y2 = 0;
	}
	
	Line[] arr = new Line[3];
	{arr[0] = new Line();
	arr[1] = new Line();
	arr[2] = new Line();} // because this acts as a method similar to the case of the static method 
	//Line[] arr = {new Line(), new Line(), new Line()};
	
	//the following block causes error because you can't define or assign values within the class unless it's inside a method
	/* arr[0] = new Line();
	arr[1] = new Line();
	arr[2] = new Line(); */
	
	
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
				arr[index].x1 = e.getX();
				arr[index].y1 = e.getY();
				
			}
 			else 
			{
				arr[index].x2 = e.getX();
				arr[index].y2 = e.getY();
			}
			dragged = true;
		 }
		 }
		 );
		this.addMouseListener(new MouseAdapter(){

		 public void mouseReleased(MouseEvent e) 
		 {
			
			if ( dragged )
			{	
				arr[index].x2 = e.getX();
				arr[index].y2 = e.getY();
				started = dragged = false;
				
				if(index <2 )
					index++;
				else 
					index = 0;
				
			}
		 }
		});
	 

	}
	
	
	public void paint(Graphics g)
	{
		//g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		
		g.setColor(Color.BLACK);
		g.drawLine(arr[0].x1, arr[0].y1, arr[0].x2, arr[0].y2);
		g.drawLine(arr[1].x1, arr[1].y1, arr[1].x2, arr[1].y2);
		g.drawLine(arr[2].x1, arr[2].y1, arr[2].x2, arr[2].y2);
		
	}
	//(Math.abs(arr[index].x2-arr[index].x1)+Math.abs(arr[index].y2-arr[index].y1)) >= 10)
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