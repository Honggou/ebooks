//: c13:Label1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using JLabels.
// <applet code=Label1 width=200 height=100>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class Label1 extends JApplet {
  JTextField t1 = new JTextField("t1", 10);
  JLabel 
    labl1 = new JLabel("TextField t1"),
    labl2 = new JLabel("                   ");
  JButton 
    b1 = new JButton("Test 1"),
    b2 = new JButton("Test 2");
  ActionListener a1 = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      t1.setText("Button 1");
      labl2.setText("Text set into Label");
    }
  };
  ActionListener a2 = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      t1.setText("Button 2");
      labl1.setText("Hello");
    }
  };
  public void init() {
    b1.addActionListener(a1);
    b2.addActionListener(a2);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(labl1); 
    cp.add(t1);
    cp.add(b1); 
    cp.add(labl2);
    cp.add(b2);
  }
  public static void main(String[] args) {
    Console.run(new Label1(), 200, 100);
  }
} ///:~