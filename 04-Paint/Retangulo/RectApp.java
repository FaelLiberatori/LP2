import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class RectApp {
    public static void main (String[] args) {
        RectAppFrame frame = new RectAppFrame();
        frame.setVisible(true);
    }
}

class RectAppFrame extends JFrame {
    Rect r1, r2, r3, r4;

    RectAppFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("RectApp");
        this.setSize(1280, 720);
        this.r1 = new Rect(-1, 0, 1281, 720, 0, 0, Color.blue, Color.white);
        this.r2 = new Rect(-20, 600, 1280+20*2, 280, 30, 0, new Color(102, 51, 0), Color.green);
        this.r3 = new Rect(900, 120, 300, 300, 20, 35, Color.red, Color.yellow);
        this.r4 = new Rect(50, 525, 100, 50, 5, 80, Color.black, Color.white);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);        
    }
}

public class Rect {
    private int x, y;
    private int w, h;
    private float borderSize, angle;
    private Color backgroundColor, borderColor;

    public Rect (int x, int y, int w, int h, float borderSize, float angle, Color backgroundColor, Color borderColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.borderSize = borderSize;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void setPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPosition () {
        int[] pos = {x, y};
        return pos;
    }

    public void setSize (int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int[] getSize () {
        int[] size = {w, h};
        return size;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public float getBorderSize () {
        return borderSize;
    }

    public void setAngle (float angle) {
        this.angle = angle;
    }

    public float getAngle () {
        return angle;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor () {
        return backgroundColor;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), this.x + this.w/2, this.y + this.h/2);

        g2d.setColor(backgroundColor);
        g2d.fillRect(this.x,this.y, this.w,this.h);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawRect(this.x, this.y, this.w, this.h);
        g2d.dispose();
    }
}

public class Ellipse {
    private int x, y;
    private int w, h;
    private float borderSize, angle;
    private Color backgroundColor, borderColor;

    Ellipse (int x, int y, int w, int h, float borderSize, float angle, Color backgroundColor, Color borderColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.borderSize = borderSize;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void setPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPosition () {
        int[] pos = {x, y};
        return pos;
    }

    public void setSize (int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int[] getSize () {
        int[] size = {w, h};
        return size;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public float getBorderSize () {
        return borderSize;
    }

    public void setAngle (float angle) {
        this.angle = angle;
    }

    public float getAngle () {
        return angle;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor () {
        return backgroundColor;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), this.x + this.w/2, this.y + this.h/2);

        g2d.setColor(backgroundColor);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.dispose();
    }
}