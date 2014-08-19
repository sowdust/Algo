package vinci.esercizio3;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Classe che permette di visualizzare l'albero in una finestra grafica.
 */
class DrawingPanel extends JPanel {

    /**
     * Needed by the Serializable interface.
     */
    private static final long serialVersionUID = 1L;

    BinTree tree;
    private int visitedNodeNumber = 1;
    private Graphics graphics;
    private int hScale = 40;
    private int vScale = 40;

    DrawingPanel(BinTree t) {
        tree = t;
    }

    TreeDrawing build(BinTree t, int level) {
        if (t == null) {
            return null;
        } else {
            TreeDrawing left = build(t.left, level + 1);
            String str = Integer.toString(t.element);
            Rectangle2D rect = graphics.getFontMetrics().getStringBounds(str, graphics);
            int width = (int) Math.round(rect.getWidth()) + 2;
            int height = (int) Math.round(rect.getHeight());
            int dx = (int) Math.round(rect.getCenterX());
            int dy = (int) Math.round(rect.getCenterY());
            int x = hScale * visitedNodeNumber - dx;
            int y = vScale * level + dy;
            visitedNodeNumber++;
            TreeDrawing right = build(t.right, level + 1);
            return new TreeDrawing(t.element, x, y, width, height, left, right);
        }
    }

    void draw(TreeDrawing td) {
        if (td != null) {
            String str = Integer.toString(td.element);
            graphics.drawRect(td.x, td.y, td.width, td.height);
            graphics.drawString(str, td.x + 1, td.y + td.height - 2);
            if (td.left != null) {
                int x1 = td.x + td.width / 2;
                int y1 = td.y + td.height;
                int x2 = td.left.x + td.left.width / 2;
                int y2 = td.left.y;
                graphics.drawLine(x1, y1, x2, y2);
            }
            draw(td.left);
            if (td.right != null) {
                int x1 = td.x + td.width / 2;
                int y1 = td.y + td.height;
                int x2 = td.right.x + td.right.width / 2;
                int y2 = td.right.y;
                graphics.drawLine(x1, y1, x2, y2);
            }
            draw(td.right);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = g;
        TreeDrawing td = build(tree, 1);
        draw(td);
        visitedNodeNumber = 1;
    }
}
