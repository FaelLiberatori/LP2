public class Retangulo {
    int x, y;
    int w, h;

    Retangulo (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    int area () {
        return w * h;
    }

    void drag (int dx, int dy) {
        x += dx;
        y += dy;
    }
}
