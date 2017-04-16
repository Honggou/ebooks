//: Interrupt.java
// The alternative approach to using stop()
// when a thread is blocked
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

class Blocked extends Thread {
  public synchronized void run() {
    try {
      wait(); // Blocks
    } catch(InterruptedException e) {
      System.out.println("InterruptedException");
    }
    System.out.println("Exiting run()");
  }
}

public class Interrupt extends Applet {
  private Button 
    interrupt = new Button("Interrupt");
  private Blocked blocked = new Blocked();
  public void init() {
    add(interrupt);
    interrupt.addActionListener(
      new ActionListener() {
        public 
        void actionPerformed(ActionEvent e) {
          System.out.println("Button pressed");
          if(blocked == null) return;
          Thread remove = blocked;
          blocked = null; // to release it
          remove.interrupt();
        }
      });
    blocked.start();
  }
  public static void main(String[] args) {
    Interrupt applet = new Interrupt();
    Frame aFrame = new Frame("Interrupt");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(200,100);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~