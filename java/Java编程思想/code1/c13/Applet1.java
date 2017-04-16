//: Applet1.java
// Very simple applet
package c13;
import java.awt.*;
import java.applet.*;

public class Applet1 extends Applet {
  public void paint(Graphics g) {
    g.drawString("First applet", 10, 10);
  }
} ///:~