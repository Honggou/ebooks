//: Separation.java
// Separating GUI logic and business objects
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

class BusinessLogic {
  private int modifier;
  BusinessLogic(int mod) {
    modifier = mod;
  }
  public void setModifier(int mod) {
    modifier = mod;
  }
  public int getModifier() {
    return modifier;
  }
  // Some business operations:
  public int calculation1(int arg) {
    return arg * modifier;
  }
  public int calculation2(int arg) {
    return arg + modifier;
  }
}

public class Separation extends Applet {
  TextField 
    t = new TextField(20),
    mod = new TextField(20);
  BusinessLogic bl = new BusinessLogic(2);
  Button
    calc1 = new Button("Calculation 1"),
    calc2 = new Button("Calculation 2");
  public void init() {
    add(t);
    calc1.addActionListener(new Calc1L());
    calc2.addActionListener(new Calc2L());
    add(calc1); add(calc2);
    mod.addTextListener(new ModL());
    add(new Label("Modifier:"));
    add(mod);
  }
  static int getValue(TextField tf) {
    try {
      return Integer.parseInt(tf.getText());
    } catch(NumberFormatException e) {
      return 0;
    }
  }
  class Calc1L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t.setText(Integer.toString(
        bl.calculation1(getValue(t))));
    }
  }
  class Calc2L implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      t.setText(Integer.toString(
        bl.calculation2(getValue(t))));
    }
  }
  class ModL implements TextListener {
    public void textValueChanged(TextEvent e) {
      bl.setModifier(getValue(mod));
    }
  }
  public static void main(String[] args) {
    Separation applet = new Separation();
    Frame aFrame = new Frame("Separation");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(200,200);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~