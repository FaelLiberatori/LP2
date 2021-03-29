package figures;

import java.awt.*;

public class Parallelogram {
    private int[] XPoints, YPoints;
    private float borderSize, angle;
    private Color backgroundColor, borderColor;

    public Parallelogram (int[] XPoints, int[] YPoints, float borderSize, float angle, Color backgroundColor, Color borderColor) {
        this.XPoints = XPoints;
        this.YPoints = YPoints;
        this.borderSize = borderSize;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void setPositionX (int[] XPoints) {
        this.XPoints = XPoints;
    }

    public int[] getPositionX () {
        return XPoints;
    }

    public void setPositionY (int[] XPoints) {
        this.YPoints = YPoints;
    }

    public int[] getPositionY () {
        return YPoints;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public float getBorderSize () {
        return borderSize;
    }

    public void setAngle (float angle) {
        this.angle = angle;
    }

    public float getAngle () {
        return angle;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor () {
        return backgroundColor;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), (this.XPoints[1] + this.XPoints[2])/2,(this.YPoints[0] + this.YPoints[1])/2);

        // Só para garantir que não existam mais de 4 pontos.
        int[] x = {XPoints[0], XPoints[1], XPoints[2], XPoints[3]};
        int[] y = {YPoints[0], YPoints[1], YPoints[2], YPoints[3]};

        g2d.setColor(backgroundColor);
        g2d.fillPolygon(x, y, 4);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderSize));
        
        g2d.drawPolygon(XPoints, YPoints, 3);
        g2d.dispose();
    }
}
