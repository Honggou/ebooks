//: c13:List1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using JLists.
// <applet code=List1
// width=200 height=350> </applet>
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class List1 extends JApplet {
  String[] flavors = { "Chocolate", "Strawberry",
    "Vanilla Fudge Swirl", "Mint Chip", 
    "Mocha Almond Fudge", "Rum Raisin", 
    "Praline Cream", "Mud Pie" };
  JList list = new JList(flavors);
  JTextArea t = 
    new JTextArea(flavors.length + 1, 15);
  public void init() {
    t.setEditable(false);
    list.addListSelectionListener(
      new ListSelectionListener() {
      public void
      valueChanged(ListSelectionEvent e) {
        t.setText(""); // Erase the text area
        Object[] items= list.getSelectedValues();
        for(int i = 0; i < items.length; i++)
          t.append(items[i] + "\n");
      }
    });
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(t);
    cp.add(list);
  }
  public static void main(String[] args) {
    Console.run(new List1(), 200, 350);
  }
} ///:~