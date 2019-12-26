import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class ClassTable {
	ClassObj classObj;
	//boolean isParent=false;
	public Point pos;
	private double width;
	private double height;
	public Rectangle2D Table_Class;
	public boolean hv=false;
	public void setHv(boolean hv) {
		this.hv=hv;
	}
	public ClassTable(Point point, ClassObj classObj) {
		this.pos= point;
		this.classObj= classObj;
	}
	public ClassObj getClasObj() {
		return classObj;
	}
	public boolean containsMouse(MouseEvent mouseEvent) {
        return Table_Class.contains(mouseEvent.getX()-7 , mouseEvent.getY() -32);
    }
	
	public void Move( int moveX, int moveY) {
		double x=pos.getX()+moveX;
		double y=pos.getY()+moveY;
		pos.setX(x);
		pos.setY(y);
		
	}
	
	private Double[] getSizeofRect(Graphics g, String s, Font font) {
		return new Double[] {Size.getWidth(g, classObj.ClassName, font),Size.getHeight(g, font)};
	}
	
	
	private Double[] getSizeofRect(Graphics g, List <String> list,Font font) {
		Double maxWidth= 0.0;
		Double height=0.0;
		for(String s: list) {
			height+=Size.getHeight(g, font);
			maxWidth= Math.max(maxWidth, Size.getWidth(g, s+"   ", font));
		}
		return new Double[] {maxWidth+20,height};
	}
	public int getWidth() {
		return (int)width;
	}
	public int getHeight() {
		return (int) height;
	}
	public void drawRect(Graphics g) {
		Graphics2D graphic=(Graphics2D) g;
		Font font = new Font("TimesRoman",Font.BOLD|Font.ITALIC,13);
        g.setFont(font);
		Double[] ClassNameSize=getSizeofRect(g, classObj.ClassName, font);
		Double[] FieldSize=getSizeofRect(g, classObj.stringFields, font);
		Double[] MethodSize=getSizeofRect(g, classObj.stringMethods, font);
		Double maxWidth= Math.max(ClassNameSize[0], Math.max(FieldSize[0], MethodSize[0]));
		
	    Rectangle2D ClassName_Rect= new Rectangle2D.Double(pos.getX(), pos.getY(), maxWidth, ClassNameSize[1]);
		Rectangle2D Field_Rect= new Rectangle2D.Double(pos.getX(),pos.getY()+ClassNameSize[1],maxWidth, FieldSize[1]);
		Rectangle2D Method_Rect= new Rectangle2D.Double(pos.getX(), pos.getY()+ClassNameSize[1]+FieldSize[1], maxWidth, MethodSize[1]);
		
		width=maxWidth;
		height=ClassNameSize[1]+FieldSize[1]+MethodSize[1];
		Table_Class = new Rectangle2D.Double(pos.getX(), pos.getY(), width, height);
		
		graphic.setColor(new Color(204,204,204));
		graphic.fill(Table_Class);
		graphic.setColor(Color.BLACK);
		graphic.draw(ClassName_Rect);
		graphic.draw(Field_Rect);
		graphic.draw(Method_Rect);
		graphic.draw(Table_Class);
		g.drawString(classObj.ClassName, (int)(pos.getX() + (maxWidth-Size.getWidth(g,classObj.ClassName,font))/2), (int)pos.getY()+13);
		int count=1;
		for(String s: classObj.stringFields) {
			g.drawString(s, (int)Field_Rect.getX()+5, (int)(Field_Rect.getY()+(FieldSize[1]/classObj.stringFields.size())*count-5));
			count++;
			if(count>classObj.stringFields.size()) {
				count=1;
			}
		}
		for(String s: classObj.stringMethods) {
			g.drawString(s, (int)Method_Rect.getX()+5, (int)(Method_Rect.getY()+(MethodSize[1]/classObj.stringMethods.size())*count-5));
			count++;
			if(count>classObj.stringMethods.size()) {
				count=1;
			}
		}
		g.setFont(font.deriveFont(Font.ROMAN_BASELINE));
		//System.out.println(classObj.ClassExtend);
	}
}
