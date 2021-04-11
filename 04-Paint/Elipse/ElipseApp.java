import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class ElipseApp {
    public static void main (String[] args) {
        ElipseAppFrame frame = new ElipseAppFrame();
        frame.setVisible(true);
    }
}

class ElipseAppFrame extends JFrame {
    Ellipse e1, e2, e3, e4;

    ElipseAppFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("EllipseApp");
        this.setSize(1280, 720);
        this.e1 = new Ellipse(-1, 0, 1281, 720, 0, 0, Color.blue, Color.white);
        this.e2 = new Ellipse(-20, 600, 1280+20*2, 280, 30, 0, new Color(102, 51, 0), Color.green);
        this.e3 = new Ellipse(900, 120, 300, 300, 20, 35, Color.red, Color.yellow);
        this.e4 = new Ellipse(50, 580, 100, 50, 5, 80, Color.black, Color.gray);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.e4.paint(g);
    }
}

class Rect {
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

class Ellipse {
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