import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import figures.*;
import gui.*;
import io.*;

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
        Binary bin = new Binary();
        ArrayList<Figure> newFigList = bin.loadFromFile("proj.bin");
        if (newFigList != null)
            figList = newFigList;
        newFigList = new ArrayList<Figure>(); // Só para não ocupar memória à toa.

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SVG svg = new SVG();

                svg.saveToFile("proj.svg", figList, getWidth(), getHeight());
                bin.saveToFile("proj.bin", figList);

                System.exit(0);
            }
        });
    }

    class MainPanel extends JPanel {
        Random rand = new Random();
        SideMenu toolBox = new SideMenu(this.getHeight(), 10);
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

            toolBox.addTopButton(new ImageFig("./icons/mouse.png", 0, 0, 0, 0, 2, 0)); // index 0
            toolBox.addTopButton(new ImageFig("./icons/resize.png", 0, 0, 0, 0, 2, 0)); // index 1
            toolBox.addTopButton(new LineFig(0, 0, 0, 2, 0, Color.yellow)); // index 2
            toolBox.addTopButton(new Arrow(0, 0, 0, 2, 0, Color.yellow)); // index 3
            toolBox.addTopButton(new Rect(0, 0, 0, 0, 2, 0, Color.yellow, Color.black)); // index 4
            toolBox.addTopButton(new Ellipse(0, 0, 0, 0, 2, 0, Color.yellow, Color.black)); // index 5
            toolBox.addTopButton(new Parallelogram(0, 0, 0, 0, 2, 0, Color.yellow, Color.black)); // index 6
            toolBox.addTopButton(new Triangle(0, 0, 0, 0, 2, 0, Color.yellow, Color.black)); // index 7

            toolBox.addBottomButton(new ImageFig("./icons/trash.png", 0, 0, 0, 0, 2, 0)); // Index 0
            toolBox.addBottomButton(new ImageFig("./icons/invalid.png", 0, 0, 0, 0, 2, 0)); // Index 0
            toolBox.addBottomButton(new ImageFig("./icons/invalid.png", 0, 0, 0, 0, 2, 0)); // Index 0
            
            this.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    toolBox.setHeight(getHeight());
                    repaint();
                }
    
             });

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
                            else if (toolBox.topButtonClicked(1))
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
    
                        if (toolBox.clicked(evt.getX(), evt.getY())) {
                            // Verifica se clicou nos botões de baixo

                            if (focusedFigure != null) {
                                if (toolBox.bottomButtonClicked(2)) { // Cor de fundo
                                    focusedFigure.backgroundColor = colorList[rand.nextInt(13)];
                                }
                                else if (toolBox.bottomButtonClicked(1) && focusedFigure instanceof Figure2D) { // Cor de contorno
                                    ((Figure2D)focusedFigure).borderColor = colorList[rand.nextInt(13)];
                                }
                                else if (toolBox.bottomButtonClicked(0)) { // Lixeira
                                    figList.remove(focusedFigure);
                                    focusedFigure = null;
                                    toolBox.getBottomButtonFigure(2).backgroundColor = Color.white;
                                    toolBox.getBottomButtonFigure(1).backgroundColor = Color.white;
                                }
                            }
                        }
                        else if (toolBox.topButtonClicked(0) || toolBox.topButtonClicked(1)) {
                            // Verifica se alguma figura foi clicada.

                            Figure lastFocus = focusedFigure;
                            focusedFigure = null;

                            for (Figure fig: figList) {
                                if (fig.clicked(mousePositionX, mousePositionY))
                                    focusedFigure = fig;
                                else if (toolBox.topButtonClicked(1) && fig == lastFocus && fig.borderClicked(mousePositionX, mousePositionY)) {
                                    focusedFigure = fig;
                                    focusedFigure.dragResize(mousePositionX, mousePositionY);
                                }
                            }
        
                            if (focusedFigure != null) {
                                figList.add(figList.remove(figList.indexOf(focusedFigure)));
                                focusedFigure = figList.get(figList.size() -1);
                            }
                        }
                        else {
                            // Verifica se o usuário quer criar um objeto.

                            int x = mousePositionX;
                            int y = mousePositionY;
                            int w = 40;
                            int h = 40;
                            int b = 5;
                            int a = 0;
        
                            if (toolBox.topButtonClicked(4))
                                figList.add(new Rect(x, y, w, h, b, a, Color.yellow, Color.black));
                            else if (toolBox.topButtonClicked(2))
                                figList.add(new LineFig(x, y, 40, b, a, Color.black));
                            else if (toolBox.topButtonClicked(3))
                                figList.add(new Arrow(x, y, 40, b, a, Color.black));
                            else if (toolBox.topButtonClicked(5))
                                figList.add(new Ellipse(x, y, w, h, b, a, Color.yellow, Color.black));
                            else if (toolBox.topButtonClicked(7))
                                figList.add(new Triangle(x, y, w, h, b, a, Color.yellow, Color.black));
                            else if (toolBox.topButtonClicked(6))
                                figList.add(new Parallelogram(x, y, w, h, b, a, Color.yellow, Color.black));

                            focusedFigure = figList.get(figList.size() -1);     
                        }

                        updateMenuColor();
                        repaint();
                    }

                    public void mouseReleased (MouseEvent evt) {
                        toolBox.released();

                        if (focusedFigure != null)
                            focusedFigure.released();

                        repaint();
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
            BufferedImage img_buff = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g_buff = img_buff.createGraphics();
    
            for (Figure fig: figList) {
                int focus = 0;
                if (fig == focusedFigure)
                    focus = toolBox.topButtonClicked(1) ? 2:1;

                fig.paint(g_buff, focus);
            }

            toolBox.paint(g_buff, 0);
            g.drawImage(img_buff, 0, 0, null);
        }

        void updateMenuColor () {
            toolBox.setBottomButtonFigure(1, new Rect(0, 0, 0, 0, 2, 0, Color.white, Color.black));
            toolBox.setBottomButtonFigure(2, new Rect(0, 0, 0, 0, 2, 0, Color.white, Color.black));

            if (focusedFigure != null) {
                toolBox.getBottomButtonFigure(2).backgroundColor = focusedFigure.backgroundColor;

                if (focusedFigure instanceof Figure2D)
                    toolBox.getBottomButtonFigure(1).backgroundColor = ((Figure2D)focusedFigure).borderColor;
                else 
                    toolBox.setBottomButtonFigure(1, new ImageFig("./icons/invalid.png", 0, 0, 0, 0, 2, 0));
            }
            else {
                toolBox.setBottomButtonFigure(1, new ImageFig("./icons/invalid.png", 0, 0, 0, 0, 2, 0));
                toolBox.setBottomButtonFigure(2, new ImageFig("./icons/invalid.png", 0, 0, 0, 0, 2, 0));
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
