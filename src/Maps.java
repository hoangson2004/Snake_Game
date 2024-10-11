import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Maps extends JPanel {
    public static final int W_MAP = 580;
    public static final int H_MAP = 420;

    private Image imgBackground = new ImageIcon(getClass()
            .getResource("/images/imgBackground.png")).getImage();

    public Maps() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(10, 10, W_MAP + 20, H_MAP + 20);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(imgBackground, 10, 10, W_MAP, H_MAP, null);
    }
}