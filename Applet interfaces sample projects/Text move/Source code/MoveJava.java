import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;

public class MoveJava extends Applet{
	
	private String s = "JAVA";
	private int counter=0;
	private int size;
	private int height;
	private int x = 100;
	private int y = 100;
	
	public void init()
	{
 		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				
				switch (e.getKeyCode()){
					case KeyEvent.VK_UP:
						y-=5;
						break;
					case KeyEvent.VK_LEFT:
						x-=5;
						break;
					case KeyEvent.VK_DOWN:
						y+=5;
						break;
					case KeyEvent.VK_RIGHT:
						x+=5;
						break;
				}
				repaint();
			}				
		});
		
/* 		new Thread(){ 

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
		}.start();  */
	}
	
	public void paint(Graphics g)
	{
		g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		size = g.getFontMetrics().stringWidth(s);
		height = g.getFontMetrics().getHeight();
		g.drawString(s, x, y);

 		if (x >= getWidth()- size)
		{
			if(x >= getWidth())
				x=0;
			else
				g.drawString(s, x-getWidth(), y);
			
		}
		if (y >= getHeight())
		{
			if(y >= getHeight())
				y=0;

			
		}
 		if (x <= 0)
		{
			if(x <=  -size)
				x=getWidth();
			else
				g.drawString(s, getWidth()+x, y);
			
		}
		if (y <= 0)
		{
			if(y <= -height)
				y=getHeight();
			
		}		
	}

	
}
