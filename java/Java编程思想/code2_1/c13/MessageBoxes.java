//: c13:MessageBoxes.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates JoptionPane.
// <applet code=MessageBoxes 
// width=200 height=100> </applet>
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class MessageBoxes extends JApplet {
  JButton[] b = { new JButton("alert"), 
    new JButton("yes/no"), new JButton("Color"),
    new JButton("input"), new JButton("3 vals")
  };
  ActionListener al = new ActionListener() {
    public void actionPerformed(ActionEvent e){
      String id = 
        ((JButton)e.getSource()).getText();
      if(id.equals("alert"))
        JOptionPane.showMessageDialog(null, 
          "There's a bug on you!", "Hey!", 
          JOptionPane.ERROR_MESSAGE);
      else if(id.equals("yes/no"))
        JOptionPane.showConfirmDialog(null, 
          "or no", "choose yes", 
          JOptionPane.YES_NO_OPTION);
      else if(id.equals("Color")) {
        Object[] options = { "Red", "Green" };
        int sel = JOptionPane.showOptionDialog(
          null, "Choose a Color!", "Warning", 
          JOptionPane.DEFAULT_OPTION, 
          JOptionPane.WARNING_MESSAGE, null, 
          options, options[0]);
          if(sel != JOptionPane.CLOSED_OPTION)
            getAppletContext().showStatus(
              "Color Selected: " + options[sel]);
      } else if(id.equals("input")) {
        String val = JOptionPane.showInputDialog(
            "How many fingers do you see?"); 
        getAppletContext().showStatus(val);
      } else if(id.equals("3 vals")) {
        Object[] selections = {
          "First", "Second", "Third" };
        Object val = JOptionPane.showInputDialog(
          null, "Choose one", "Input",
          JOptionPane.INFORMATION_MESSAGE, 
          null, selections, selections[0]);
        if(val != null)
          getAppletContext().showStatus(
            val.toString());
      }
    }
  };
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    for(int i = 0; i < b.length; i++) {
      b[i].addActionListener(al);
      cp.add(b[i]);
    }
  }
  public static void main(String[] args) {
    Console.run(new MessageBoxes(), 200, 100);
  }
} ///:~