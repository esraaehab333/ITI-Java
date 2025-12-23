import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lab5ButtonCounterApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Counter App");
        CounterPanel panel = new CounterPanel();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class CounterPanel extends JPanel implements ActionListener {

    private int count = 0;
    private Button incButton;
    private Button decButton;

    public CounterPanel() {
        incButton = new Button("Increment +");
        decButton = new Button("Decrement -");
        add(incButton);
        add(decButton);
        incButton.addActionListener(this);
        decButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == incButton) {
            count++;
        } else if (e.getSource() == decButton) {
            count--;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Count: " + count, 50, 120);
    }
}
