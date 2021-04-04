package figures;

import java.awt.*;

public abstract class Figure {
    private int x, y;
    private int w, h;
    private int borderSize, angle;
    private Color backgroundColor, borderColor;

    public abstract void paint (Graphics g);


    // Você pode fazer um override caso necessário
    public Figure (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        setPosition(x, y);
        setSize(w, h);
        setBorderSize(borderSize);
        setAngle(angle);
        setBackgroundColor(backgroundColor);
        setBorderColor(borderColor);
    }

    public int[] getPosition() {
        int[] pos = {x, y};
        return pos;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getSize() {
        int[] size = {w, h};
        return size;
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}