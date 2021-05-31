package figures;

import java.awt.*;
import java.security.cert.PolicyQualifierInfo;

public class Parallelogram extends Figure2D {
    private Polygon parallelogram;

    public Parallelogram (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);        
        parallelogram = new Polygon(new int[]{x, x + w/2, x + w, x + w/2}, new int[]{y, y, y + h , y + h}, 4);
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return parallelogram.contains(x, y);
        else
            return false;
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        parallelogram.xpoints = new int[]{x, x + w/2, x + w, x + w/2};
        parallelogram.ypoints = new int[]{y, y, y + h , y + h};
        
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(borderSize*2));
        
        g2d.drawPolygon(parallelogram);
        g2d.dispose();

    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        parallelogram.xpoints = new int[]{x, x + w/2, x + w, x + w/2};
        parallelogram.ypoints = new int[]{y, y, y + h , y + h};
        parallelogram.invalidate();

        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(parallelogram);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(parallelogram);
        g2d.dispose();
    }
}