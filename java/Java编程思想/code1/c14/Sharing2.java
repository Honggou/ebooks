//: Sharing2.java
// Using the synchronized keyword to prevent
// multiple access to a particular resource.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

class TwoCounter2 extends Thread {
  private boolean started = false;
  private TextField 
    t1 = new TextField(5),
    t2 = new TextField(5);
  private Label l = 
    new Label("count1 == count2");
  private int count1 = 0, count2 = 0;
  public TwoCounter2(Container c) {
    Panel p = new Panel();
    p.add(t1);
    p.add(t2);
    p.add(l);
    c.add(p);
  }    
  public void start() {
    if(!started) {
      started = true;
      super.start();
    }
  }
  public synchronized void run() {
    while (true) {
      t1.setText(Integer.toString(count1++));
      t2.setText(Integer.toString(count2++));
      try {
        sleep(500);
      } catch (InterruptedException e){}
    }
  }
  public synchronized void synchTest() {
    Sharing2.incrementAccess();
    if(count1 != count2)
      l.setText("Unsynched");
  }
}

class Watcher2 extends Thread {
  private Sharing2 p;
  public Watcher2(Sharing2 p) { 
    this.p = p;
    start();
  }
  public void run() {
    while(true) {
      for(int i = 0; i < p.s.length; i++)
        p.s[i].synchTest();
      try {
        sleep(500);
      } catch (InterruptedException e){}
    }
  }
}

public class Sharing2 extends Applet {
  TwoCounter2[] s;
  private static int accessCount = 0;
  private static TextField aCount = 
    new TextField("0", 10);
  public static void incrementAccess() {
    accessCount++;
    aCount.setText(Integer.toString(accessCount));
  }
  private Button 
    start = new Button("Start"),
    observer = new Button("Observe");
  private boolean isApplet = true;
  private int numCounters = 0;
  private int numObservers = 0;
  public void init() {
    if(isApplet) {
      numCounters = 
        Integer.parseInt(getParameter("size"));
      numObservers = 
        Integer.parseInt(
          getParameter("observers"));
    }
    s = new TwoCounter2[numCounters];
    for(int i = 0; i < s.length; i++)
      s[i] = new TwoCounter2(this);
    Panel p = new Panel();
    start.addActionListener(new StartL());
    p.add(start);
    observer.addActionListener(new ObserverL());
    p.add(observer);
    p.add(new Label("Access Count"));
    p.add(aCount);
    add(p);
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
        new Watcher2(Sharing2.this);
    }
  }
  public static void main(String[] args) {
    Sharing2 applet = new Sharing2();
    // This isn't an applet, so set the flag and
    // produce the parameter values from args:
    applet.isApplet = false;
    applet.numCounters = 
      (args.length == 0 ? 5 :
        Integer.parseInt(args[0]));
    applet.numObservers =
      (args.length < 2 ? 5 :
        Integer.parseInt(args[1]));
    Frame aFrame = new Frame("Sharing2");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(350, applet.numCounters *100);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~