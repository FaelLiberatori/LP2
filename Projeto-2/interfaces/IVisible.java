package interfaces;

import java.awt.Graphics;

public interface IVisible {
    public boolean clicked (int x, int y);
    public void paint (Graphics g, int focused); // 0 = none; 1 = normal; 2 = rec;
}