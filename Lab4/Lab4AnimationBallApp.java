import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Lab4AnimationBallApp extends JPanel implements Runnable {
    Thread th;
    int x = 200;
    int y = 200;
    int ballSize = 40;
    int dx = 3;
    int dy = 2;
    Random random;
    
    public Lab4AnimationBallApp() {
        random = new Random();
        dx = random.nextInt(5) + 2;
        dy = random.nextInt(5) + 2;
        if(random.nextBoolean()) dx = -dx;
        if(random.nextBoolean()) dy = -dy;
        th = new Thread(this);
        th.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, ballSize, ballSize);
    }
    
    public void run() {
        while(true) {
            try {
                x += dx;
                y += dy;
                if(x <= 0 || x >= getWidth() - ballSize) {
                    dx = -dx;
                    dy += random.nextInt(3) - 1;
                }
                if(y <= 0 || y >= getHeight() - ballSize) {
                    dy = -dy;
                    dx += random.nextInt(3) - 1;
                }
                repaint();
                Thread.sleep(30);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab4AnimationBallApp");
        Lab4AnimationBallApp applet = new Lab4AnimationBallApp();
        
        frame.add(applet);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}