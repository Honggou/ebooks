//: Button1.java
// Putting buttons on an applet
import java.awt.*;
import java.applet.*;

public class Button1 extends Applet {
  Button 
    b1 = new Button("Button 1"), 
    b2 = new Button("Button 2");
  public void init() {
    add(b1);
    add(b2);
  }
} ///:~