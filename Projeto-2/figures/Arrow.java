package figures;

import java.awt.*;
import java.awt.geom.*;

public class Arrow extends Figure1D {
    public int arrowPointSize;

    public Arrow (int x, int y, int length, int stroke, int angle, Color backgroundColor) {
        super(x, y, length, stroke, angle, backgroundColor);
        this.arrowPointSize = 6;
    }

    @Override
    public boolean borderClicked (int x, int y) {
        if (dragStatus == 2)
            return true;
        else if (dragStatus == 0)
            return (x >= this.x - focusDistance && x < this.x || x <= this.x + this.length + focusDistance + arrowPointSize && x > this.x) &&
            (y >= this.y - focusDistance && y < this.y || y <= this.y + focusDistance && y > this.y);
        else
            return false;
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dash[] = {4.0f};
        g2d.rotate(Math.toRadians(angle), x + length/2, y + stroke/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND,
        10.0f, dash, 0.0f));
        
        g2d.drawRect(x - focusDistance, y - (focusDistance + stroke), length + focusDistance*2 + arrowPointSize+6, stroke*2 + focusDistance*2);
        g2d.dispose();
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + length/2, y);

        int[] xArrow = {x+length, x+length, x+length+arrowPointSize};
        int[] yArrow = {y-arrowPointSize/2, y+arrowPointSize/2, y};
        
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(backgroundColor);
        g2d.draw(new Line2D.Float(x, y, x+length, y));
        g2d.drawPolygon(xArrow, yArrow, 3);

        g2d.dispose();
    }
}