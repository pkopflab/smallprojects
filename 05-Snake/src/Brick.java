/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class Brick extends GameItem {

    public Brick(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(AudColor.gray);
        g.fillRect(this.getPosition().getX()*SnakeGame.SQUARE_SIZE, this.getPosition().getY() * SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);

    }


}
