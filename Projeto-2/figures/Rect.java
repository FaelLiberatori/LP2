package figures;

import java.awt.*;

public class Rect extends Figure2D {

    public Rect (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);

        g2d.setColor(backgroundColor);
        g2d.fillRect(x, y, w, h);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawRect(x, y, w, h);
        g2d.dispose();
    }
}