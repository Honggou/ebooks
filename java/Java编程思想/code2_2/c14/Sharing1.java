//: c14:Sharing1.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Problems with resource sharing while threading.
// <applet code=Sharing1 width=350 height=500>
// <param name=size value="12">
// <param name=observers value="15">
// </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class Sharing1 extends JApplet {
  private static int accessCount = 0;
  private static JTextField aCount = 
    new JTextField("0", 7);
  public static void incrementAccess() {
    accessCount++;
    aCount.setText(Integer.toString(accessCount));
  }
  private JButton 
    start = new JButton("Start"),
    observer = new JButton("Observe");
  private boolean isApplet = true;
  private int numCounters = 0;
  private int numObservers = 0;
  private TwoCounter[] s;
  class TwoCounter extends Thread {
    private boolean started = false;
    private JTextField 
      t1 = new JTextField(5),
      t2 = new JTextField(5);
    private JLabel l = 
      new JLabel("count1 == count2");
    private int count1 = 0, count2 = 0;
    // Add the display components as a panel
    // to the given container:
    public TwoCounter() {
      JPanel p = new JPanel();
      p.add(t1);
      p.add(t2);
      p.add(l);
      getContentPane().add(p);
    }
    public void start() {
      if(!started) {
        started = true;
        super.start();
      }
    }
    public void run() {
      while (true) {
        t1.setText(Integer.toString(count1++));
        t2.setText(Integer.toString(count2++));
        try {
          sleep(500);
        } catch (InterruptedException e){}
      }
    }
    public void synchTest() {
      Sharing1.incrementAccess();
      if(count1 != count2)
        l.setText("Unsynched");
    }
  }
  class Watcher extends Thread {
    public Watcher() { start(); }
    public void run() {
      while(true) {
        for(int i = 0; i < s.length; i++)
          s[i].synchTest();
        try {
          sleep(500);
        } catch (InterruptedException e){}
      }
    }
  }
  class StartL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      for(int i = 0; i < s.length; i++)
        s[i].start();
    }
  }
  class ObserverL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      for(int i = 0; i < numObservers; i++)
        new Watcher();
    }
  }
  public void init() {
    if(isApplet) {
      numCounters = 
        Integer.parseInt(getParameter("size"));
      numObservers = 
        Integer.parseInt(
          getParameter("observers"));
    }
    s = new TwoCounter[numCounters];
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    for(int i = 0; i < s.length; i++)
      s[i] = new TwoCounter();
    JPanel p = new JPanel();
    start.addActionListener(new StartL());
    p.add(start);
    observer.addActionListener(new ObserverL());
    p.add(observer);
    p.add(new JLabel("Access Count"));
    p.add(aCount);
    cp.add(p);
  }
  public static void main(String[] args) {
    Sharing1 applet = new Sharing1();
    // This isn't an applet, so set the flag and
    // produce the parameter values from args:
    applet.isApplet = false;
    applet.numCounters = 
      (args.length == 0 ? 12 :
        Integer.parseInt(args[0]));
    applet.numObservers =
      (args.length < 2 ? 15 :
        Integer.parseInt(args[1]));
    Console.run(applet, 350, 
      applet.numCounters * 50);
  }
} ///:~