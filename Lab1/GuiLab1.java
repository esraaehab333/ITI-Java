import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
class GuiLab1 extends JPanel{
   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello Java", 50, 20);
    }
  public static void main (String [] args){
    JFrame frame = new JFrame ("Hello World Frame");
    GuiLab1 panel = new GuiLab1();
    
    frame.setContentPane(panel);
    frame.setSize(500,500);
    frame.setBackground(Color.BLUE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
   }
}