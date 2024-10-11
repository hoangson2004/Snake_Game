import java.awt.Image;

public class SnakePosition {
    private int x, oX;
    private int y, oY;
    private Image img;

    public SnakePosition() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getoX() {
        return oX;
    }

    public void setoX(int oX) {
        this.oX = oX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getoY() {
        return oY;
    }

    public void setoY(int oY) {
        this.oY = oY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

}