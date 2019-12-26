
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public abstract class Size {
    public static double getWidth(Graphics g, String s, Font font) {
        FontMetrics fm = g.getFontMetrics(font);
        return fm.stringWidth(s);
    }

    public static double getHeight(Graphics g, Font font) {
        FontMetrics fm = g.getFontMetrics(font);
        return fm.getHeight();
    }
}

