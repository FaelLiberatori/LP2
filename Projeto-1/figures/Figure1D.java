package figures;

import java.awt.*;

public abstract class Figure1D extends Figure {

    public int stroke, length;

    public Figure1D (int x, int y, int length, int stroke, int angle, Color backgroundColor) {
        super(x, y, angle, backgroundColor);
        this.stroke = stroke;
        this.length = length;
    }

    @Override
    public boolean isOnPoint (int x, int y) {
        if (x >= this.x && x <= this.x + length &&
        y >= this.y - stroke && y <= this.y + stroke)
            return true;
        else
            return false;
    }

    @Override
    protected void paintSelect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dash[] = {4.0f};
        g2d.rotate(Math.toRadians(angle), x + length/2, y + stroke/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND,
        10.0f, dash, 0.0f));
        
        g2d.drawRect(x - selectDistance, y - (selectDistance + stroke), length + selectDistance*2, stroke*2 + selectDistance*2);
        g2d.dispose();
    }
}