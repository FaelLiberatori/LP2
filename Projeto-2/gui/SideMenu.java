package gui;

import java.util.*;
import java.awt.*;

import figures.*;
import gui.*;
import interfaces.*;

public class SideMenu implements IVisible {
    private int padding;
    private int height;
    private int topFocusedIndex, bottomFocusedIndex;
    private float scale;
    private ArrayList<ButtonGUI> topButtonList, bottomButtonList;
    private static int buttonSize, borderSize;
    private Rect panel;

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        updateButtons();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        panel.setH(height - padding/3);
        updateButtons();
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        panel.setW((int)(scale*(buttonSize + padding*2)));
        updateButtons();
    }

    
    public SideMenu (int height, int padding) {
        this.height = height;
        this.padding = padding;
        this.topFocusedIndex = 0;
        this.bottomFocusedIndex = -1;
        this.borderSize = 3;  // borda padrão.
        this.buttonSize = 35; // tamanho padrão.
        this.scale = 1;       // escala padrão,
        this.panel = new Rect(borderSize/2, borderSize/2, (int)(scale*(buttonSize + padding*2)), height - padding/3, borderSize, 0, Color.gray, Color.black);
        this.topButtonList = new ArrayList<ButtonGUI>();
        this.bottomButtonList = new ArrayList<ButtonGUI>();
    }

    public void addTopButton (Figure figure) {
        topButtonList.add(new ButtonGUI(panel.x + padding, (int)(panel.y + 18 + borderSize*2 + topButtonList.size() * buttonSize*1.2 * scale),
        (int)(buttonSize * scale), (int)(buttonSize * scale), padding, figure));
    }
    
    public void addBottomButton (Figure figure) {
        bottomButtonList.add(new ButtonGUI(panel.x + padding, (int)(height - borderSize*2 - (bottomButtonList.size()+1) * buttonSize*1.2 * scale),
        (int)(buttonSize * scale), (int)(buttonSize * scale), padding, figure));
    }

    public void setTopButtonFigure (int index, Figure figure) {
        topButtonList.get(index).setFigure(figure);
    }

    public void setBottomButtonFigure (int index, Figure figure) {
        bottomButtonList.get(index).setFigure(figure);
    }

    public Figure getTopButtonFigure (int index) {
        return topButtonList.get(index).getFigure();
    }

    public Figure getBottomButtonFigure (int index) {
        return bottomButtonList.get(index).getFigure();
    }

    public void removeTopButton (int index) {
        topButtonList.remove(index);
        updateButtons();
    }

    public void removeBottomButton (int index) {
        bottomButtonList.remove(index);
        updateButtons();
    }

    public boolean clicked (int x, int y) {
        int i = 0; // Para não precisar ficar chamando o get(index) toda hora.
        for (ButtonGUI button: topButtonList) {
            if (button.clicked(x, y)) {
                topFocusedIndex = i;
                break;
            }

            i++;
        }

        i = 0;
        for (ButtonGUI button: bottomButtonList) {
            if (button.clicked(x, y)) {
                bottomFocusedIndex = i;
                break;
            }

            i++;
        }

        return panel.clicked(x, y);
    }

    public boolean topButtonClicked (int index) {
        return index == topFocusedIndex;
    }

    public boolean bottomButtonClicked (int index) {
        return index == bottomFocusedIndex;
    }

    public void released () {
        // Precisa ser chamado quando o mouse for solto para que a animação do botões de baixo funcione corretamente.
        bottomFocusedIndex = -1;
    }

    public void paint (Graphics g, int focused) {
        panel.paint(g, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.white);
        g2d.drawString("Menu", panel.x + panel.getW()/4-2, panel.y+borderSize+13);

        int i = 0; // Para não precisar ficar chamando o get(index) toda hora.
        for (ButtonGUI button: topButtonList) {
            button.paint(g, i == topFocusedIndex ? 1:0);
            i++;
        }

        i = 0;
        for (ButtonGUI button: bottomButtonList) {
            button.paint(g, i == bottomFocusedIndex ? 1:0);
            i++;
        }
    }

    private void updateButtons () {
        int size = topButtonList.size();
        for (int i = 0; i < size; i++) {
            // Substituir o botão ao invés de atualizar cada propriedade individualmente otimiza o código, pois,
            // internante na classe ButtonGUI, o método updateButton é chamado apenas uma vez.
            topButtonList.set(i, new ButtonGUI(panel.x + padding, (int)(panel.y + 18 + borderSize*2 + i * buttonSize*1.2 * scale),
            (int)(buttonSize * scale), (int)(buttonSize * scale), padding, topButtonList.get(i).getFigure()));
        }

        size = bottomButtonList.size();
        for (int i = 0; i < size; i++) {
            bottomButtonList.set(i, new ButtonGUI(panel.x + padding, (int)(height - borderSize*2 - (i+1) * buttonSize*1.2 * scale),
            (int)(buttonSize * scale), (int)(buttonSize * scale), padding, bottomButtonList.get(i).getFigure()));
        }
    }
}
