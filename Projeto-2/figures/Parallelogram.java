package figures;

import java.awt.*;

public class Parallelogram extends Figure2D {

    public Parallelogram (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int[] xAux = {x, x + w/2, x + w, x + w/2};
        int[] yAux = {y, y, y + h , y + h};

        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(xAux, yAux, 4);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(xAux, yAux, 4);
        g2d.dispose();
    }
}