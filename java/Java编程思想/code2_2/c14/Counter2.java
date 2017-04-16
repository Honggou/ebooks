//: c14:Counter2.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// A responsive user interface with threads.
// <applet code=Counter2 width=300 height=100>
// </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class Counter2 extends JApplet {
  private class SeparateSubTask extends Thread {
    int count = 0;
    boolean runFlag = true;
    SeparateSubTask() { start(); }
    public void run() {
      while (true) {
       try {
        sleep(100);
       } catch (InterruptedException e){}
       if(runFlag) 
         t.setText(Integer.toString(count++));
      }
    }
  } 
  private SeparateSubTask sp = null;
  private JTextField t = new JTextField(10);
  private JButton 
    onOff = new JButton("Toggle"),
    start = new JButton("Start");
  class StartL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(sp == null)
        sp = new SeparateSubTask();
    }
  }
  class OnOffL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(sp != null)
        sp.runFlag = !sp.runFlag; // invertFlag();
    }
  }
  public void init() {
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(t);
    start.addActionListener(new StartL());
    cp.add(start);
    onOff.addActionListener(new OnOffL());
    cp.add(onOff);
  }
  public static void main(String[] args) {
    Console.run(new Counter2 (), 300, 100);
  }
} ///:~