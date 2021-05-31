package figures;

import java.awt.*;
import java.awt.geom.*;

public abstract class Figure2D extends Figure {

    public Color borderColor;
    protected int w, h;
    protected int borderSize;
    private boolean negativeDragResizeX, negativeDragResizeY, positiveDragResizeX, positiveDragResizeY;


    public Figure2D (int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, angle, backgroundColor);
        this.w = w;
        this.h = h;
        this.borderSize = borderSize;
        this.borderColor = borderColor;
        this.negativeDragResizeX = false;
        this.negativeDragResizeY = false;
    }

    public int getW () {
        return w;
    }

    public void setW (int w) {
        if (w >= 12)
            this.w = w;
        else
            System.out.println("Erro: não é possível diminuir a largura além deste ponto.");
    }

    public int getH () {
        return h;
    }

    public void setH (int h) {
        if (h >= 12)
            this.h = h;
        else
            System.out.println("Erro: não é possível diminuir a altura além deste ponto.");
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        if (borderSize >= 0 && borderSize <= 14)
            this.borderSize = borderSize;
        else
            System.out.println("Erro: não é possível diminuir/aumentar a borda além deste ponto.");
    }

    
    @Override
    public boolean clicked (int x, int y) {
        Ellipse2D circle = new Ellipse2D.Float(this.x,this.y,w,h);
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            //return bodyClicked(x, y);
            return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
        else
            return false;
    }

    @Override
    public boolean borderClicked (int x, int y) {
        if (dragStatus == 2)
            return true;
        else if (dragStatus == 0)
            return !clicked(x, y) && x >= this.x - focusDistance && x <= this.x + this.w + focusDistance*2 &&
            y >= this.y - focusDistance && y <= this.y + this.h + focusDistance*2;
        else
            return false;
    }

    @Override
    public void dragResize (int posX, int posY) {
        if (lastPosX == -100 && lastPosY == -100) {
            lastPosX = posX;
            lastPosY = posY;
            dragStatus = 2;
        }

        int wIncrease = posX - lastPosX;
        int hIncrease = posY - lastPosY;

        if ((positiveDragResizeX || posX >= x + w) && !negativeDragResizeX) {
            setW(w + wIncrease);
            positiveDragResizeX = true;
        }
        else if ((negativeDragResizeX || w + (-wIncrease) >= 12) && !positiveDragResizeX) {
            x += wIncrease;
            setW(w + (-wIncrease));
            negativeDragResizeX = true;
        }
        else
            posX = lastPosX;

        if ((positiveDragResizeY || posY >= y + h) && !negativeDragResizeY) {
            setH(h + hIncrease);
            positiveDragResizeY = true;
        }
        else if ((negativeDragResizeY || h + (-hIncrease) >= 12) && !positiveDragResizeY) {
            y += hIncrease;
            setH(h + (-hIncrease));
            negativeDragResizeY = true;
        }
        else
            posY = lastPosY;

        lastPosX = posX;
        lastPosY = posY;
        dragStatus = 2;
    }

    @Override
    public void released () {
        super.released();
        this.negativeDragResizeX = false;
        this.negativeDragResizeY = false;
        this.positiveDragResizeX = false;
        this.positiveDragResizeY = false;
    }

    @Override
    protected void paintFocusRec (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dash[] = {4.0f};
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND,
        10.0f, dash, 0.0f));
        
        g2d.drawRect(x - super.focusDistance, y - super.focusDistance, w + super.focusDistance*2, h + super.focusDistance*2);
        g2d.dispose();
    }
}