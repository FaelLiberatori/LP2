package figures;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class ImageFig extends Figure2D { // Já existe um classe chamada image no Java AWT

    private BufferedImage image;

    public void setImageURL(String imageURL) {
        try {
            image = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            System.out.println("Error: " + imageURL + " not found! " + e);
        }
    }

    public ImageFig (String imageURL, int x, int y, int w, int h, int borderSize, int angle, Color backgroundColor, Color borderColor) {
        super(x, y, w, h, borderSize, angle, backgroundColor, borderColor);
        setImageURL(imageURL);
        
    }
    public ImageFig (String imageURL, int x, int y, int w, int h, int borderSize, int angle) {
        super(x, y, w, h, borderSize, angle, new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        setImageURL(imageURL);
    }

    @Override
    public void painter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angle), x + w/2, y + h/2);
        g2d.drawImage(image, x, y, w, h, backgroundColor, null);
        g2d.setColor(borderColor);
        g2d.drawRect(x, y, w, h);
        g2d.dispose();
    }
}
