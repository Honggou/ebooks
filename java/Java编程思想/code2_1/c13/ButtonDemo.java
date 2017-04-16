//: c13:ButtonDemo.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Looks like Java 1.1 but with J's added.
// <applet code=ButtonDemo width=250 height=75>
// </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import com.bruceeckel.swing.*;

public class ButtonDemo extends JApplet {
  JButton 
    b1 = new JButton("JButton 1"),
    b2 = new JButton("JButton 2");
  JTextField t = new JTextField(20);
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    ActionListener al = new ActionListener() {
      public void actionPerformed(ActionEvent e){
        String name = 
          ((JButton)e.getSource()).getText();
        t.setText(name + " Pressed");
      }
    };
    b1.addActionListener(al);
    cp.add(b1);
    b2.addActionListener(al);
    cp.add(b2);
    cp.add(t);
  }
  public static void main(String[] args) {
    Console.run(new ButtonDemo(), 250, 75);
  }
} ///:~