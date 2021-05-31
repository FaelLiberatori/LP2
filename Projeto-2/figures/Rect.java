package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect extends Figure2D {
    private Rectangle2D rectangle;

    public Rect (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
        rectangle = new Rectangle2D.Float(x, y, w, h);
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return rectangle.contains(x, y);
        else
            return false;
    }
    
    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        rectangle.setRect(x, y, w, h);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(borderSize*2));
        
        g2d.draw(rectangle);
        g2d.dispose();
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        rectangle.setRect(x, y, w, h);

        g2d.setColor(backgroundColor);
        g2d.fill(rectangle);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.draw(rectangle);
        g2d.dispose();
    }
}