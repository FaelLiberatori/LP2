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
    public int getLength() {
        return length + arrowPointSize;
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return (x >= this.x && x <= this.x + getLength() && y >= this.y-arrowPointSize && y <= this.y+arrowPointSize);
        else
            return false;
    }

    @Override
    public boolean borderClicked (int x, int y) {
        if (dragStatus == 2)
            return true;
        else if (dragStatus == 0)
            return (x >= this.x - focusDistance && x < this.x || x <= this.x + getLength() + 6 + focusDistance*2 && x > this.x) &&
            (y >= this.y - focusDistance && y < this.y || y <= this.y + focusDistance*2 && y > this.y);
        else
            return false;
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + length/2, y);

        int[] xArrow = {x+length, x+length, x+length+arrowPointSize};
        int[] yArrow = {y-arrowPointSize/2, y+arrowPointSize/2, y};
        
        g2d.setStroke(new BasicStroke(stroke*2));
        g2d.setColor(Color.red);
        g2d.draw(new Line2D.Float(x, y, x+length, y));
        g2d.drawPolygon(xArrow, yArrow, 3);

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