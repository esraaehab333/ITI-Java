import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BasicStroke;

class Lab3DrawingLamp extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));  
       // g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(2)); 
        g2.fillOval(200, 200, 250, 80);
        g2.fillOval(300, 325, 60, 100);
        g2.fillOval(400, 325, 40, 60);
        g2.fillOval(200, 325, 40, 60);
        g2.setColor(Color.BLACK);
        g2.drawLine(200, 240, 150, 400);
        g2.drawLine(450, 240, 500, 400);
        g2.drawArc(150, 325, 350, 150, 0, -180);
        g2.drawLine(300, 475, 250, 600);
        g2.drawLine(350, 475, 400, 600);
        g2.drawRect(200, 600, 250, 40);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing Lamp");
        Lab3DrawingLamp panel = new Lab3DrawingLamp();
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
