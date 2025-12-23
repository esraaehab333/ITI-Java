import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

class Line {
    int x1, y1, x2, y2;
    Line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Lab5DrawMultipleLinesUsingVector extends JPanel
        implements MouseListener, MouseMotionListener {
    Vector<Line> lines = new Vector<Line>();
    int x1, y1, x2, y2;

    public Lab5DrawMultipleLinesUsingVector() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
       lines.add(new Line(x1, y1, x2, y2));
    }

    public void mouseDragged(MouseEvent e) {
        lines.get(lines.size()-1).x2 = e.getX();
        lines.get(lines.size()-1).y2 = e.getY();
        repaint();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Multiple Lines By Mouse");
        Lab5DrawMultipleLinesUsingVector panel = new Lab5DrawMultipleLinesUsingVector();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}