package figures;

import java.awt.*;

public class Rect extends Figure {

    public Rect (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(getAngle()), getPosition()[0] + getSize()[0]/2, getPosition()[1] + getSize()[1]/2);

        g2d.setColor(getBackgroundColor());
        g2d.fillRect(getPosition()[0], getPosition()[1], getSize()[0], getSize()[1]);
        
        g2d.setColor(getBorderColor());
        g2d.setStroke(new BasicStroke(getBorderSize()));
        
        g2d.drawRect(getPosition()[0], getPosition()[1], getSize()[0], getSize()[1]);
        g2d.dispose();
    }
}