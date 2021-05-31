package figures;

import java.awt.*;

public abstract class Figure1D extends Figure {

    protected int stroke, length;
    private boolean positiveDragResize, negativeDragResize;

    public Figure1D (int x, int y, int length, int stroke, int angle, Color backgroundColor) {
        super(x, y, angle, backgroundColor);
        this.stroke = stroke;
        this.length = length;
    }


    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        if (stroke >= 1 && stroke <= 14)
            this.stroke = stroke;
        else
            System.out.println("Erro: não é possível diminuir/aumentar o contorno além deste ponto.");
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length >= 3)
            this.length = length;
        else
            System.out.println("Erro: não é possível diminuir o comprimento além deste ponto.");
    }

    @Override
    public boolean clicked (int x, int y) {
        if (dragStatus == 1)
            return true;
        else if (dragStatus == 0)
            return x >= this.x && x <= this.x + length && y >= this.y - stroke*2 && y <= this.y + stroke*2;
        else
            return false;
    }

    @Override
    public boolean borderClicked (int x, int y) {
        if (dragStatus == 2)
            return true;
        else if (dragStatus == 0)
            return (x >= this.x - focusDistance && x < this.x || x <= this.x + this.length + focusDistance*2 && x > this.x) &&
            (y >= this.y - focusDistance && y < this.y || y <= this.y + focusDistance*2 && y > this.y);
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

        if ((positiveDragResize || posX >= x + length/2) && !negativeDragResize) {
            setLength(length + wIncrease);
            positiveDragResize = true;
        }
        else if ((negativeDragResize || length + (-wIncrease) >= 12) && !positiveDragResize) {
            x += wIncrease;
            setLength(length + (-wIncrease));
            negativeDragResize = true;
        }
        else
            posX = lastPosX;

        lastPosX = posX;
        lastPosY = posY;
        dragStatus = 2;
    }

    @Override
    public void released () {
        super.released();
        this.positiveDragResize = false;
        this.negativeDragResize = false;
    }

    @Override
    protected void paintFocusRec (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dash[] = {4.0f};
        g2d.rotate(Math.toRadians(angle), x + length/2, y + stroke/2);
        
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND,
        10.0f, dash, 0.0f));
        
        g2d.drawRect(x - super.focusDistance, y - (super.focusDistance + stroke), length + super.focusDistance*2, stroke*2 + super.focusDistance*2);
        g2d.dispose();
    }
}