import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class App {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figList = new ArrayList<Figure>();
    Random rand = new Random();
    Figure activeFigure = null;
    Figure select = null;
    int mousePositionX = -100, mousePositionY = -100;

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addMouseMotionListener (
            new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent evt) {
                    mousePositionX = evt.getX();
                    mousePositionY = evt.getY();
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    if (activeFigure != null && select != null) {
                        figList.remove(select);
                        select = null;
                        repaint();
                    }
                    
                    activeFigure = null;

                    for (Figure fig: figList) {
                        if (fig.isOnPoint(mousePositionX, mousePositionY))
                            activeFigure = fig;
                    }

                    if (activeFigure != null) {
                        figList.add(figList.remove(figList.indexOf(activeFigure)));
                        activeFigure = figList.get(figList.size() -1);
                        figList.add(new Select(activeFigure));
                        select = figList.get(figList.size() -1);
                        repaint();
                    }
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = mousePositionX;
                    int y = mousePositionY;
                    int w = 40;
                    int h = 40;
                    int b = 5;
                    int a = 0;

                    int key = evt.getKeyCode();

                    if (key == evt.VK_R) {
                        figList.add(new Rect(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    if (key == evt.VK_E) {
                        figList.add(new Ellipse(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    if (key == evt.VK_T) {
                        figList.add(new Triangle(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    if (key == evt.VK_Y) {
                        figList.add(new Parallelogram(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    if (key == evt.VK_DELETE) {
                        if (activeFigure != null) {
                            figList.remove(figList.size() - 1);
                            figList.remove(activeFigure);
                            activeFigure = null;
                            repaint();
                        }
                    }
                }
            }
        );

        this.setTitle("Projeto Vetorial");
        this.setSize(1024, 768);
    }

    public void paint (Graphics g) {
        Rect background = new Rect(0, 0, getWidth(), getHeight(), 0, 0, Color.white, Color.white);
        background.paint(g);

        for (Figure fig: figList) {
            fig.paint(g);
        }
    }
}