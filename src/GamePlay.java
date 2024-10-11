import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements Runnable {
    private ReentrantLock reentrantLock;
    private Maps maps;
    private Snake snake;
    private Food food;
    private boolean isEndGame, isPausing;
    private Thread thread;
    private int scores;
    private GUI gui;
    private Graphics2D g2D;
    int countTime;
    private KeyHandler keyHandler;

    private Image imgPauseGame = new ImageIcon(getClass().getResource(
            "/images/imgPauseGame.png")).getImage();
    private Image imgGameOver = new ImageIcon(getClass().getResource(
            "/images/imgGameOver.png")).getImage();
    JLabel lbLogo, lbScore, lbScoreNumber, lbState;

    
    public GamePlay(GUI gui) {
        this.setLayout(null);
        this.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);

        snake = new Snake();
        scores = 0;
        maps = new Maps();
        this.add(maps);
        food = new Food();
        food.initFood(snake);
        reentrantLock = new ReentrantLock();
        isEndGame = false;
        isPausing = true;
        countTime = 0;
        this.gui = gui;

        initComponent();
        keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        new Thread(this).start();
    }

    public Graphics2D getG2D() {
        return g2D;
    }

    public void setG2D(Graphics2D g2d) {
        g2D = g2d;
    }

    private void handleKeyInput() {
        int code = keyHandler.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                if (snake.getDir() != 2)
                    snake.setDir(1);
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDir() != 1)
                    snake.setDir(2);
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDir() != 4)
                    snake.setDir(3);
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDir() != 3)
                    snake.setDir(4);
                break;
            case KeyEvent.VK_SPACE:
                if (isPausing && !isEndGame) {
                    isPausing = false;
                } else {
                    isPausing = true;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (isEndGame) startGame();
        }
        keyHandler.resetKeyCode();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D) g;
        reentrantLock.lock();
        if (!isEndGame && !isPausing) {
            snake.move();
        }
        reentrantLock.unlock();
        for (int i = 0; i <= snake.getLength(); i++) {
            g2D.drawImage(snake.getSnakePosition()[i].getImg(),
                    snake.getSnakePosition()[i].getX(),
                    snake.getSnakePosition()[i].getY(), null);
        }
        eatFood();
        if (countTime % 2 == 0) {
            drawFood(g2D);
        }
        if (isPausing) {
            g2D.drawImage(imgPauseGame, 20 + (Maps.W_MAP - imgPauseGame.getWidth(null)) / 2,
                    20 + (Maps.H_MAP - imgPauseGame.getHeight(null)) / 2, null);
        }
        if (snake.isDead()) {
            g2D.drawImage(imgGameOver,
                    20 + (Maps.W_MAP - imgGameOver.getWidth(null))/2,
                    20 + (Maps.H_MAP - imgGameOver.getHeight(null))/2, null);
            isEndGame = true;
        }

    }

    @Override
    public void run() {
        while (!isEndGame) {
            handleKeyInput();
            repaint();
            try {
                Thread.sleep(150);
                countTime = countTime + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (isEndGame) {
            handleKeyInput();
            try {
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void eatFood() {
        if (Math.abs(snake.getSnakePosition()[0].getX() - food.getxFood()) <= food
                .getImgFood().getWidth(null) / 2
                && Math.abs(snake.getSnakePosition()[0].getY()
                        - food.getyFood()) <= food.getImgFood().getHeight(null) / 2) {

            if (snake.getLength() < Snake.MAX_LENGTH - 1) {
                snake.grow();
            }
            scores += 1;

            lbScoreNumber.setText(String.valueOf(scores));

            food.initFood(snake);
        }
    }

    public void startGame() {
        if (!isPausing) {
            snake = new Snake();

            isEndGame = false;
            snake.setDir(4);
            thread = new Thread(this);
            thread.start();
            scores = 0;
            lbScoreNumber.setText("0");
        } else {
            isPausing = false;
        }
        countTime = 0;
    }
   
    

    public void initComponent() {
        // Logo
        lbLogo = new JLabel();
        lbLogo.setBounds(620, 20, 160, 160);
        lbLogo.setIcon(new ImageIcon(getClass().getResource(
                "/images/imgLogo.png")));
        this.add(lbLogo);

        // State
        lbState = new JLabel();
		lbState.setBounds(620, 185, 160, 150);
		lbState.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgState.png")));

        // Score
        lbScore = new JLabel();
        lbScore.setBounds(30, 40, 35, 35);
        lbScore.setIcon(new ImageIcon(getClass().getResource(
                "/images/imgScore.png")));
        lbScoreNumber = new JLabel("0");
        lbScoreNumber.setForeground(Color.BLUE);
		lbScoreNumber.setHorizontalAlignment(JLabel.CENTER);
		lbScoreNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbScoreNumber.setBounds(70, 45, 70, 25);
        this.add(lbState);
        lbState.add(lbScore);
        lbState.add(lbScoreNumber);
    }

    public void drawFood(Graphics2D g2D) {
        g2D.drawImage(food.getImgFood(), food.getxFood(), food.getyFood(), null);
    }

    
}
