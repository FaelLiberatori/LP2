public class TextoApp {
    public static void main (String[] args) {
        Texto t1 = new Texto(10,10,22,"Arial","Texto grande",false,false,true,false);
        t1.print();
    }
}

class Texto {
    int x, y, size;
    String font, text;
    boolean italic, bold, underline, strike;

    Texto (int x, int y, int size, String font, String text, boolean italic, boolean bold, boolean underline, boolean strike) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.font = font;
        this.text = text;
        this.italic = italic;
        this.bold = bold;
        this.underline = underline;
        this.strike = strike;
    }
    void print () {
        System.out.format("Texto de fonte %s, de tamanho %d na posicao (%d,%d) com texto \"%s\"",
            this.font, this.size, this.x, this.y, this.text);

        if(italic)
            System.out.format(" e em itálico.\n");
        if(bold)
            System.out.format(" e em negrito.\n");
        if(underline)
            System.out.format(" e sublinhado.\n");
        if(strike)
            System.out.format(" e riscado.\n");
    }
}