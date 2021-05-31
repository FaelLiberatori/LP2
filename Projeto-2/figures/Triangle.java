package figures;

import java.awt.*;

public class Triangle extends Figure2D {
    private Polygon triangle;

    public Triangle (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
        triangle = new Polygon(new int[]{x, x + w/2, x + w}, new int[]{y + h, y, y + h}, 3);
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return triangle.contains(x, y);
        else
            return false;
    }

    @Override
    protected void paintFocus (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        triangle.xpoints = new int[]{x, x + w/2, x + w};
        triangle.ypoints = new int[]{y + h, y, y + h};

        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(borderSize*2));
        
        g2d.drawPolygon(triangle);
        g2d.dispose();
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        triangle.xpoints = new int[]{x, x + w/2, x + w};
        triangle.ypoints = new int[]{y + h, y, y + h};
        triangle.invalidate();

        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(triangle);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(triangle);
        g2d.dispose();
    }
}