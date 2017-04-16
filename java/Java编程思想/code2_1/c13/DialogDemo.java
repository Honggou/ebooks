//: c13:DialogDemo.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Creating and using Dialog Boxes.
// <applet code=DialogDemo width=125 height=50>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

class MyDialog extends JDialog {
  public MyDialog(JFrame parent) {
    super(parent, "My dialog", true);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(new JLabel("Here is my dialog"));
    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        setVisible(false); // Closes the dialog
      }
    });
    cp.add(ok);
    setSize(150,125);
  }
}

public class DialogDemo extends JApplet {
  JButton b1 = new JButton("Dialog Box");
  MyDialog dlg = new MyDialog(null);
  public void init() {
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        dlg.show();
      }
    });
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
  }
  public static void main(String[] args) {
    Console.run(new DialogDemo(), 125, 50);
  }
} ///:~