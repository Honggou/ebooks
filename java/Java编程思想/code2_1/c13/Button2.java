//: c13:Button2.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Capturing button presses.
// <applet code=Button2 width=200 height=50>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class Button2 extends JApplet {
  JButton 
    b1 = new JButton("Button 1"), 
    b2 = new JButton("Button 2");
  class BL implements ActionListener {
    public void actionPerformed(ActionEvent e){
      String name = 
        ((JButton)e.getSource()).getText();
      getAppletContext().showStatus(name);
    }
  }
  BL al = new BL();
  public void init() {
    b1.addActionListener(al);
    b2.addActionListener(al);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
    cp.add(b2);
  }
  public static void main(String[] args) {
    Console.run(new Button2(), 200, 50);
  }
} ///:~