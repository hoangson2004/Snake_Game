import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Food {
    private int xFood, yFood, xBonusFood, yBonusFood;
    private Image imgFood = new ImageIcon(getClass().getResource(
            "/images/imgFood.png")).getImage();
    private boolean isBonus;

    public Image getImgFood() {
        return imgFood;
    }

    public void setImgFood(Image imgFood) {
        this.imgFood = imgFood;
    }

    public int getxFood() {
        return xFood;
    }

    public void setxFood(int xFood) {
        this.xFood = xFood;
    }

    public int getyFood() {
        return yFood;
    }

    public void setyFood(int yFood) {
        this.yFood = yFood;
    }

    public int getxBonusFood() {
        return xBonusFood;
    }

    public void setxBonusFood(int xBonusFood) {
        this.xBonusFood = xBonusFood;
    }

    public int getyBonusFood() {
        return yBonusFood;
    }

    public void setyBonusFood(int yBonusFood) {
        this.yBonusFood = yBonusFood;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean isBonus) {
        this.isBonus = isBonus;
    }

    public void initFood(Snake snake) {
        Random random = new Random();
        int x = 0, y = 0;
        boolean isOverlap = false;
        do {
            x = 1 + random.nextInt(Maps.W_MAP / 20);
            y = 1 + random.nextInt(Maps.H_MAP / 20);
            xFood = 20 * x;
            yFood = 20 * y;
            for (int i = 0; i <= snake.getLength(); i++) {
                if (snake.getSnakePosition()[i].getX() == xFood
                        && snake.getSnakePosition()[i].getY() == yFood) {
                    isOverlap = true;
                    break;
                }
            }
        } while (isOverlap);
    }

}