package figures;

import java.awt.*;

public class Triangle extends Figure {

    // Somente faz triângulos equiláteros.
    public Triangle (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int[] x = {getPosition()[0], getPosition()[0]+getSize()[0]/2, getPosition()[0]+getSize()[0]};
        int[] y = {getPosition()[1]+getSize()[1], getPosition()[1], getPosition()[1]+getSize()[1]};

        g2d.rotate(Math.toRadians(getAngle()), (x[1] + x[2])/2,(y[0] + y[1])/2);

        g2d.setColor(getBackgroundColor());
        g2d.fillPolygon(x, y, 3);
        
        g2d.setColor(getBorderColor());
        g2d.setStroke(new BasicStroke(getBorderSize()));
        
        g2d.drawPolygon(x, y, 3);
        g2d.dispose();
    }
}