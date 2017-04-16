//: c13:ListCombo.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// List boxes & Combo boxes.
// <applet code=ListCombo
//  width=300 height=125></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class ListCombo extends JApplet {
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(2,1));
    JList list = new JList(ButtonGroups.ids);
    cp.add(new JScrollPane(list));
    JComboBox combo = new JComboBox();
    for(int i = 0; i < 100; i++)
      combo.addItem(Integer.toString(i));
    cp.add(combo);
  }
  public static void main(String[] args) {
    Console.run(new ListCombo(), 300, 125);
  }
} ///:~