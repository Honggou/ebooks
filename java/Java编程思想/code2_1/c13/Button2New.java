//: c13:Button2New.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Capturing button presses.
// <applet code=Button2New 
// width=200 height=50> </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Must add this
import com.bruceeckel.swing.*;

public class Button2New extends JApplet {
  JButton
    b1 = new JButton("Button 1"),
    b2 = new JButton("Button 2");
  public void init() {
    b1.addActionListener(new B1());
    b2.addActionListener(new B2());
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
    cp.add(b2);
  }
  class B1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      getAppletContext().showStatus("Button 1");
    }
  }
  class B2 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      getAppletContext().showStatus("Button 2");
    }
  }
  public static void main(String[] args) {
    Console.run(new Button2New(), 200, 50);
  }
} ///:~