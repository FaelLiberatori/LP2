package figures;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends Figure2D {
    private Ellipse2D ellipse;

    public Ellipse (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
        ellipse = new Ellipse2D.Float(x, y, w, h);
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return ellipse.contains(x, y);
        else
            return false;
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        ellipse.setFrame(x, y, w, h);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(borderSize*2));
        g2d.draw(ellipse);

        g2d.dispose();
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        ellipse.setFrame(x, y, w, h);

        g2d.setColor(backgroundColor);
        g2d.fill(ellipse);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.draw(ellipse);
        g2d.dispose();
    }
}