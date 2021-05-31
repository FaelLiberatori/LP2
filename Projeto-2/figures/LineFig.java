package figures;

import java.awt.*;
import java.awt.geom.*;

public class LineFig extends Figure1D { // Já existe uma classe chamada Line no javafx. É bom evitar usar o mesmo nome.

    public LineFig (int x, int y, int length, int stroke, int angle, Color backgroundColor) {
        super(x, y, length, stroke, angle, backgroundColor);
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + length/2, y);
        
        g2d.setStroke(new BasicStroke(stroke*2));
        g2d.setColor(Color.red);
        g2d.draw(new Line2D.Float(x, y, x+length, y));

        g2d.dispose();
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + length/2, y);
        
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(backgroundColor);
        g2d.draw(new Line2D.Float(x, y, x+length, y));

        g2d.dispose();
    }
}