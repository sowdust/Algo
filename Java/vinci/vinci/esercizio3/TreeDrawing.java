package vinci.esercizio3;

/**
 * Classe introdotta solo per ragioni didattiche, al fine di non appesantire la
 * classe BinTree
 */
class TreeDrawing {

    int element;
    TreeDrawing left, right;
    int x, y, width, height;

    TreeDrawing(int element, int x, int y, int width, int height, TreeDrawing left, TreeDrawing right) {
        this.element = element;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.left = left;
        this.right = right;
    }
}
