/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Apple extends GameItem {

    public static int VALUE = 0;

    public Apple(int x, int y) {
        super(x, y);
        VALUE += 1;
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(AudColor.red);
        g.fillOval(getPosition().getX()*SnakeGame.SQUARE_SIZE, getPosition().getY()*SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }
}
