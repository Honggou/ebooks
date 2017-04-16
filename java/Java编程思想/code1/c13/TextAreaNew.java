//: TextAreaNew.java
// Controlling scrollbars with the TextArea
// component in Java 1.1
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class TextAreaNew extends Applet {
  Button b1 = new Button("Text Area 1");
  Button b2 = new Button("Text Area 2");
  Button b3 = new Button("Replace Text");
  Button b4 = new Button("Insert Text");
  TextArea t1 = new TextArea("t1", 1, 30);
  TextArea t2 = new TextArea("t2", 4, 30);
  TextArea t3 = new TextArea("t3", 1, 30,
    TextArea.SCROLLBARS_NONE);
  TextArea t4 = new TextArea("t4", 10, 10,
    TextArea.SCROLLBARS_VERTICAL_ONLY);
  TextArea t5 = new TextArea("t5", 4, 30,
    TextArea.SCROLLBARS_HORIZONTAL_ONLY);
  TextArea t6 = new TextArea("t6", 10, 10,
    TextArea.SCROLLBARS_BOTH);
  public void init() {
    b1.addActionListener(new B1L());
    add(b1);
    add(t1);
    b2.addActionListener(new B2L());
    add(b2);
    add(t2);
    b3.addActionListener(new B3L());
    add(b3);
    b4.addActionListener(new B4L());
    add(b4);
    add(t3); add(t4); add(t5); add(t6);
  }
  class B1L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t5.append(t1.getText() + "\n");
    }
  }
  class B2L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t2.setText("Inserted by Button 2");
      t2.append(": " + t1.getText());
      t5.append(t2.getText() + "\n");
    }
  }
  class B3L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String s = " Replacement ";
      t2.replaceRange(s, 3, 3 + s.length());
    }
  }
  class B4L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t2.insert(" Inserted ", 10);
    }
  }
  public static void main(String[] args) {
    TextAreaNew applet = new TextAreaNew();
    Frame aFrame = new Frame("TextAreaNew");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(300,725);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~