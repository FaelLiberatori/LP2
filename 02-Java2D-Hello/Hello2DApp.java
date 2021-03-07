import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main(String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setTitle("Java2D - Hello World!");
        this.setSize(650, 450);
        this.getContentPane().setBackground(Color.white);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Obtêm a dimensão da janela. 
        int w = getWidth();
        int h = getHeight();

        // Fundo degradê / Céu
        g2d.setPaint(new GradientPaint(0, 0, Color.cyan, 0, h / 1.5f, Color.white));
        g2d.fillRect(0, 0, w, h);

        // Arco circular / Sol
        g2d.setPaint(Color.yellow);
        g2d.fillArc((int) (w / 1.3), 50, (int) (w * 0.2f), (int) (w * 0.2f), 0, 360);


        // Retângulo / Grama
        g2d.setPaint(Color.green);
        g2d.fillRect(0, (int) (h / 1.3f), w, h);

        // Retângulo / Arvore
        float dash1[] = { 10.0f };
        g2d.setPaint(new Color(102, 51, 0));
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, dash1, 0.0f)); // Arredondar o retângulo
        g2d.fillRoundRect((int) (w * 0.10f), (int) (h / 2f), (int) (w * 0.05f + 50), (int) (h * 0.40f), 30, 30);

        // Arco elipse / Arvore
        int treePos = (int) (w * 0.10f - (h / 2.5f * 1.2f) / 3);
        g2d.setPaint(new Color(0, 102, 0));
        g2d.fillArc(treePos, (int) (h / 5f), (int) (h / 2.5f * 1.2f), (int) (h / 2.5f), 0, 360);

        // Texto / Hello, World!
        g2d.setPaint(Color.red);
        g2d.setFont(g2d.getFont().deriveFont(w/20.0f));
        g2d.drawString("Hello, World!", (int) (w/2.7f), (int) (h/3));
    }
}