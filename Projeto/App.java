import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class App extends JFrame {

    App() {
        super("Projeto Vetorial");
        MainPanel panel = new MainPanel();
        panel.setFocusable(true);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
        this.setVisible(true);
    }

    class MainPanel extends JPanel {
        ArrayList<Figure> figList = new ArrayList<Figure>();
        Random rand = new Random();
        Figure activeFigure = null;
        int mousePositionX = -100, mousePositionY = -100;

        MainPanel() {
            setPreferredSize(new Dimension(1024, 720));
            setBackground(Color.WHITE);
            setDoubleBuffered(true);

            this.addMouseMotionListener (
                new MouseMotionAdapter() {
                    public void mouseMoved (MouseEvent evt) {
                        mousePositionX = evt.getX();
                        mousePositionY = evt.getY();
                    }
    
                    public void mouseDragged (MouseEvent evt) {
                        if (activeFigure != null) {
                            activeFigure.drag(evt.getX(), evt.getY());
                            repaint();
                        }
                    }
                }
            );

            this.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        if (activeFigure != null) {
                            activeFigure.isSelected = false;
                            activeFigure = null;
                        }
    
                        for (Figure fig: figList) {
                            if (fig.isOnPoint(mousePositionX, mousePositionY))
                                activeFigure = fig;
                        }
    
                        if (activeFigure != null) {
                            figList.add(figList.remove(figList.indexOf(activeFigure)));
                            activeFigure = figList.get(figList.size() -1);
                            activeFigure.isSelected = true;
                        }
                        
                        repaint();
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
                            figList.add(new Rect(x, y, w, h, b, a, Color.yellow, Color.black));
                            repaint();
                        }
                        if (key == evt.VK_E) {
                            figList.add(new Ellipse(x, y, w, h, b, a, Color.yellow, Color.black));
                            repaint();
                        }
                        if (key == evt.VK_T) {
                            figList.add(new Triangle(x, y, w, h, b, a, Color.yellow, Color.black));
                            repaint();
                        }
                        if (key == evt.VK_Y) {
                            figList.add(new Parallelogram(x, y, w, h, b, a, Color.yellow, Color.black));
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
        }

        @Override
        public void paintComponent (Graphics g) {
            super.paintComponent(g);
    
            for (Figure fig: figList) {
                fig.paint(g);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
            }
        });
    }
}