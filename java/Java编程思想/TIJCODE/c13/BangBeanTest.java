//: c13:BangBeanTest.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// <applet code=BangBeanTest 
// width=400 height=500></applet>
import bangbean.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.bruceeckel.swing.*;

public class BangBeanTest extends JApplet {
  JTextField txt = new JTextField(20);
  // During testing, report actions:
  class BBL implements ActionListener {
    int count = 0;
    public void actionPerformed(ActionEvent e){
      txt.setText("BangBean action "+ count++);
    }
  }
  public void init() {
    BangBean bb = new BangBean();
    try {
      bb.addActionListener(new BBL());
    } catch(TooManyListenersException e) {
      txt.setText("Too many listeners");
    }
    Container cp = getContentPane();
    cp.add(bb);
    cp.add(BorderLayout.SOUTH, txt);
  }
  public static void main(String[] args) {
    Console.run(new BangBeanTest(), 400, 500);
  }
} ///:~