//: ListCombo.java
// List boxes & Combo boxes
package c13.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListCombo extends JPanel {
  public ListCombo() {
    setLayout(new GridLayout(2,1));
    JList list = new JList(ButtonGroups.ids);
    add(new JScrollPane(list));
    JComboBox combo = new JComboBox();
    for(int i = 0; i < 100; i++)
      combo.addItem(Integer.toString(i));
    add(combo);
  }
  public static void main(String args[]) {
    Show.inFrame(new ListCombo(),200,200);
  }
} ///:~