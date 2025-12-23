import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Lab5DragBallApp extends JPanel {
    int x = 200;
    int y = 200;
    int ballSize = 40;
    boolean dragging = false;

    public Lab5DragBallApp() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getX() >= x && e.getX() <= x + ballSize &&
                    e.getY() >= y && e.getY() <= y + ballSize) {
                    dragging = true;
                }
            }

            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    x = e.getX() - ballSize / 2;
                    y = e.getY() - ballSize / 2;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, ballSize, ballSize);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drag Ball App");
        Lab5DragBallApp panel = new Lab5DragBallApp();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}