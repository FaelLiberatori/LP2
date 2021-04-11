package figures;

import java.awt.*;

public class Triangle {
    public int[] XPoints, YPoints;
    public float borderSize, angle;
    public Color backgroundColor, borderColor;

    public Triangle (int[] XPoints, int[] YPoints, float borderSize, float angle, Color backgroundColor, Color borderColor) {
        this.XPoints = XPoints;
        this.YPoints = YPoints;
        this.borderSize = borderSize;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), (this.XPoints[1] + this.XPoints[2])/2,(this.YPoints[0] + this.YPoints[1])/2);

        // Só para garantir que não existam mais de 3 pontos.
        int[] x = {XPoints[0], XPoints[1], XPoints[2]};
        int[] y = {YPoints[0], YPoints[1], YPoints[2]};

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(x, y, 3);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(XPoints, YPoints, 3);
        g2d.dispose();
    }
}
