import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1;
    Ellipse e1;
    Triangle t1;
    Parallelogram p1;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30, 8, -5, Color.blue, Color.green);
        this.e1 = new Ellipse(50,100, 100,30, 8, 30, Color.white, Color.black);
        this.t1 = new Triangle(new int[] {100, 80, 120}, new int[] {150, 200, 200}, 8, 0, Color.pink, Color.yellow);
        this.p1 = new Parallelogram(new int[] {50, 100, 150, 100}, new int[] {230, 230, 280, 280}, 0, 0, Color.cyan, Color.lightGray);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
        this.t1.paint(g);
        this.p1.paint(g);
    }
}