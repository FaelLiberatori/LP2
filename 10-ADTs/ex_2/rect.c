#include "rect.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct Rect {
    int x;
    int y;
    int w;
    int h;
} Rect;

Rect* rect_new (void) {
    Rect *rect = malloc (sizeof(Rect));
    rect->x = 0;
    rect->y = 0;
    rect->w = 10;
    rect->h = 20;


    return rect;
}

void rect_drag (Rect* this, int dx, int dy) {
    this->x += dx;
    this->y += dy;
}

void rect_print (Rect* this) {
    printf("RECT (%d, %d) (%d, %d)\n", this->x, this->y, this->w, this->h);
}