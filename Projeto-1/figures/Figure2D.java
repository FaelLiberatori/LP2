package figures;

import java.awt.*;

public abstract class Figure2D extends Figure {
    public int w, h;
    public Color borderColor;
    public int borderSize;

    public Figure2D (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, angle, backgroundColor);
        this.w = w;
        this.h = h;
        this.borderSize = borderSize;
        this.borderColor = borderColor;
    }

    @Override
    public boolean isOnPoint (int x, int y) {
        if (x >= this.x && x <= this.x + w &&
        y >= this.y && y <= this.y + h)
            return true;
        else
            return false;
    }

    @Override
    protected void paintSelect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dash[] = {4.0f};
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND,
        10.0f, dash, 0.0f));
        
        g2d.drawRect(x - selectDistance, y - selectDistance, w + selectDistance*2, h + selectDistance*2);
        g2d.dispose();
    }
}