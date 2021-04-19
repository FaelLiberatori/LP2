package figures;

import java.awt.*;

public class Triangle extends Figure {

    public Triangle (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int[] xAux = {x, x + w/2, x + w};
        int[] yAux = {y + h, y, y + h};

        g2d.rotate(Math.toRadians(angle), (xAux[1] + xAux[2])/2,(yAux[0] + yAux[1])/2);

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(xAux, yAux, 3);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(xAux, yAux, 3);
        g2d.dispose();
    }
}