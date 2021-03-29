package figures;

import java.awt.*;

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