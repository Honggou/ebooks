//: c13:Applet3.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Shows init(), start() and stop() activities.
// <applet code=Applet3 width=250 height=50>
// </applet>
import javax.swing.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class Applet3 extends JApplet {
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
  public static void main(String[] args) {
    Console.run(new Applet3 (), 250, 50);
  }
} ///:~