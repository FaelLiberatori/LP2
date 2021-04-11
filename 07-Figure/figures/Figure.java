package figures;

import java.awt.*;

public abstract class Figure {
    public int x, y;
    public int w, h;
    public int borderSize, angle;
    public Color backgroundColor, borderColor;

    public abstract void paint (Graphics g);


    // Você pode fazer um override caso necessário
    public Figure (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.borderSize = borderSize;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
}