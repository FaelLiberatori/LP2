package figures;

import java.awt.*;

public class Select extends Rect {
    
    public Select (Figure fig) {
        super(fig.x - 6, fig.y - 6, fig.w + 6*2, fig.h + 6*2, 2, fig.angle, new Color(1, 1, 1, 1), Color.red);
    }

    @Override
    public boolean isOnPoint (int x, int y) {
        return false;
    }

}