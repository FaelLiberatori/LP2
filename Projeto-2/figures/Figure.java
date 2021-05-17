package figures;

import java.awt.*;
import java.io.Serializable;

import interfaces.IVisible;

public abstract class Figure implements IVisible, Serializable {
    public int x, y;
    public int angle;
    public Color backgroundColor;
    protected static int focusDistance;
    protected int lastPosX, lastPosY;

    protected abstract void painter (Graphics g);
    protected abstract void paintFocus (Graphics g);
    public abstract boolean borderClicked (int x, int y);

    public Figure (int x, int y, int angle, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.backgroundColor = backgroundColor;
        this.focusDistance = 9;
        this.lastPosX = -100;
        this.lastPosY = -100;
    }

    public void paint (Graphics g, boolean focused) {
        this.painter(g);
        if (focused) { this.paintFocus(g); }
    }

    public void dragMove (int posX, int posY) {
        if (lastPosX != -100 && lastPosY != -100) {
            x += posX - lastPosX;
            y += posY - lastPosY;
        }

        lastPosX = posX;
        lastPosY = posY;
    }

    public void dragResize (int posX, int posY) {
        // Em desenvolvimento, ainda não funciona.
        if (lastPosX != -100 && lastPosY != -100) {
            w += posX - lastPosX;
            h += posY - lastPosY;
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