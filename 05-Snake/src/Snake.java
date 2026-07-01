/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/


public class Snake {

    private Point[] points;
    private final AudColor color = AudColor.BLUE; //In Anleitung steht awt.Color verwenden??
    private Direction nextDirection = Direction.RIGHT;
    private Direction lastDirection;

    public enum Direction {RIGHT, DOWN, LEFT, UP}

    public Snake(int length, int x, int y) {
        if (length < 0)
            throw new IllegalArgumentException();

        points = new Point[length];
        points[0] = new Point(x, y);
    }

    public Snake(int x, int y) {
        points = new Point[5];
        points[0] = new Point(x, y);
    }

    public void paint(AudGraphics g) {
        g.setColor(new AudColor(color.getRed(), color.getGreen(), color.getBlue()));

        for (int i = 0; i < points.length; i++) {
            if (points[i] != null) {
                g.fillRect(points[i].getX() * SnakeGame.SQUARE_SIZE, points[i].getY() * SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
            }
        }
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public void step() {


        Point[] copyPoints = new Point[points.length];

        System.arraycopy(points, 0, copyPoints, 1, points.length - 1);

        switch (nextDirection) {
            case RIGHT:
                copyPoints[0] = new Point(points[0].getX() + 1, points[0].getY());
                lastDirection = Direction.RIGHT;
                break;
            case DOWN:
                copyPoints[0] = new Point(points[0].getX(), points[0].getY() + 1);
                lastDirection = Direction.DOWN;
                break;
            case LEFT:
                copyPoints[0] = new Point(points[0].getX() - 1, points[0].getY());
                lastDirection = Direction.LEFT;

                break;
            case UP:
                copyPoints[0] = new Point(points[0].getX(), points[0].getY() - 1);
                lastDirection = Direction.UP;

                break;
        }
        points = copyPoints;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }


    boolean collidesWith(GameItem item) {
        return collidesWith(item.getPosition().getX(), item.getPosition().getY());
    }

    boolean collidesWith(int x, int y) {
        for(Point point : points) {
            return x == point.getX() && y == point.getY();
        }
        return false;
    }

    boolean collidesWithSelf() {
        for(int i = 1; i < points.length; i++) {
            if(points[i] != null) {
                if (collidesWith(points[i].getX(), points[i].getY())) {
                    return true;
                }
            }
        }
        return false;

    }

    public void grow(int grow) {
        if(grow < 0 )
            throw new IllegalArgumentException();

        Point[] newPoint = new Point[points.length+grow];
        System.arraycopy(points, 0, newPoint, 0, points.length);
        points = newPoint;
    }
}
