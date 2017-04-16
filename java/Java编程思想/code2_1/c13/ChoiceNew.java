//: c13:ChoiceNew.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Drop-down lists (combo boxes).
// <applet code=ChoiceNew 
// width=450 height=175></applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class ChoiceNew extends JApplet {
  String[] descriptions1 = { "Ebullient", 
    "Obtuse", "Recalcitrant", "Brilliant" };
  String[] descriptions2 = { "Somnescent",
    "Timorous", "Florid", "Putrescent" };
  JTextArea t = new JTextArea(7, 40);
  JComboBox c = new JComboBox(descriptions1);
  JButton b = new JButton("Add items");
  int count = 0;
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    t.setLineWrap(true);
    t.setEditable(false);
    cp.add(t);
    cp.add(c);
    cp.add(b);
    c.addItemListener(new CL());
    b.addActionListener(new BL());
  }
  class CL implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      t.setText("index: " +  c.getSelectedIndex()
        + "   " + e);
    }
  }
  class BL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(count < descriptions2.length)
        c.addItem(descriptions2[count++]);
      if(count >=descriptions2.length)
        b.setEnabled(false);
    }
  }
  public static void main(String[] args) {
    Console.run(new ChoiceNew(), 450, 175);
  }
} ///:~