/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/


public abstract class GameItem {

    private final Point position;

    public GameItem(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public abstract void paint(AudGraphics g);

}
