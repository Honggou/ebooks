//: Progress.java
// Using progress bars and sliders
package c13.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class Progress extends JPanel {
  JProgressBar pb = new JProgressBar();
  JSlider sb = 
    new JSlider(JSlider.HORIZONTAL, 0, 100, 60);
  public Progress() {
    setLayout(new GridLayout(2,1));
    add(pb);
    sb.setValue(0);
    sb.setPaintTicks(true);
    sb.setMajorTickSpacing(20);
    sb.setMinorTickSpacing(5);
    sb.setBorder(new TitledBorder("Slide Me"));
    sb.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        pb.setValue(sb.getValue());
      }
    });
    add(sb);
  }
  public static void main(String args[]) {
    Show.inFrame(new Progress(),200,150);
  }
} ///:~