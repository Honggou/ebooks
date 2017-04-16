//: c13:HTMLButton.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Putting HTML text on Swing components.
// <applet code=HTMLButton width=200 height=500>
// </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class HTMLButton extends JApplet {
  JButton b = new JButton("<html><b><font size=+2>" +
    "<center>Hello!<br><i>Press me now!");
  public void init() {
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        getContentPane().add(new JLabel("<html>"+
          "<i><font size=+4>Kapow!"));
        // Force a re-layout to
        // include the new label:
        validate();
      }
    });
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(b);
  }
  public static void main(String[] args) {
    Console.run(new HTMLButton(), 200, 500);
  }
} ///:~