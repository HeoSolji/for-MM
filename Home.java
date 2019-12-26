import java.util.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Home {
	public static void saveComponent(Component c, String filename) {
	    Dimension d = c.getPreferredSize();
	    BufferedImage bi = new BufferedImage((int) d.getWidth(), (int) d.getHeight(),
	            BufferedImage.TYPE_INT_RGB);

	    Graphics2D g2 = bi.createGraphics();
	    //g2.setClip(0, 0, (int) d.getWidth(), (int) d.getHeight());
	    c.paint(g2);
	    try {
	        ImageIO.write(bi,"jpg", new File(filename));
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	public static List<Classs> listClasses= new ArrayList<Classs>();
	public static List<ClassObj> classObjs= new ArrayList<ClassObj>();
	private static Point random() {
		Point point = new Point(0, 0);
		Random random= new Random();
		point.setX(random.nextInt(600));
		point.setY(random.nextInt(500));
		return point;
		
	}
	
	public static void main(String[] args) throws IOException {
		ReadFile a= new ReadFile();
		listClasses= a.getListClass();
		a.getNameClass();
		a.Show();
		Home home= new Home();
		
		for(Classs e: listClasses) {
			ClassObj co= new ClassObj(e.getName(), e.getStringFields(), e.getStringMethods(),e.getName_Class_extend(),e.isExtend(),e.getClassName(),e.getImplement(),e.getName_Class_Implement());
			classObjs.add(co);
		}
		
		Layer layer= new Layer(listClasses);
		JFrame frame = new JFrame("JAVA_PROGRAM");
		
		JButton buttonSaveImage= new JButton("SAVE IMAGE");
		buttonSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==buttonSaveImage) {
					saveComponent(layer,"test.jpg");
				}
				
			}
		});
		
		for(ClassObj c: classObjs) {
			ClassTable table1= new ClassTable(random(), c);
			layer.addTable(table1);
		}
		layer.add(buttonSaveImage);
        frame.add(layer);
        frame.addMouseListener(layer);
        frame.addMouseMotionListener(layer);

        frame.setSize(1000, 1000);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
