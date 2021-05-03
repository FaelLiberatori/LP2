#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    int angle;
    int isSelected;
    Color* backgroundColor;

    void (* print) (struct Figure*);
    void (* isOnPoint) (struct Figure*, int x, int y);
    void (* drag) (struct Figure*, int x, int y);

} Figure;

Color* color_new (int r, int g, int b) {
    Color* this = malloc(sizeof(Color));

    this->r = r;
    this->g = g;
    this->b = b;

    return this;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h, borderSize;
    Color* borderColor;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d), na posicao (%d,%d), com angulo de %d°, na cor de fundo (%d,%d,%d), com borda de tamanho %d e cor(%d,%d,%d).\n",
           this->w, this->h, sup->x, sup->y, sup->angle, sup->backgroundColor->r, sup->backgroundColor->g, sup->backgroundColor->b, this->borderSize, this->borderColor->r, this->borderColor->g, this->borderColor->b);
    if (sup->isSelected)
        printf("Atualmente, a figura se encontra selecionada.\n\n");
    else
        printf("Atualmente, a figura NÃO se encontra selecionada.\n\n");
}

Rect* rect_new (int x, int y, int w, int h, int angle, Color* backgroundColor, int isSelected, Color* borderColor, int BorderSize) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;

    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    sup->angle = angle;
    sup->backgroundColor = backgroundColor;
    sup->isSelected = isSelected;

    this->w = w;
    this->h = h;
    this->borderColor = borderColor;
    this->borderSize = BorderSize;

    return this;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int stroke, length, arrowPointSize;
} Arrow;

void arrow_print (Arrow* this) {
    Figure* sup = (Figure*) this;
    printf("Seta de comprimento %d, na posicao (%d,%d), com grossura de %d e angulo de %d°, na cor de fundo (%d,%d,%d) e \"cabeça\" da seta com tamanho %d.\n",
           this->length, sup->x, sup->y, this->stroke, sup->angle, sup->backgroundColor->r, sup->backgroundColor->g, sup->backgroundColor->b, this->arrowPointSize);
    if (sup->isSelected)
        printf("Atualmente, a figura se encontra selecionada.\n\n");
    else
        printf("Atualmente, a figura NÃO se encontra selecionada.\n\n");
}

Arrow* arrow_new (int x, int y, int length, int stroke, int arrowPointSize, int angle, Color* backgroundColor, int isSelected) {
    Arrow* this = malloc(sizeof(Arrow));
    Figure* sup = (Figure*) this;

    sup->print = (Figure_Print) arrow_print;
    sup->x = x;
    sup->y = y;
    sup->angle = angle;
    sup->backgroundColor = backgroundColor;
    sup->isSelected = isSelected;
    
    this->stroke = stroke;
    this->length = length;
    this->arrowPointSize = arrowPointSize;

    return this;
} 

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[4] = {
        (Figure*) rect_new(10,10,100,100,90,color_new(0,0,0),0,color_new(1,1,1),5),
        (Figure*) arrow_new(30,20,500,10,5,15,color_new(100,100,100),1),
        (Figure*) rect_new(15,40,50,50,180,color_new(255,255,255),0,color_new(0,0,0),3),
        (Figure*) arrow_new(5,2,200,5,9,45,color_new(50,50,50),0),
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}