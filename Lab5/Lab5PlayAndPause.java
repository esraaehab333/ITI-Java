import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Button;

public class Lab5PlayAndPause extends JPanel implements Runnable {

    Thread th;
    int x = 200;
    int y = 200;
    int ballSize = 40;
    int dx = 3;
    int dy = 3;

    boolean paused = true;   
    final Object lock = new Object();

    public Lab5PlayAndPause() {
        th = new Thread(this);
        th.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, ballSize, ballSize);
    }

    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    while (paused) {
                        lock.wait();  
                    }
                }

                x += dx;
                y += dy;

                if (x <= 0 || x >= getWidth() - ballSize)
                    dx = -dx;

                if (y <= 0 || y >= getHeight() - ballSize)
                    dy = -dy;

                repaint();
                Thread.sleep(30);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resumeThread() {
        synchronized (lock) {
            paused = false;
            lock.notify();   
        }
    }

    public void pauseThread() {
        paused = true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab5PlayAndPause");
        Lab5PlayAndPause panel = new Lab5PlayAndPause();

        Button startBtn = new Button("Start");
        Button pauseBtn = new Button("Pause");

        startBtn.addActionListener(e -> panel.resumeThread());
        pauseBtn.addActionListener(e -> panel.pauseThread());

        panel.setBounds(0, 0, 500, 450);
        startBtn.setBounds(150, 460, 80, 30);
        pauseBtn.setBounds(260, 460, 80, 30);

        frame.add(panel);
        frame.add(startBtn);
        frame.add(pauseBtn);

        frame.setSize(500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
