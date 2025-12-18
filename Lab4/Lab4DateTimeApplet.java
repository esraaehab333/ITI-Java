import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Date;

public class Lab4DateTimeApplet extends JPanel implements Runnable {
    Thread th;
    
    public Lab4DateTimeApplet() {
        th = new Thread(this);
        th.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Date d = new Date();
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        g.drawString(d.toString(), 50, 100);
    }
    
    public void run() {
        while(true) {
            try {
                repaint();
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Date Time Display");
        Lab4DateTimeApplet applet = new Lab4DateTimeApplet();
        
        frame.add(applet);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}