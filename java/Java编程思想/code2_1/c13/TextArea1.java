//: c13:TextArea1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using the JTextArea control.
// <applet code=TextArea1 width=350 height=200>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class TextArea1 extends JApplet {
  JButton
    b1 = new JButton("Text Area 1"),
    b2 = new JButton("Text Area 2"),
    b3 = new JButton("Replace Text"),
    b4 = new JButton("Insert Text");
  JTextArea 
    t1 = new JTextArea("t1", 1, 30),
    t2 = new JTextArea("t2", 4, 30);
  ActionListener a1 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      getAppletContext().showStatus(t1.getText());
    }
  };
  ActionListener a2 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      t2.setText("Inserted by Button 2");
      t2.append(": " + t1.getText());
      getAppletContext().showStatus(t2.getText());
    }
  };
  ActionListener a3 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      String s = " Replacement ";
      t2.replaceRange(s, 3, 3 + s.length());
    }
  };
  ActionListener a4 = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      t2.insert(" Inserted ", 10);
    }
  };
  public void init() {
    b1.addActionListener(a1);
    b2.addActionListener(a2);
    b3.addActionListener(a3);
    b4.addActionListener(a4);
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b1);
    cp.add(t1);
    cp.add(b2);
    cp.add(t2);
    cp.add(b3);
    cp.add(b4);
  }
  public static void main(String[] args) {
    Console.run(new TextArea1(), 350, 200);
  }
} ///:~