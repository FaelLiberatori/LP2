package gui;

import java.util.*;
import java.awt.*;

import figures.*;
import gui.*;
import interfaces.*;

public class SideMenu implements IVisible {
    private int padding;
    private int height;
    private float scale;
    private ArrayList<ButtonGUI> buttonList;
    private ButtonGUI focusedButton;
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
        panel.setH(height - borderSize);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        panel.setW((int)(scale * (45 - borderSize)));
        updateButtons();
    }

    
    public SideMenu (int height, int padding) {
        this.height = height;
        this.padding = padding;
        this.focusedButton = null;
        this.borderSize = 3;  // borda padrão.
        this.buttonSize = 35; // tamanho padrão.
        this.scale = 1;       // escala padrão,
        this.panel = new Rect(borderSize, borderSize, height - borderSize, (int)(scale * (45 - borderSize)), borderSize, 0, Color.gray, Color.black);
        this.buttonList = new ArrayList<ButtonGUI>();
    }

    public void addButton (Figure figure) {
        int index = 0;
        if (buttonList.size() > 0)
            index = buttonList.size() - 1;

        buttonList.add(new ButtonGUI(panel.x + padding, (int)(panel.y + borderSize + index * buttonSize * scale),
        (int)(buttonSize * scale), (int)(buttonSize * scale), padding, figure));
    }

    public void removeButton (int index) {
        buttonList.remove(index);
        updateButtons();
    }

    public boolean clicked (int x, int y) {
        for (ButtonGUI button: buttonList) {
            if (button.clicked(x, y)) {
                focusedButton = button;
                break;
            }
        }

        return panel.clicked(x, y);
    }

    public boolean buttonClicked (int index) {
        if (buttonList.size() > index)
            return buttonList.get(index) == focusedButton;
        else
            return false;
    }

    public void paint (Graphics g, boolean focused) {
        panel.paint(g, false);

        for (ButtonGUI button: buttonList) {
            button.paint(g, button == focusedButton);
        }
    }

    private void updateButtons () {
        for (int i = 0; i < buttonList.size(); i++) {
            // Substituir o botão ao invés de atualizar cada propriedade individualmente otimiza o código, pois,
            // internante na classe ButtonGUI, o método updateButton é chamado apenas uma vez.
            buttonList.set(i, new ButtonGUI(panel.x + padding, (int)(panel.y + borderSize + i * buttonSize * scale),
            (int)(buttonSize * scale), (int)(buttonSize * scale), padding, buttonList.get(i).getFigure()));
        }
    }
}
