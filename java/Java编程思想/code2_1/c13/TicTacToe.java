//: c13:TicTacToe.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstration of dialog boxes
// and creating your own components.
// <applet code=TicTacToe
//  width=200 height=100></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

class ToeButton extends JPanel {
  int state = ToeDialog.BLANK;
  ToeDialog p;
  public ToeButton(ToeDialog parent) {
    p = parent;
    addMouseListener(ml);
  }
  MouseListener ml = new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
      if(state == ToeDialog.BLANK) {
        state = p.turn;
        p.turn = (p.turn == ToeDialog.XX ?
          ToeDialog.OO : ToeDialog.XX);
      } else
        state = (state == ToeDialog.XX ? 
          ToeDialog.OO : ToeDialog.XX);
      repaint();
    }
  };
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int x1 = 0;
    int y1 = 0;
    int x2 = getWidth() - 1;
    int y2 = getHeight() - 1;
    g.drawRect(x1, y1, x2, y2);
    x1 = x2/4;
    y1 = y2/4;
    int wide = x2/2;
    int high = y2/2;
    if(state == ToeDialog.XX) {
      g.drawLine(x1, y1, x1 + wide, y1 + high);
      g.drawLine(x1, y1 + high, x1 + wide, y1);
    }
    if(state == ToeDialog.OO) {
      g.drawOval(x1, y1, x1+wide/2, y1+high/2);
    }
  }
}

class ToeDialog extends JDialog {
  static final int BLANK = 0, XX = 1, OO = 2;
  int turn = XX; // Start with x's turn
  // w = number of cells wide
  // h = number of cells high
  public ToeDialog(int w, int h) {
    setTitle("The game itself");
   // JDK 1.3 close dialog:
   //#setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    // JDK 1.2 close dialog:
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        dispose();
      }
    });    
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(w, h));
    for(int i = 0; i < w * h; i++)
      cp.add(new ToeButton(this));
    setSize(w * 50, h * 50);
  }
}

public class TicTacToe extends JApplet {
  JTextField 
    rows = new JTextField("3"),
    cols = new JTextField("3");
  JButton go = new JButton("go");
  public void init() {
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(2,2));
    p.add(new JLabel("Rows", JLabel.CENTER));
    p.add(rows);
    p.add(new JLabel("Columns", JLabel.CENTER));
    p.add(cols);
    Container cp = getContentPane();
    cp.add(p, BorderLayout.NORTH);
    cp.add(go, BorderLayout.SOUTH);
    go.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        JDialog d = new ToeDialog(
          Integer.parseInt(rows.getText()),
          Integer.parseInt(cols.getText()));
        d.setVisible(true);
      }
    });
  }
  public static void main(String[] args) {
    Console.run(new TicTacToe(), 200, 100);
  }
} ///:~