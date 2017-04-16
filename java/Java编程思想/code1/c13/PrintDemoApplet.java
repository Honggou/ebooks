//: PrintDemoApplet.java
// Creating a Frame from within an Applet
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class PrintDemoApplet extends Applet {
  public void init() {
    Button b = new Button("Run PrintDemo");
    b.addActionListener(new PDL());
    add(b);
  }
  class PDL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      final PrintDemo pd = new PrintDemo();
      pd.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          pd.dispose();
        }
      });
      pd.setSize(500, 500);
      pd.show();
    }
  }
} ///:~