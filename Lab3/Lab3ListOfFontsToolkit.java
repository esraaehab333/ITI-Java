import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

class Lab3ListOfFontsToolkit {
    public static void main(String[] args) {
        JFrame frame = new JFrame("List of Fonts");
        MyPanel1 panel = new MyPanel1();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class MyPanel1 extends JPanel {
    Toolkit tk = Toolkit.getDefaultToolkit();
    String[] fonts = tk.getFontList();  

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);

        int y = 20;
        for (String fontName : fonts) {
            Font font = new Font(fontName, Font.BOLD, 18);
            g.setFont(font);
            g.drawString(fontName, 20, y);
            y += 20;
        }
    }
}
