/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public class SnakeGame extends AudGameWindow {

    private int score;
    private final int width;
    private final int height;

    private long lastSnakeUpdate;

    private final Snake snake;
    private Apple apple;

    public static final int SQUARE_SIZE = 16;
    public static final int STEP_TIME = 100;
    public static final int GROW_AMOUNT = 5;

    private Brick[] wall;

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.start();
    }

    public SnakeGame() {
        score = 0;
        setTitle("AuD-Snake - Score: " + score);

        width = getGameAreaWidth() / SQUARE_SIZE;
        height = getGameAreaHeight() / SQUARE_SIZE;

        snake = new Snake(width / 2, height / 2);

        lastSnakeUpdate = System.currentTimeMillis();

        buildWall();
        createNewApple();

    }

    @Override
    public void updateGame(long time) {
        long elapsedTime = time - lastSnakeUpdate;

        int steps = (int) (elapsedTime / STEP_TIME);

        for (int i = 0; i < steps; i++) {
            snake.step();
            lastSnakeUpdate += STEP_TIME;
            checkCollisions();
        }
    }

    @Override
    public void paintGame(AudGraphics g) {
        g.setColor(AudColor.white);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());

        snake.paint(g);
        apple.paint(g);

        for (Brick brick : wall) {
            brick.paint(g);
        }
    }

    @Override
    public void handleInput(int keyCode) {

        if (keyCode == KeyEvent.VK_DOWN) {
            if (snake.getLastDirection() != Snake.Direction.UP) {
                snake.setNextDirection(Snake.Direction.DOWN);
            }

        } else if (keyCode == KeyEvent.VK_UP) {
            if (snake.getLastDirection() != Snake.Direction.DOWN) {
                snake.setNextDirection(Snake.Direction.UP);
            }

        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (snake.getLastDirection() != Snake.Direction.LEFT) {
                snake.setNextDirection(Snake.Direction.RIGHT);
            }

        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (snake.getLastDirection() != Snake.Direction.RIGHT) {
                snake.setNextDirection(Snake.Direction.LEFT);
            }

        }


    }

    private void buildWall() {
        wall = new Brick[166];
        int wallNumber = 0;

        for (int i = 0; i < width - 1; i++) {
            wall[wallNumber] = new Brick(i, 0);
            wallNumber += 1;
        }

        for (int i = 0; i < height - 1; i++) {
            wall[wallNumber] = new Brick(width - 1, i);
            wallNumber += 1;
        }
        for (int i = 0; i < height - 1; i++) {
            wall[wallNumber] = new Brick(0, i);
            wallNumber += 1;
        }

        for (int i = 0; i < width + 1; i++) {
            wall[wallNumber] = new Brick(i, height - 1);
            wallNumber += 1;
        }
    }

    public void checkCollisions() {
        for (Brick brick : wall) {
            if (snake.collidesWith(brick)) {
                stop();
                showDialog("You died! Score: " + score);
            }
        }
        if (snake.collidesWithSelf()) {
            stop();
            showDialog("You died! Score: " + score);
        }

        if (snake.collidesWith(apple)) {
            snake.grow(GROW_AMOUNT);
            score += Apple.VALUE;
            setTitle("AuD-Snake - Score: " + score);
            createNewApple();
        }


    }

    public void createNewApple() {
        int randomX = (int) (Math.random() * (width - 2) + 1);
        int randomY = (int) (Math.random() * (height - 2) + 1);


        if (snake.collidesWith(randomX, randomY)) {
            createNewApple();
        } else {
            apple = new Apple(randomX, randomY);
        }


    }

}
