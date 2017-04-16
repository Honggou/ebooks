//: Counter3.java
// Using the Runnable interface to turn the 
// main class into a thread.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Counter3 
    extends Applet implements Runnable {
  private int count = 0;
  private boolean runFlag = true;
  private Thread selfThread = null;
  private Button 
    onOff = new Button("Toggle"),
    start = new Button("Start");
  private TextField t = new TextField(10);
  public void init() {
    add(t);
    start.addActionListener(new StartL());
    add(start);
    onOff.addActionListener(new OnOffL());
    add(onOff);
  }
  public void run() {
    while (true) {
      try {
        selfThread.sleep(100);
      } catch (InterruptedException e){}
      if(runFlag) 
        t.setText(Integer.toString(count++));
    }
  }
  class StartL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(selfThread == null) {
        selfThread = new Thread(Counter3.this);
        selfThread.start();
      }
    }
  }
  class OnOffL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      runFlag = !runFlag;
    }
  }
  public static void main(String[] args) {
    Counter3 applet = new Counter3();
    Frame aFrame = new Frame("Counter3");
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