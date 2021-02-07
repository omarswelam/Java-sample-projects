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
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Panel;
import java.awt.Image;


public class PaintApplet extends Applet
{
	
	int index = 0;
	int undoIndex=0;
	Color color = Color.BLACK;
	boolean fill = false;
	boolean started = false;
	boolean dragged = false;
	boolean cleared = false;
	boolean dashed = false;
	Vector<Shape> vec = new Vector<Shape>();
	Vector<Shape> clearedVec = new Vector<Shape>(); // for undo of clear given that you can only undo one clear action not more
	String shape = "None";
	
	// for double buffer implementation
	private Image imageDrawn;
	private Graphics g2;
	
	private void drawShapes(Graphics g)
	{
		for (int i = 0; i<vec.size(); i++)
		{
			vec.get(i).draw(g);
		} 
	}
	
	public void init()
	{
		
		Panel p = new Panel();
		Button circleBtn = new Button("Circle");
		Button rectBtn = new Button("Rect");
		Button lineBtn = new Button("Line");
		Button freeBtn = new Button("FreeForm");
		Button eraseBtn = new Button("Eraser");
		Button blackBtn = new Button("Black");
		Button greenBtn = new Button("Green");
		Button redBtn = new Button("Red");
		Button blueBtn = new Button("Blue");	
		Button fillBtn = new Button("Solid");
		Button clearAllBtn = new Button("Clear");
		Button undoBtn = new Button("Undo");
		Button dashBtn = new Button("Dotted");
		
		circleBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 shape = "Circle";
		 }
		});
		
		rectBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 shape = "Rectangle";
		 }
		});
		
		lineBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 shape = "Line";
		 }
		});
		
		freeBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 shape = "FreeForm";
		 }
		});
		
		eraseBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 shape = "Eraser";
		 }
		});
		
		blackBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 color = Color.BLACK;
		 }
		});
		
		greenBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 color = Color.GREEN;
		 }
		});
		
		redBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 color = Color.RED;
		 }
		});
		
		blueBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 color = Color.BLUE;
		 }
		});
		
		fillBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 fill = !fill;
		 }
		});
		
		dashBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 dashed = !dashed;
		 }
		});
		
		clearAllBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 if (vec.size()>0)
			 {
				undoIndex = index;
				clearedVec = (Vector<Shape>)vec.clone();
				vec.removeAllElements();
				index = 0;
			 }
		 }
		});
		
		undoBtn.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {
			 if (vec.size()==0)
			 {
				vec = (Vector<Shape>)clearedVec.clone();
				index = undoIndex;
				clearedVec.removeAllElements();
				undoIndex = 0;
			 }
			 else
			 {
				 vec.remove(vec.size()-1);
				 index--;
			 }
		 }
		});
				
		p.add(circleBtn);
		p.add(rectBtn);
		p.add(lineBtn);
		p.add(freeBtn);
		p.add(eraseBtn);
		blackBtn.setForeground(Color.WHITE);
		blackBtn.setBackground(Color.BLACK);
		p.add(blackBtn);
		greenBtn.setBackground(Color.GREEN);
		p.add(greenBtn);
		redBtn.setBackground(Color.RED);
		p.add(redBtn);
		blueBtn.setBackground(Color.BLUE);
		p.add(blueBtn);
		p.add(fillBtn);
		p.add(clearAllBtn);
		p.add(undoBtn);
		p.add(dashBtn);
		p.setBackground(Color.GRAY);
		add(p);
		
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
			 switch(shape){
				case "FreeForm":
					FreeForm.freeDragAction(e, vec, PaintApplet.this);
					break;
				case "Circle":
					Oval.ovalDragAction(e, vec, PaintApplet.this);
					break;
				case "Rectangle":
					Rectangle.recDragAction(e, vec, PaintApplet.this);
					break;
				case "Line":
					Line.lineDragAction(e, vec, PaintApplet.this);
					break;
				case "Eraser":
					Eraser.eraserDragAction(e, vec, PaintApplet.this);
					break;
				default:
					break;
			 }
		 }
		 }
		 );
		 
		this.addMouseListener(new MouseAdapter(){

		 public void mouseReleased(MouseEvent e) 
		 {
			switch(shape){
				case "FreeForm":
					FreeForm.releaseAction(e, vec, PaintApplet.this);
					break;
				case "Circle":
					Oval.releaseAction(e, vec, PaintApplet.this);
					break;
				case "Rectangle":
					Rectangle.releaseAction(e, vec, PaintApplet.this);
					break;
				case "Line":
					Line.releaseAction(e, vec, PaintApplet.this);
					break;
				case "Eraser":
					Eraser.releaseAction(e, vec, PaintApplet.this);
					break;
				default:
					break;		 
			}
		}
		
		public void mouseClicked(MouseEvent e) 
		{
			if (shape.equals("Eraser"))
				Eraser.eraserClickAction(e, vec, PaintApplet.this);
		}
	});
	}	
	
	public void update(Graphics g) {
		
		imageDrawn = createImage(getWidth(), getHeight());
		g2 = imageDrawn.getGraphics();
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(getForeground());
		paint(g2);
		g.drawImage(imageDrawn, 0, 0, null);
	} 
			
	public void paint(Graphics g)
	{		
		
		drawShapes(g);
		
	}	

}