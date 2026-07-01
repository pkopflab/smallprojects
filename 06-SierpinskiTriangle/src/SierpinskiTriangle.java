/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.awt.*;
import java.awt.event.KeyEvent;

public class SierpinskiTriangle extends SierpinskiTriangleAbstract {

    public static int MAX_DEPTH = 7;
    public static int MIN_DEPTH = 0;


    public static void main(String[] args) {
        new SierpinskiTriangle();
    }


    @Override
    protected void drawTriangleRec(int ax, int ay, int bx, int by, int cx, int cy, int depth, Color color) {

        System.out.println(depth);

        int width = (bx - ax);
        int height = cy - ay;

        int dx = (width / 4) + ax;
        int dy = (height / 2) + ay;

        int ex = bx - (width / 4);
        int ey = (height / 2) + ay;

        int fx = bx - (width / 2);

        if (width > 3 && depth != 0) {

            g.setColor(color);
            g.fillPolygon(new int[]{ax, bx, cx}, new int[]{ay, by, cy}, 3);


            g.setColor(Color.white);
            g.fillPolygon(new int[]{dx, ex, fx}, new int[]{dy, ey, ay}, 3);

            drawTriangleRec(dx, dy, ex, ey, cx, cy, depth-1, color);
            drawTriangleRec(ax, ay, fx, ay, dx, dy, depth-1, color);
            drawTriangleRec(fx, ay, bx, by, ex, ey, depth-1, color);

        } else if (depth == 0) {
            g.setColor(color);
            g.fillPolygon(new int[]{ax, bx, cx}, new int[]{ay, by, cy}, 3);
        }


    }

    @Override
    protected void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            if (depth < MAX_DEPTH) {
                depth += 1;
                paint(getGraphics());
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (depth > MIN_DEPTH) {
                depth -= 1;
                paint(getGraphics());
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            toggleRandomColor();
            System.out.println(useRandomColor);
            paint(getGraphics());
        }


    }

    @Override
    protected void toggleRandomColor() {
        useRandomColor = !useRandomColor;
    }

    @Override
    protected void drawTriangle() {
        super.drawTriangle();

        if (useRandomColor) {
            color = new Color((int) (Math.random() * 0x1000000));
        } else {
            color = Color.black;
        }

    }
}
