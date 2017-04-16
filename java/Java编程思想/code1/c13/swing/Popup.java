//: Popup.java
// Creating popup menus with Swing
package c13.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Popup extends JPanel {
  JPopupMenu popup = new JPopupMenu();
  JTextField t = new JTextField(10);
  public Popup() {
    add(t);
    ActionListener al = new ActionListener() {
      public void actionPerformed(ActionEvent e){
        t.setText(
          ((JMenuItem)e.getSource()).getText());
      }
    };
    JMenuItem m = new JMenuItem("Hither");
    m.addActionListener(al);
    popup.add(m);
    m = new JMenuItem("Yon");
    m.addActionListener(al);
    popup.add(m);
    m = new JMenuItem("Afar");
    m.addActionListener(al);
    popup.add(m);
    popup.addSeparator();
    m = new JMenuItem("Stay Here");
    m.addActionListener(al);
    popup.add(m);
    enableEvents(AWTEvent.MOUSE_EVENT_MASK);
  }
  protected void processMouseEvent(MouseEvent e){
    if (e.isPopupTrigger())
      popup.show(
        e.getComponent(), e.getX(), e.getY());
    else super.processMouseEvent(e);
  }
  public static void main(String args[]) {
    Show.inFrame(new Popup(),200,150);
  }
} ///:~