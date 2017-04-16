//: c13:GoodIdea.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// The best way to design classes using the
// Java event model: use an inner class for
// each different event. This maximizes 
// flexibility and modularity.
// <applet code=GoodIdea
//  width=200 height=100></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.bruceeckel.swing.*;

public class GoodIdea extends JApplet {
  JButton 
    b1 = new JButton("Button 1"), 
    b2 = new JButton("Button 2");
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    b1.addActionListener(new B1L());
    b2.addActionListener(new B2L());
    cp.add(b1);
    cp.add(b2);
  }
  public class B1L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button 1 pressed");
    }
  }
  public class B2L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button 2 pressed");
    }
  }
  public static void main(String[] args) {
    Console.run(new GoodIdea(), 200, 100);
  }
} ///:~