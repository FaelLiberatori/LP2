package figures;

import java.awt.*;

public abstract class Figure {
    public int x, y;
    public int angle;
    public Color backgroundColor;
    public boolean isSelected;
    protected int selectDistance;
    protected int lastPosX, lastPosY;

    protected abstract void painter (Graphics g);
    protected abstract void paintSelect(Graphics g);
    public abstract boolean isOnPoint (int x, int y);

    public Figure (int x, int y, int angle, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.isSelected = false;
        this.selectDistance = 9;
        this.lastPosX = -100;
        this.lastPosY = -100;
    }

    // Você pode fazer um override caso necessário
    
    public void paint (Graphics g) {
        this.painter(g);

        if (isSelected)
            this.paintSelect(g);
    }

    public void drag (int posX, int posY) {
        if (lastPosX != -100 && lastPosY != -100) {
            x += posX - lastPosX;
            y += posY - lastPosY;
        }

        lastPosX = posX;
        lastPosY = posY;
    }

    public void released () {
        // Necessita ser executado quando a evento de "arrastar" (drag) na figura for finalizado.
        lastPosX = -100;
        lastPosY = -100;
    }
}