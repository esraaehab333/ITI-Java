import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Lab4TextBannerApplet extends JPanel implements Runnable {
    Thread th;
    int x = 0;
    int y = 200;
    
    public Lab4TextBannerApplet() {
        th = new Thread(this);
        th.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("JAVA World", x, y);
    }
    
    public void run() {
        while(true) {
            try {
                x += 5;
                if(x > getWidth()) {
                    x = -250;
                }
                repaint();
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Applet Viewer: StringApplet");
        Lab4TextBannerApplet applet = new Lab4TextBannerApplet();
        
        frame.add(applet);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}