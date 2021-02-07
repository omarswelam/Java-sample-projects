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


public class DragBall extends Applet
{
	int ballSize = 60;
	int x1 = ballSize/2;
	int y1 = ballSize/2;
		
	public void init()
	{
		
		this.addMouseMotionListener(new MouseAdapter(){
	
		 public void mouseDragged(MouseEvent e) 
		 {
			x1 = e.getX();
			y1 = e.getY();
			repaint();
		 }
		 }
		 );
		 
/* 		 this.addMouseListener(new MouseAdapter(){

		 public void mousePressed(MouseEvent e) 
		 {
			x1 = e.getX();
			y1 = e.getY();
			repaint();
		 }
		}); */
	}
	
	
	public void paint(Graphics g)
	{
		
		g.setColor(Color.RED);
		g.fillOval(x1-ballSize/2, y1-ballSize/2, ballSize,ballSize);
		
	}
	
}