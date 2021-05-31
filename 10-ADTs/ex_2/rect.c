#include "rect.h"
#include<stdio.h>

typedef struct Rect {
    int x;
    int y;
};

Rect* rect_new (void) {
    Rect rect;
    rect.x = 0;
    rect.y = 0;
}

void rect_drag (Rect* this, int dx, int dy) {
    this->x += dx;
    this->y += dy;
}

void rect_print (Rect* this) {
    printf("RECT (%c, %c)", this->x, this->y);
}