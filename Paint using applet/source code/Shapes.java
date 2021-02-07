import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

abstract class Shape{
	
	class Pt {
		int x;
		int y;
		
		public Pt(int x1, int y1){
			x = x1;
			y = y1;
		}
		
		public Pt(){}
	}
	
	public Pt startPt;
	public Pt endPt;
	protected Color color;
	protected boolean fill;
	protected boolean dashed;
	
	// for the implementation of FreeForm
	protected Vector<Line> lineVec;
	protected int iLine = 0;
	
	// for implementation of erasers
	protected Vector<Rectangle> eraserVec ;
	
	
	public static void dragAction(MouseEvent e, Vector<Shape> vec, PaintApplet a)
	{
		vec.get(a.index).endPt.x = e.getX();
		vec.get(a.index).endPt.y = e.getY();	
	}
	
	public static void releaseAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if ( a.dragged )
			{	
				vec.get(a.index).endPt.x = e.getX();
				vec.get(a.index).endPt.y = e.getY();
				a.started = a.dragged = false;
				if ( (vec.get(a.index).startPt.x == vec.get(a.index).endPt.x) && (vec.get(a.index).startPt.y == vec.get(a.index).endPt.y) )
					vec.remove(vec.size()-1);
				else 
					a.index++;
			}
			
	}
	
	abstract public void draw(Graphics g);
		
}

class Oval extends Shape{
	
	private Graphics2D g;
	
	public Oval(int x1, int y1, int x2, int y2, Color color, boolean fill, boolean dash)
	{
		startPt = new Pt(x1, y1);
		endPt = new Pt(x2, y2);	
		this.color = color;
		this.fill = fill;
		this.dashed = dash; ///
	}
	
	public void draw(Graphics g1){
		//////////////////////////////
		g = (Graphics2D) g1;
		if (dashed)
		{
			float[] dashPattern = {7f, 7f};
 
			g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,
									BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));
		}
		else
		{
			g.setStroke(new BasicStroke(3f));
		}
		///////////////////
		g.setColor(this.color);
		if (fill)
		{
			int width = endPt.x-startPt.x;
			int height = endPt.y-startPt.y;
			if (width<0 && height <0)
				g.fillOval(endPt.x, endPt.y, -width, -height);
			else if (width<0 && height>0)
				g.fillOval(endPt.x, startPt.y, -width, height);
			else if (width>0 && height<0)
				g.fillOval(startPt.x, endPt.y, width, -height);
			else
				g.fillOval(startPt.x, startPt.y, width, height);
		}
		else
		{
			int width = endPt.x-startPt.x;
			int height = endPt.y-startPt.y;
			if (width<0 && height <0)
				g.drawOval(endPt.x, endPt.y, -width, -height);
			else if (width<0 && height>0)
				g.drawOval(endPt.x, startPt.y, -width, height);
			else if (width>0 && height<0)
				g.drawOval(startPt.x, endPt.y, width, -height);
			else
				g.drawOval(startPt.x, startPt.y, width, height);
		}
	}
	
	public static void ovalDragAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if (!a.started)
			{
				a.started = true;
				vec.addElement(new Oval(e.getX(),e.getY(),e.getX(),e.getY(), a.color, a.fill, a.dashed));
			}
 			else
			{
				dragAction(e, vec, a);
			}
			
			a.dragged = true;
	}
}


class Line extends Shape{

	private Graphics2D g;
	
	public Line(int x1, int y1, int x2, int y2, Color color, boolean dash)
	{
		startPt = new Pt(x1, y1);
		endPt = new Pt(x2, y2);	
		this.color = color;
		this.dashed = dash;
	}
	
	public void draw(Graphics g1){
		
		g = (Graphics2D) g1;
		if (dashed)
		{
			float[] dashPattern = {7f, 7f};
 
			g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,
									BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));
		}
		else
		{
			g.setStroke(new BasicStroke(3f));
		}
		
		g.setColor(this.color);
		g.drawLine(startPt.x, startPt.y, endPt.x, endPt.y);
	}
	
	public static void lineDragAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if (!a.started)
			{
				a.started = true;
				vec.addElement(new Line(e.getX(),e.getY(),e.getX(),e.getY(), a.color, a.dashed));
			}
 			else
			{
				dragAction(e, vec, a);
			}
			
			a.dragged = true;
	}
	

}


class Rectangle extends Shape{
	
	public Rectangle(){}
	private Graphics2D g;
	public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean fill, boolean dash)
	{
		startPt = new Pt(x1, y1);
		endPt = new Pt(x2, y2);	
		this.color = color;
		this.fill = fill;
		this.dashed = dash;
	}
	
	public void draw(Graphics g1){
		
		g = (Graphics2D) g1;
		if (dashed)
		{
			float[] dashPattern = {7f, 7f};
 
			g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,
									BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));
		}
		else
		{
			g.setStroke(new BasicStroke(3f));
		}
		
		g.setColor(this.color);
		if (fill)
		{
			int width = endPt.x-startPt.x;
			int height = endPt.y-startPt.y;
			if (width<0 && height <0)
				g.fillRect(endPt.x, endPt.y, -width, -height);
			else if (width<0 && height>0)
				g.fillRect(endPt.x, startPt.y, -width, height);
			else if (width>0 && height<0)
				g.fillRect(startPt.x, endPt.y, width, -height);
			else
				g.fillRect(startPt.x, startPt.y, width, height);
		}
		else
		{
			int width = endPt.x-startPt.x;
			int height = endPt.y-startPt.y;
			if (width<0 && height <0)
				g.drawRect(endPt.x, endPt.y, -width, -height);
			else if (width<0 && height>0)
				g.drawRect(endPt.x, startPt.y, -width, height);
			else if (width>0 && height<0)
				g.drawRect(startPt.x, endPt.y, width, -height);
			else
				g.drawRect(startPt.x, startPt.y, width, height);
		}
	}
	
	public static void recDragAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if (!a.started)
			{
				a.started = true;
				vec.addElement(new Rectangle(e.getX(),e.getY(),e.getX(),e.getY(), a.color, a.fill, a.dashed));
			}
 			else
			{
				dragAction(e, vec, a);
			}
			
			a.dragged = true;
	}
}

class FreeForm extends Shape{
	
	{
		lineVec = new Vector<Line>();
	}
	public FreeForm(int x1, int y1, Color color, boolean dash)
	{
		startPt = new Pt(x1, y1);
		endPt = new Pt(x1, y1);	
		this.color = color;
		this.dashed = dash;
	}
	
	public void draw(Graphics g){
		
		for (int i = 0; i<lineVec.size(); i++)
		{
			lineVec.get(i).draw(g);
		}
	}
	
	public static void freeDragAction(MouseEvent e, Vector<Shape> v, PaintApplet a){
		
		if (!a.started)
			{
				a.started = true;
				v.addElement(new FreeForm(e.getX(), e.getY(), a.color, a.dashed));
				v.get(a.index).lineVec.addElement(new Line(e.getX(),e.getY(),e.getX(),e.getY(), a.color, a.dashed));
			}
 			else
			{
				v.get(a.index).lineVec.get(v.get(a.index).iLine).endPt.x = e.getX();
				v.get(a.index).lineVec.get(v.get(a.index).iLine).endPt.y = e.getY();
				
				if ( (Math.abs(v.get(a.index).lineVec.get(v.get(a.index).iLine).startPt.x - e.getX()) + Math.abs(v.get(a.index).lineVec.get(v.get(a.index).iLine).startPt.y - e.getY())) > 15 )
				{
					v.get(a.index).lineVec.addElement(new Line(v.get(a.index).lineVec.get(v.get(a.index).iLine).endPt.x, v.get(a.index).lineVec.get(v.get(a.index).iLine).endPt.y ,e.getX(),e.getY(), a.color, a.dashed));
					v.get(a.index).iLine++;
				}
				
			}
			
			a.dragged = true;
	}
	
	public static void releaseAction(MouseEvent e, Vector<Shape> outerVec, PaintApplet a){
		
		if ( a.dragged )
			{	
				outerVec.get(a.index).endPt.x = e.getX();
				outerVec.get(a.index).endPt.y = e.getY();
				a.started = false;
				a.dragged = false;
				a.index++;
			}
			
	}
	
} 



class Eraser extends Shape{
	
	public static int size = 20;
	
	{eraserVec = new Vector<Rectangle> ();}
	
	public void draw(Graphics g){
		for (int i = 0; i<eraserVec.size(); i++)
			eraserVec.get(i).draw(g);

	}
	
	public static void eraserDragAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if (!a.started)
		{
			vec.addElement(new Eraser());
			vec.get(a.index).eraserVec.addElement(new Rectangle(e.getX()-size/2,e.getY()-size/2, e.getX()+size/2,e.getY()+size/2, Color.WHITE,true, false));
			a.started = true;
		}
		else
		{
			vec.get(a.index).eraserVec.addElement(new Rectangle(e.getX()-size/2,e.getY()-size/2, e.getX()+size/2,e.getY()+size/2, Color.WHITE,true, false));
		}
		a.dragged = true;
		
	}

	public static void releaseAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		if ( a.dragged )
			{	
				a.started = a.dragged = false;
				a.index++;
			}
			
	}
	
	
	public static void eraserClickAction(MouseEvent e, Vector<Shape> vec, PaintApplet a){
		
		vec.addElement(new Rectangle(e.getX()-size/2,e.getY()-size/2, e.getX()+size/2,e.getY()+size/2, Color.WHITE,true, false));
		a.index++;
		
	}
	
}

