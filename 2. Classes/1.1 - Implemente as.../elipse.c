#include <stdio.h>

typedef struct {
  int x, y;
  int w, h;
  int r, g, b;
} Elipse;

void print (Elipse* elipse) {
  printf("Elipse de cor (%d,%d,%d), tamanho (%d,%d) e na posicao (%d,%d).\n",
    elipse->r, elipse->g, elipse->b, elipse->w,elipse->h, elipse->x,elipse->y);
}

void main (void) {
    Elipse elipse1 = { 1,1, 10,10, 0,0,0 };
    print(&elipse1);
}

// Saida:
// Elipse de cor (0,0,0), tamanho (10,10) e na posicao (1,1).