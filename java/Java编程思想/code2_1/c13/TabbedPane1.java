//: c13:TabbedPane1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates the Tabbed Pane.
// <applet code=TabbedPane1 
// width=350 height=200> </applet>
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class TabbedPane1 extends JApplet {
  String[] flavors = { "Chocolate", "Strawberry",
    "Vanilla Fudge Swirl", "Mint Chip", 
    "Mocha Almond Fudge", "Rum Raisin", 
    "Praline Cream", "Mud Pie" };
  JTabbedPane tabs = new JTabbedPane();
  public void init() {
    for(int i = 0; i < flavors.length; i++)
      tabs.addTab(flavors[i], 
        new JButton("Tabbed pane " + i));
    tabs.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e) {
        getAppletContext().showStatus(
          "Tab selected: " + 
            tabs.getSelectedIndex());
      }
    });
    getContentPane().add(tabs);
  }
  public static void main(String[] args) {
    Console.run(new TabbedPane1(), 350, 200);
  }
} ///:~