//: c13:TextField1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using the JTextField control.
// <applet code=TextField1 width=350 height=75>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class TextField1 extends JApplet {
  JButton
    b1 = new JButton("Get Text"),
    b2 = new JButton("Set Text");
  JTextField 
    t = new JTextField("Starting text: ", 30);
  String s = new String();
  ActionListener a1 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      getAppletContext().showStatus(t.getText());
      s = t.getSelectedText();
      if(s == null)
        s = t.getText();
      t.setEditable(true);
    }
  };
  ActionListener a2 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      t.setText("Inserted by Button 2: " + s);
      t.setEditable(false);
    }
  };
  public void init() {
    b1.addActionListener(a1);
    b2.addActionListener(a2);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
    cp.add(b2);
    cp.add(t);
  }
  public static void main(String[] args) {
    Console.run(new TextField1(), 350, 75);
  }
} ///:~