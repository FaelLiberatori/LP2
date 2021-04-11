package figures;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse {
    public int x, y;
    public int w, h;
    public float borderSize, angle;
    public Color backgroundColor, borderColor;

    public Ellipse (int x, int y, int w, int h, float borderSize, float angle, Color backgroundColor, Color borderColor) {
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