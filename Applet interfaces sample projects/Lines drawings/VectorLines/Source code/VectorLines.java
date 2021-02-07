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
import java.util.Vector;


public class VectorLines extends Applet
{
	
	int index = 0;
	boolean started = false;
	boolean dragged = false;
	Vector<Line> vec = new Vector<Line>();
	
	private class Line{

		public int x1 = 0;
		public int y1 = 0;
		public int x2 = 0;
		public int y2 = 0;
		
		public Line(){}
		
		public Line(int x1, int y1, int x2, int y2)
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public boolean isPoint()
		{
			return ( x1 == x2 && y1 == y2);
		}
	}
	
	private void drawLines(Graphics g)
	{
		for (int i = 0; i<vec.size(); i++)
		{
			g.drawLine(vec.get(i).x1, vec.get(i).y1, vec.get(i).x2, vec.get(i).y2);
		}
	}
	
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
				vec.addElement(new Line(e.getX(),e.getY(),e.getX(),e.getY()));
			}
 			else 
			{
				vec.get(index).x2 = e.getX();
				vec.get(index).y2 = e.getY();
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
				vec.get(index).x2 = e.getX();
				vec.get(index).y2 = e.getY();
				started = dragged = false;
				if (vec.get(index).isPoint())
					vec.remove(vec.size()-1);
				else 
					index++;
			}
		 }
		 
		});
	}
	
	public void paint(Graphics g)
	{
		//g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		
		g.setColor(Color.BLACK);
		drawLines(g);
		
	}

}