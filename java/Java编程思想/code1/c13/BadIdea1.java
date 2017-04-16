//: BadIdea1.java
// Some literature recommends this approach,
// but it's missing the point of the new event
// model in Java 1.1
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BadIdea1 extends Frame 
    implements ActionListener, WindowListener {
  Button 
    b1 = new Button("Button 1"), 
    b2 = new Button("Button 2");
  public BadIdea1() {
    setLayout(new FlowLayout());
    addWindowListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    add(b1);
    add(b2);
  }
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == b1)
      System.out.println("Button 1 pressed");
    else if(source == b2)
      System.out.println("Button 2 pressed");
    else
      System.out.println("Something else");
  }    
  public void windowClosing(WindowEvent e) {
    System.out.println("Window Closing");
    System.exit(0);
  }
  public void windowClosed(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  public void windowOpened(WindowEvent e) {}  

  public static void main(String[] args) {
    Frame f = new BadIdea1();
    f.setSize(300,200);
    f.setVisible(true);
  }
} ///:~