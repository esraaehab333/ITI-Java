import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Lab5DrawSingleLineApp extends JPanel
        implements MouseListener, MouseMotionListener {

    int x1, y1, x2, y2;
    boolean drawing = false;

    public Lab5DrawSingleLineApp() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (drawing) {
            g.drawLine(x1, y1, x2, y2);
        }
    }
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        x2 = x1;
        y2 = y1;

        drawing = true;
    }

    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Single Line By Mouse");
        Lab5DrawSingleLineApp panel = new Lab5DrawSingleLineApp();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
