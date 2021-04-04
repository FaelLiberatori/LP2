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
        g2d.rotate(Math.toRadians(getAngle()), getPosition()[0] + getSize()[0]/2, getPosition()[1] + getSize()[1]/2);

        g2d.setColor(getBackgroundColor());
        g2d.fill(new Ellipse2D.Float(getPosition()[0],getPosition()[1], getSize()[0],getSize()[1]));
        
        g2d.setColor(getBorderColor());
        g2d.setStroke(new BasicStroke(getBorderSize()));
        
        g2d.draw(new Ellipse2D.Float(getPosition()[0],getPosition()[1], getSize()[0],getSize()[1]));
        g2d.dispose();
    }
}