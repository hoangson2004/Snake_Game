import java.awt.Toolkit;
import javax.swing.JFrame;

public class GUI extends JFrame {
    public static final int W_SCREEN = Toolkit.getDefaultToolkit()
            .getScreenSize().width;
    public static final int H_SCREEN = Toolkit.getDefaultToolkit()
            .getScreenSize().height;
    public static final int W_FRAME = 800;
    public static final int H_FRAME = 490;

    private GamePlay gamePlay;

    public GUI() {
        this.setTitle("Snake Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        gamePlay = new GamePlay(this);
        this.add(gamePlay);

        gamePlay.setVisible(true);
        gamePlay.setFocusable(true);
        gamePlay.requestFocusInWindow();

        this.setVisible(true);
    }

}
