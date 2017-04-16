//: Suspend.java
// The alternative approach to using suspend()
// and resume(), which have been deprecated
// in Java 1.2.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Suspend extends Applet {
  private TextField t = new TextField(10);
  private Button 
    suspend = new Button("Suspend"),
    resume = new Button("Resume");
  class Suspendable extends Thread {
    private int count = 0;
    private boolean suspended = false;
    public Suspendable() { start(); }
    public void fauxSuspend() { 
      suspended = true;
    }
    public synchronized void fauxResume() {
      suspended = false;
      notify();
    }
    public void run() {
      while (true) {
        try {
          sleep(100);
          synchronized(this) {
            while(suspended)
              wait();
          }
        } catch (InterruptedException e){}
        t.setText(Integer.toString(count++));
      }
    }
  } 
  private Suspendable ss = new Suspendable();
  public void init() {
    add(t);
    suspend.addActionListener(
      new ActionListener() {
        public 
        void actionPerformed(ActionEvent e) {
          ss.fauxSuspend();
        }
      });
    add(suspend);
    resume.addActionListener(
      new ActionListener() {
        public 
        void actionPerformed(ActionEvent e) {
          ss.fauxResume();
        }
      });
    add(resume);
  }
  public static void main(String[] args) {
    Suspend applet = new Suspend();
    Frame aFrame = new Frame("Suspend");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(300,100);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~