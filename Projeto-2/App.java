import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import figures.*;
import gui.*;

public class App extends JFrame {
    ArrayList<Figure> figList = new ArrayList<Figure>();

    App() {
        super("Projeto Vetorial");
        MainPanel panel = new MainPanel();
        panel.setFocusable(true);
        this.add(panel);
        this.setLocationByPlatform(true);
        this.pack();
        this.setVisible(true);

        /*
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figList = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("Não foi encontrado nenhum projeto salvo.\nError: proj.bin not found! " + x);
        } */

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream f = new FileOutputStream("proj.bin");
                    ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(figList);
                    o.flush();
                    o.close();
                } catch (Exception x) {
                    System.out.println("Não foi possível salvar o projeto atual.\nError: " + x);

                }
                System.exit(0);
            }
        });
    }

    class MainPanel extends JPanel {
        Random rand = new Random();
        SideMenu sideMenu = new SideMenu(this.getSize().height, 10);
        Figure focusedFigure = null;
        int mousePositionX = -100, mousePositionY = -100;
        int lastClickX = -100,  lastClickY = -100;
        Color colorList[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
                            Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
                            Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

        MainPanel() {
            setPreferredSize(new Dimension(1024, 720));
            setBackground(Color.WHITE);
            setDoubleBuffered(true);

            sideMenu.addButton(new LineFig(0, 0, 0, 2, 0, Color.yellow));
            sideMenu.addButton(new Arrow(0, 0, 0, 2, 0, Color.yellow));
            sideMenu.addButton(new Rect(0, 0, 0, 0, 2, 0, Color.yellow, Color.black));
            sideMenu.addButton(new Ellipse(0, 0, 0, 0, 2, 0, Color.yellow, Color.black));
            sideMenu.addButton(new Parallelogram(0, 0, 0, 0, 2, 0, Color.yellow, Color.black));
            sideMenu.addButton(new Triangle(0, 0, 0, 0, 2, 0, Color.yellow, Color.black));

            this.addMouseMotionListener (
                new MouseMotionAdapter() {
                    public void mouseMoved (MouseEvent evt) {
                        mousePositionX = evt.getX();
                        mousePositionY = evt.getY();
                    }
    
                    public void mouseDragged (MouseEvent evt) {
                        if (focusedFigure != null) {
                            if (focusedFigure.clicked(evt.getX(), evt.getY()))
                                focusedFigure.dragMove(evt.getX(), evt.getY());
                            else
                                focusedFigure.dragResize(evt.getX(), evt.getY());

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
                        Figure lastFocus = focusedFigure;
                        focusedFigure = null;
    
                        for (Figure fig: figList) {
                            if (fig.clicked(mousePositionX, mousePositionY) ||
                            (fig == lastFocus && fig.borderClicked(mousePositionX, mousePositionY))) {
                                focusedFigure = fig;
                                break;
                            }
                        }
    
                        if (focusedFigure != null) {
                            figList.add(figList.remove(figList.indexOf(focusedFigure)));
                            focusedFigure = figList.get(figList.size() -1);
                        }
                        
                        repaint();
                    }

                    public void mouseReleased (MouseEvent evt) {
                        if (focusedFigure != null)
                            focusedFigure.released();
                    }
                }
            );
    
            this.addMouseWheelListener (
                new MouseWheelListener() {
                    public void mouseWheelMoved(MouseWheelEvent evt) {
                        int scroll = evt.getUnitsToScroll();

                        if (focusedFigure != null) {
                            if ((evt.getModifiersEx() & evt.CTRL_DOWN_MASK) != 0) {
                                if (focusedFigure instanceof Figure2D) {
                                    ((Figure2D) focusedFigure).setBorderSize(((Figure2D) focusedFigure).getBorderSize() + scroll);
                                }
                                else {
                                    ((Figure1D) focusedFigure).setStroke(((Figure1D) focusedFigure).getStroke() + scroll);
                                }

                            }
                            else {
                                if (focusedFigure instanceof Figure2D) {
                                    if ((evt.getModifiersEx() & evt.SHIFT_DOWN_MASK) != 0)
                                        ((Figure2D) focusedFigure).setH(((Figure2D) focusedFigure).getH() + scroll);
                                    else if ((evt.getModifiersEx() & evt.ALT_DOWN_MASK) != 0)
                                        ((Figure2D) focusedFigure).setW(((Figure2D) focusedFigure).getW() + scroll);
                                    else {
                                        ((Figure2D) focusedFigure).setH(((Figure2D) focusedFigure).getH() + scroll);
                                        ((Figure2D) focusedFigure).setW(((Figure2D) focusedFigure).getW() + scroll);
                                    }
                                }
                                else {
                                    ((Figure1D) focusedFigure).setLength(((Figure1D) focusedFigure).getLength() + scroll);
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
                        else if (key == evt.VK_TAB)
                            figList.add(figList.remove(0));
                        else if (focusedFigure != null) {
                            if (key == evt.VK_C) {
                                if ((evt.getModifiersEx() & evt.CTRL_DOWN_MASK) != 0 && 
                                    focusedFigure instanceof Figure2D)
                                    ((Figure2D) focusedFigure).borderColor = colorList[rand.nextInt(13)];
                                else
                                    focusedFigure.backgroundColor = colorList[rand.nextInt(13)];
                                
                                repaint();
                            }
                            else if (key == evt.VK_S) {
                                if ((evt.getModifiersEx() & evt.SHIFT_DOWN_MASK) != 0)
                                    focusedFigure.angle -= 5;
                                else
                                    focusedFigure.angle += 5;

                                repaint();
                            }
                            else if (key == evt.VK_DELETE) {
                                figList.remove(focusedFigure);
                                focusedFigure = null;
                                repaint();
                            }
                        }

                        if (key == evt.VK_Q || key == evt.VK_W || key == evt.VK_R 
                        || key == evt.VK_E || key == evt.VK_T || key == evt.VK_Y || key == evt.VK_TAB) {
                            focusedFigure = figList.get(figList.size() -1);
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
                fig.paint(g, fig == focusedFigure);
            }

            sideMenu.paint(g, false);
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