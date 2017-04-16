//: GoodIdea.java
// The best way to design classes using the new
// Java 1.1 event model: use an inner class for
// each different event. This maximizes 
// flexibility and modularity.
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GoodIdea extends Frame {
  Button 
    b1 = new Button("Button 1"), 
    b2 = new Button("Button 2");
  public GoodIdea() {
    setLayout(new FlowLayout());
    b1.addActionListener(new B1L());
    b2.addActionListener(new B2L());
    add(b1);
    add(b2);
  }
  public class B1L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button 1 pressed");
    }
  }
  public class B2L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button 2 pressed");
    }
  }
  public static void main(String[] args) {
    Frame f = new GoodIdea();
    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          System.out.println("Window Closing");
          System.exit(0);
        }
      });
    f.setSize(300,200);
    f.setVisible(true);
  }
} ///:~