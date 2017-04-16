//: c13:RadioButton1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using JRadioButtons.
// <applet code=RadioButton1 
// width=200 height=75> </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class RadioButton1 extends JApplet {
  JTextField t = 
    new JTextField("Radio button 2", 15);
  ButtonGroup g = new ButtonGroup();
  JRadioButton 
    rb1 = new JRadioButton("one", false),
    rb2 = new JRadioButton("two", true),
    rb3 = new JRadioButton("three",false);
  ActionListener al = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      t.setText("Radio button " + 
        ((JRadioButton)e.getSource()).getText());
    }
  };
  public void init() {
    rb1.addActionListener(al);
    rb2.addActionListener(al);
    rb3.addActionListener(al);
    g.add(rb1); g.add(rb2); g.add(rb3);
    t.setEditable(false);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(t); 
    cp.add(rb1); 
    cp.add(rb2); 
    cp.add(rb3); 
  }
  public static void main(String[] args) {
    Console.run(new RadioButton1(), 200, 75);
  }
} ///:~