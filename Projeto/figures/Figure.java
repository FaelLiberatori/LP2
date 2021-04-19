package figures;

import java.awt.*;

import javax.swing.Painter;

public abstract class Figure {
    public int x, y;
    public int w, h;
    public int borderSize, angle;
    public Color backgroundColor, borderColor;
    public boolean isSelected;
    protected int selectDistance;

    protected abstract void painter (Graphics g);

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
        this.isSelected = false;
        this.selectDistance = 6;
    }

    public void paint (Graphics g) {
        this.painter(g);

        if (isSelected)
            this.paintSelect(g);
    }

    public boolean isOnPoint (int x, int y) {
        if (x >= this.x && x <= this.x + w &&
        y >= this.y && y <= this.y + h)
            return true;
        else
            return false;
    }

    public void drag (int dx, int dy) {
        x += dx - x;
        y += dy - y;
    }

    private void paintSelect(Graphics g) {
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