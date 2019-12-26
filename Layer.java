import javax.swing.*;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Layer extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
	private Point mousePos= new Point(0.0, 0.0);
	public boolean p=false;
    private static List<ClassTable> tables = new ArrayList<>();
    private List<Classs> classses= new ArrayList<Classs>();
    
    Layer(List<Classs> classses){
    	this.classses=classses;
    }
    Timer t = new Timer(1, this);
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(204,204,204));
        if(tables.isEmpty()==false) {
        	Graphics2D graphics2d=(Graphics2D) g;
        	for(ClassTable table: tables) {
        		for(ClassTable table1: tables) {
        			for(int i=0; i<table1.classObj.vectorImplement.size();i++) {
        				String s1= table.classObj.name;
        				String s2=table1.classObj.vectorImplement.get(i);
        				if(s1.equals(s2)==true) {
        					Polygon poly = new Polygon(
        			                new int[]{(int)(table.pos.getX()+table.getWidth()/2), (int)(table.pos.getX()+table.getWidth()/2+10), (int)(table.pos.getX()+table.getWidth()/2-10)},
        			                new int[]{(int) (table.pos.getY()+table.getHeight()),(int) (table.pos.getY()+table.getHeight()+10), (int) (table.pos.getY()+table.getHeight()+10)},
        			                3);
        					graphics2d.setColor(Color.RED);
        					graphics2d.fill(poly);
        				}
        			}
        			if(table.classObj.name.equals(table1.classObj.ClassExtend)==true) {
        				Polygon poly = new Polygon(
    			                new int[]{(int)(table.pos.getX()+table.getWidth()/2), (int)(table.pos.getX()+table.getWidth()/2+10), (int)(table.pos.getX()+table.getWidth()/2-10)},
    			                new int[]{(int) (table.pos.getY()+table.getHeight()),(int) (table.pos.getY()+table.getHeight()+10), (int) (table.pos.getY()+table.getHeight()+10)},
    			                3);
    					graphics2d.setColor(Color.RED);
    					graphics2d.fill(poly);
        			}
        		}
        	}
        	for(ClassTable table: tables) {
        		if(table.classObj.isExtend==true) {
        			for(ClassTable table1: tables) {
        				if(table.classObj.ClassExtend.equals(table1.classObj.name)==true) {
        					graphics2d.drawLine((int) (table.pos.getX() + table.getWidth() / 2), (int) table.pos.getY(), (int) (table1.pos.getX() + table1.getWidth() / 2), (int) (table1.pos.getY() + table1.getHeight() + 10));
        				}
        			}
        		}
        	}
        	for(ClassTable table: tables) {
        		if(table.classObj.isImplement==true) {
        			for(ClassTable table1: tables) {
        				for(String s: table.classObj.vectorImplement) {
        					if(s.equals(table1.classObj.name)==true) {
        						float[] dash3 = {4f, 0f, 2f};
                              BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                                      BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
                              graphics2d.setStroke(bs3);
                              graphics2d.drawLine((int) (table.pos.getX() + table.getWidth() / 2), (int) table.pos.getY(), (int) (table1.pos.getX() + table1.getWidth() / 2), (int) (table1.pos.getY() + table1.getHeight() + 10));
                              BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                                    BasicStroke.JOIN_BEVEL);
                            graphics2d.setStroke(bs1);
        					}
        				}
        			}
        		}
        	}
            tables.forEach(table -> table.drawRect(g));
        }

        t.start();
    }
    private int mouseX = -1, mouseY = -1, draggingTable = -1;
    public void addTable(ClassTable table) {
        tables.add(table);
    }
    public void actionPerformed (ActionEvent e) {
    	repaint();
    }
    public void mouseClicked (MouseEvent e) {
    	
    }
    public void mousePressed (MouseEvent e) {
    	if (mouseX == -1) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        if (draggingTable == -1)
            for (int i = tables.size() - 1; i >= 0; i--)
                if (tables.get(i).containsMouse(e)) {
                    draggingTable = i;
                    break;
                }
    }
    public void mouseReleased (MouseEvent e) {
    	mouseX = -1;
        mouseY = -1;
        draggingTable = -1;
    }
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mouseDragged (MouseEvent e) {
    	 if (draggingTable != -1) {
             tables.get(draggingTable).Move(e.getX() - mouseX, e.getY() - mouseY);
             mouseX = e.getX();
             mouseY =e.getY();
         }
    }
    public void mouseMoved (MouseEvent e) {
    	for( ClassTable t: tables)
        {
            if(t.containsMouse(e)) t.setHv(true);
            else t.setHv(false);
        }
    }
}
