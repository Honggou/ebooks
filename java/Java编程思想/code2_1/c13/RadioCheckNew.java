//: c13:RadioCheckNew.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Radio buttons and Check Boxes.
// <applet code=RadioCheckNew 
// width=325 height=100></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class RadioCheckNew extends JApplet {
  JTextField t = new JTextField(20);
  JCheckBox[] cb = {
    new JCheckBox("Check Box 1"),
    new JCheckBox("Check Box 2"),
    new JCheckBox("Check Box 3") };
  ButtonGroup group = new ButtonGroup();
  JRadioButton 
    cb4 = new JRadioButton("four"),
    cb5 = new JRadioButton("five"),
    cb6 = new JRadioButton("six");
  // Checking the source:
  class ILCheck implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      for(int i = 0; i < cb.length; i++) {
        if(e.getSource().equals(cb[i])) {
          t.setText("Check box " + (i + 1));
          return;
        }
      }
    }
  }
  // vs. an individual class for each item:
  class IL4 implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      t.setText("Radio button four");
    }
  }
  class IL5 implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      t.setText("Radio button five");
    }
  }
  class IL6 implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      t.setText("Radio button six");
    }
  }
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    t.setEditable(false);
    cp.add(t); 
    ILCheck il = new ILCheck();
    for(int i = 0; i < cb.length; i++) {
      cb[i].addItemListener(il);
      cp.add(cb[i]);
    }
    group.add(cb4);
    group.add(cb5);
    group.add(cb6);
    cb4.addItemListener(new IL4());
    cb5.addItemListener(new IL5());
    cb6.addItemListener(new IL6());
    cp.add(cb4); cp.add(cb5); cp.add(cb6); 
  }
  public static void main(String[] args) {
    Console.run(new RadioCheckNew(), 325, 100);
  }
} ///:~