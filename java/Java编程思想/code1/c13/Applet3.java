//: Applet3.java
// Shows init(), start() and stop() activities
import java.awt.*;
import java.applet.*;

public class Applet3 extends Applet {
  String s;
  int inits = 0;
  int starts = 0;
  int stops = 0;
  public void init() { inits++; }
  public void start() { starts++; }
  public void stop() { stops++; }
  public void paint(Graphics g) {
    s = "inits: " + inits + 
      ", starts: " + starts +
      ", stops: " + stops;
    g.drawString(s, 10, 10);
  }
} ///:~