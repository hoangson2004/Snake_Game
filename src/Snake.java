import java.awt.Image;
import javax.swing.ImageIcon;

public class Snake {
    public static final int MAX_LENGTH = 100;

    private SnakePosition[] snakePosition;
    private int length;
    private int dir;
    private int level;
    private Image imgSnakeBody = new ImageIcon(getClass().getResource(
            "/images/imgSnakeBody.png")).getImage();
    private Image imgSnakeHead = new ImageIcon(getClass().getResource(
            "/images/imgSnakeHead.png")).getImage();

    private int W_IMG_BODY = imgSnakeBody.getWidth(null);
    private int H_IMG_BODY = imgSnakeBody.getHeight(null);

    public Snake() {
        snakePosition = new SnakePosition[MAX_LENGTH];
        snakePosition[0] = new SnakePosition();
        snakePosition[0].setX(100);
        snakePosition[0].setY(60);
        snakePosition[0].setImg(imgSnakeHead);
        snakePosition[1] = new SnakePosition();
        snakePosition[1].setX(80);
        snakePosition[1].setY(60);
        snakePosition[1].setImg(imgSnakeBody);
        snakePosition[2] = new SnakePosition();
        snakePosition[2].setX(60);
        snakePosition[2].setY(60);
        snakePosition[2].setImg(imgSnakeBody);

        length = 2;
        dir = 4;

    }

    public SnakePosition[] getSnakePosition() {
        return snakePosition;
    }

    public void setSnakePosition(SnakePosition[] snakePosition) {
        this.snakePosition = snakePosition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void move() {
        for (int i = 0; i <= length; i++) {
            snakePosition[i].setoX(snakePosition[i].getX());
            snakePosition[i].setoY(snakePosition[i].getY());
            if (i == 0) {
                switch (dir) {
                    case 1:
                        snakePosition[0].setY(snakePosition[0].getY() - H_IMG_BODY);
                        break;
                    case 2:
                        snakePosition[0].setY(snakePosition[0].getY() + H_IMG_BODY);
                        break;
                    case 3:
                        snakePosition[0].setX(snakePosition[0].getX() - W_IMG_BODY);
                        break;
                    case 4:
                        snakePosition[0].setX(snakePosition[0].getX() + W_IMG_BODY);
                        break;
                }
            } else {
                snakePosition[i].setX(snakePosition[i - 1].getoX());
                snakePosition[i].setY(snakePosition[i - 1].getoY());
            }
        }
        throughWall();
    }

    public void grow() {
        this.length++;
        snakePosition[length] = new SnakePosition();
        snakePosition[length].setImg(imgSnakeBody);
    }

    public boolean isDead() {
        for (int i = 1; i <= length; i++) {
            if (snakePosition[0].getX() == snakePosition[i].getX()
                    && snakePosition[0].getY() == snakePosition[i].getY()) {
                return true;
            }
        }
        return false;
    }

    public void throughWall() {
        for (int i = 0; i <= length; i++) {
            if (snakePosition[i].getY() < 20)
                snakePosition[i].setY(Maps.H_MAP);
            if (snakePosition[i].getY() > Maps.H_MAP)
                snakePosition[i].setY(20);
            if (snakePosition[i].getX() < 20)
                snakePosition[i].setX(Maps.W_MAP);
            if (snakePosition[i].getX() > Maps.W_MAP)
                snakePosition[i].setX(20);
        }
    }

}