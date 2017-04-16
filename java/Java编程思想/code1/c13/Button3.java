//: Button3.java
// Matching events on button text
import java.awt.*;
import java.applet.*;

public class Button3 extends Applet {
  Button 
    b1 = new Button("Button 1"), 
    b2 = new Button("Button 2");
  public void init() {
    add(b1);
    add(b2);
  }
  public boolean action (Event evt, Object arg) {
    if(arg.equals("Button 1"))
      getAppletContext().showStatus("Button 1");
    else if(arg.equals("Button 2"))
      getAppletContext().showStatus("Button 2");
    // Let the base class handle it:
    else 
      return super.action(evt, arg);
    return true; // We've handled it here
  }
} ///:~