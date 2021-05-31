package gui;

import figures.*;
import java.awt.*;
import interfaces.*;

public class ButtonGUI implements IVisible { // Já existe uma classe Button no JAVA AWT.
    private int x, y;
    private int w, h;
    private int padding;
    private Figure figure;
    private Rect button;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updateButton();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updateButton();
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
        updateButton();
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
        updateButton();
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        updateButton();
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        updateButton();
    }

    public ButtonGUI (int x, int y, int w, int h, int padding, Figure figure) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.figure = figure;
        this.padding = padding;
        this.button = new Rect(x, y, w, h, 3, 0, Color.lightGray, Color.black);
        updateButton();
    }

    public boolean clicked (int x, int y) {
        return button.clicked(x, y);
    }

    public void paint (Graphics g, int focused) {
        if (focused >= 1)
            button.backgroundColor = Color.darkGray;
        else
            button.backgroundColor = Color.lightGray;

        button.paint(g, 0);
        figure.paint(g, 0);
    }

    private void updateButton () {
        button.x = x;
        button.y = y;
        button.setW(w);
        button.setH(h);
        figure.x = x + padding;
        
        if (figure instanceof Figure2D) {
            figure.y = y + padding;
            ((Figure2D) figure).setH(h - padding*2);
            ((Figure2D) figure).setW(w - padding*2);
        }
        else {
            figure.y = y + padding + h/4;
            if (figure instanceof Arrow)
                ((Figure1D) figure).setLength(h - padding*2 - ((Arrow) figure).arrowPointSize);
            else
                ((Figure1D) figure).setLength(h - padding*2);
        }
    }
}
