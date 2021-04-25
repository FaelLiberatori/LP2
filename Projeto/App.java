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
        int lastClickX = -100,  lastClickY = -100;
        Color colorList[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
                            Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
                            Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

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
                    public void mousePressed (MouseEvent evt) {
                        lastClickX = evt.getX();
                        lastClickY = evt.getY();

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

                    public void mouseReleased (MouseEvent evt) {
                        if (activeFigure != null)
                            activeFigure.released();
                    }
                }
            );
    
            this.addMouseWheelListener (
                new MouseWheelListener() {
                    public void mouseWheelMoved(MouseWheelEvent evt) {
                        int scroll = evt.getUnitsToScroll();

                        if (activeFigure != null) {
                            if ((evt.getModifiersEx() & evt.CTRL_DOWN_MASK) != 0) {
                                if (activeFigure instanceof Figure2D) {
                                    if (((Figure2D) activeFigure).borderSize + scroll >= 0
                                    && ((Figure2D) activeFigure).borderSize + scroll <= 14)
                                        ((Figure2D) activeFigure).borderSize += scroll;
                                }
                                else {
                                    if (((Figure1D) activeFigure).stroke + scroll >= 1
                                    && ((Figure1D) activeFigure).stroke + scroll <= 14)
                                        ((Figure1D) activeFigure).stroke += scroll;
                                }

                            }
                            else {
                                if (activeFigure instanceof Figure2D) {
                                    if (((Figure2D) activeFigure).w + scroll > 4
                                    && ((Figure2D) activeFigure).h + scroll > 4) {
                                        if ((evt.getModifiersEx() & evt.SHIFT_DOWN_MASK) != 0)
                                            ((Figure2D) activeFigure).h += scroll;
                                        else if ((evt.getModifiersEx() & evt.ALT_DOWN_MASK) != 0)
                                            ((Figure2D) activeFigure).w += scroll;
                                        else {
                                            ((Figure2D) activeFigure).w += scroll;
                                            ((Figure2D) activeFigure).h += scroll;
                                        }
                                    }
                                }
                                else {
                                    if (((Figure1D) activeFigure).length + scroll > 4)
                                        ((Figure1D) activeFigure).length += scroll;
                                }
                            }

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
    
                        if (key == evt.VK_R)
                            figList.add(new Rect(x, y, w, h, b, a, Color.yellow, Color.black));
                        else if (key == evt.VK_Q)
                            figList.add(new LineFig(x, y, 40, b, a, Color.black));
                        else if (key == evt.VK_W)
                            figList.add(new Arrow(x, y, 40, b, a, Color.black));
                        else if (key == evt.VK_E)
                            figList.add(new Ellipse(x, y, w, h, b, a, Color.yellow, Color.black));
                        else if (key == evt.VK_T)
                            figList.add(new Triangle(x, y, w, h, b, a, Color.yellow, Color.black));
                        else if (key == evt.VK_Y)
                            figList.add(new Parallelogram(x, y, w, h, b, a, Color.yellow, Color.black));
                        else if (activeFigure != null) {
                            if (key == evt.VK_C) {
                                if ((evt.getModifiersEx() & evt.CTRL_DOWN_MASK) != 0 && 
                                    activeFigure instanceof Figure2D)
                                    ((Figure2D) activeFigure).borderColor = colorList[rand.nextInt(13)];
                                else
                                    activeFigure.backgroundColor = colorList[rand.nextInt(13)];
                                
                                repaint();
                            }
                            else if (key == evt.VK_S) {
                                if ((evt.getModifiersEx() & evt.SHIFT_DOWN_MASK) != 0)
                                    activeFigure.angle -= 5;
                                else
                                    activeFigure.angle += 5;

                                repaint();
                            }
                            else if (key == evt.VK_DELETE) {
                                figList.remove(activeFigure);
                                activeFigure = null;
                                repaint();
                            }
                        }

                        if (key == evt.VK_Q || key == evt.VK_W || key == evt.VK_R 
                        || key == evt.VK_E || key == evt.VK_T || key == evt.VK_Y) {
                            if (activeFigure != null)
                                activeFigure.isSelected = false;
                            
                            activeFigure = figList.get(figList.size() -1);
                            activeFigure.isSelected = true;
                            repaint();
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