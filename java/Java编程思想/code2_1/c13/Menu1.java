//: c13:Menu1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Shows submenus, checkbox menu 
// items, and swapping menus
// <applet code=Menu1
//  width=300 height=100> </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class Menu1 extends JApplet {
  String[] flavors = { "Chocolate", "Strawberry",
    "Vanilla Fudge Swirl", "Mint Chip", 
    "Mocha Almond Fudge", "Rum Raisin", 
    "Praline Cream", "Mud Pie" 
  };
  JTextField t = new JTextField("No flavor", 30);
  JMenuBar mb1 = new JMenuBar();
  JMenu 
    f = new JMenu("File"),
    m = new JMenu("Flavors"),
    s = new JMenu("Safety");
  // Alternative approach:
  JCheckBoxMenuItem[] safety = {
    new JCheckBoxMenuItem("Guard"),
    new JCheckBoxMenuItem("Hide")
  };
  JMenuItem[] file = {
    new JMenuItem("Open"),
    new JMenuItem("Exit")
  };
  // A second menu bar to swap to:
  JMenuBar mb2 = new JMenuBar();
  JMenu fooBar = new JMenu("fooBar");
  JMenuItem[] other = {
    new JMenuItem("Foo"),
    new JMenuItem("Bar"),
    new JMenuItem("Baz"),
  };
  JButton b = new JButton("Swap Menus");
  public void init() {
    for(int i = 0; i < flavors.length; i++) {
      JMenuItem mi = new JMenuItem(flavors[i]);
      mi.addActionListener(al);
      m.add(mi);
      // Add separators at intervals:
      if((i+1) % 3 == 0) 
        m.addSeparator();
    }
    for(int i = 0; i < safety.length; i++) {
      safety[i].addActionListener(al);
      s.add(safety[i]);
    }
    f.add(s);
    for(int i = 0; i < file.length; i++) {
      file[i].addActionListener(al);
      f.add(file[i]);
    }
    mb1.add(f);
    mb1.add(m);
    t.setEditable(false);
    Container cp = getContentPane();
    cp.add(t, BorderLayout.CENTER);
    // Set up the system for swapping menus:
    b.addActionListener(al);
    cp.add(b, BorderLayout.NORTH);
    for(int i = 0; i < other.length; i++) {
      other[i].addActionListener(al);
      fooBar.add(other[i]);
    }
    mb2.add(fooBar);
    setJMenuBar(mb1);
  }
  ActionListener al = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String arg = e.getActionCommand();
      Object source = e.getSource();
      if(source.equals(b)) {
        JMenuBar m = getJMenuBar();
        setJMenuBar(m == mb1 ? mb2 : mb1);
        validate(); // Refresh the frame
      } else if(source instanceof JMenuItem) {
        if(arg.equals("Open")) {
          String s = t.getText();
          boolean chosen = false;
          for(int i = 0; i < flavors.length; i++)
            if(s.equals(flavors[i])) 
              chosen = true;
          if(!chosen)
            t.setText("Choose a flavor first!");
          else
            t.setText("Opening "+s+". Mmm, mm!");
        } else if(source.equals(file[1]))
          System.exit(0);
        // CheckboxMenuItems cannot use String 
        // matching; you must match getSource():
        else if(source.equals(safety[0]))
          t.setText("Guard the Ice Cream! " +
            "Guarding is "+safety[0].getState());
        else if(source.equals(safety[1]))
          t.setText("Hide the Ice Cream! " +
            "Is it cold? "+safety[1].getState());
        else 
          t.setText(arg);
      }
    }
  };
  public static void main(String[] args) {
    Console.run(new Menu1(), 300, 100);
  }
} ///:~