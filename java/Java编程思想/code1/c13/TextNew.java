//: TextNew.java
// Text fields with Java 1.1 events
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class TextNew extends Applet {
  Button 
    b1 = new Button("Get Text"), 
    b2 = new Button("Set Text");
  TextField 
    t1 = new TextField(30),
    t2 = new TextField(30),
    t3 = new TextField(30);
  String s = new String();
  public void init() {
    b1.addActionListener(new B1());
    b2.addActionListener(new B2());
    t1.addTextListener(new T1());
    t1.addActionListener(new T1A());
    t1.addKeyListener(new T1K());
    add(b1);
    add(b2);
    add(t1);
    add(t2);
    add(t3);
  }
  class T1 implements TextListener {
    public void textValueChanged(TextEvent e) {
      t2.setText(t1.getText());
    }
  }
  class T1A implements ActionListener {
    private int count = 0;
    public void actionPerformed(ActionEvent e) {
      t3.setText("t1 Action Event " + count++);
    }
  }
  class T1K extends KeyAdapter {
    public void keyTyped(KeyEvent e) {
      String ts = t1.getText();
      if(e.getKeyChar() == 
          KeyEvent.VK_BACK_SPACE) {
        // Ensure it's not empty:
        if( ts.length() > 0) {
          ts = ts.substring(0, ts.length() - 1);
          t1.setText(ts);
        }
      } 
      else
        t1.setText(
          t1.getText() +
            Character.toUpperCase(
              e.getKeyChar()));
      t1.setCaretPosition(
        t1.getText().length());
      // Stop regular character from appearing:
      e.consume(); 
    }
  }
  class B1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      s = t1.getSelectedText();
      if(s.length() == 0) s = t1.getText();
      t1.setEditable(true);
    }
  }
  class B2 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t1.setText("Inserted by Button 2: " + s);
      t1.setEditable(false);
    }
  }
  public static void main(String[] args) {
    TextNew applet = new TextNew();
    Frame aFrame = new Frame("TextNew");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(300,200);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~