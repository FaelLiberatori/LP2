package figures;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w, y + y/2);

        g2d.setColor(backgroundColor);
        g2d.fill(new Ellipse2D.Float(x, y, w, h));
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.draw(new Ellipse2D.Float(x, y, w, h));
        g2d.dispose();
    }
}