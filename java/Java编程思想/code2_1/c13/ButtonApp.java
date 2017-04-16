//: c13:ButtonApp.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Creating an application.
// <applet code=ButtonApp
//  width=200 height=200></applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class ButtonApp extends JApplet {
  JButton 
    b1 = new JButton("Hello"), 
    b2 = new JButton("Howdy");
  JTextField t = new JTextField(15);
  ActionListener al = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      String name = 
        ((JButton)e.getSource()).getText();
      t.setText(name);
    }
  };
  public void init() {
    b1.addActionListener(al);
    b2.addActionListener(al);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
    cp.add(b2);
    cp.add(t);
  }
  public static void main(String[] args) {
    Console.run(new ButtonApp(), 200, 200);
  }
} ///:~