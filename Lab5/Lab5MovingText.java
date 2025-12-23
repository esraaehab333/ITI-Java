import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.FontMetrics;

public class Lab5MovingText {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Move String With Arrows");
        MoveStringPanel panel = new MoveStringPanel();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
}

class MoveStringPanel extends JPanel implements KeyListener {

    int x = 200;
    int y = 250;
    final int STEP = 10;
    String text = "JAVA";

    public MoveStringPanel() {
        addKeyListener(this);
        setFocusable(true);
        //is a method call that enables a component to receive keyboard focus.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(text, x, y);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        FontMetrics fm = getGraphics().getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                if (x > 0)
                    x -= STEP;
                break;

            case KeyEvent.VK_RIGHT:
                if (x + textWidth < getWidth())
                    x += STEP;
                break;

            case KeyEvent.VK_UP:
                if (y - textHeight > 0)
                    y -= STEP;
                break;

            case KeyEvent.VK_DOWN:
                if (y < getHeight())
                    y += STEP;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}