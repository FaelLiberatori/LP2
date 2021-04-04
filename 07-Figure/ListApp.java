import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figList = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    int b = rand.nextInt(15);
                    int a = rand.nextInt(360);

                    if (evt.getKeyChar() == 'r') {
                        figList.add(new Rect(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    else if (evt.getKeyChar() == 'e') {
                        figList.add(new Ellipse(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    else if (evt.getKeyChar() == 't') {
                        figList.add(new Triangle(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    else if (evt.getKeyChar() == 'y') {
                        figList.add(new Parallelogram(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: figList) {
            fig.paint(g);
        }
    }
}