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
    ArrayList<Rect> rs = new ArrayList<Rect>();
    ArrayList<Ellipse> es = new ArrayList<Ellipse>();
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
                        rs.add(new Rect(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                    else if (evt.getKeyChar() == 'e') {
                        es.add(new Ellipse(x, y, w, h, b, a, Color.white, Color.black));
                        repaint();
                    }
                }
            }
        );

        this.setTitle("Lista de Retangulos & Elipses");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Rect r: this.rs) {
            r.paint(g);
        }
        for (Ellipse e: this.es) {
            e.paint(g);
        }
    }
}