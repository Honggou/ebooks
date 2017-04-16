//: RadioButton1.java
// Using radio buttons
import java.awt.*;
import java.applet.*;

public class RadioButton1 extends Applet {
  TextField t = 
    new TextField("Radio button 2", 30);
  CheckboxGroup g = new CheckboxGroup();
  Checkbox 
    cb1 = new Checkbox("one", g, false),
    cb2 = new Checkbox("two", g, true),
    cb3 = new Checkbox("three", g, false);
  public void init() {
    t.setEditable(false);
    add(t); 
    add(cb1); add(cb2); add(cb3); 
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(cb1))
      t.setText("Radio button 1");
    else if(evt.target.equals(cb2))
      t.setText("Radio button 2");
    else if(evt.target.equals(cb3))
      t.setText("Radio button 3");
    else 
      return super.action(evt, arg);
    return true;
  }
} ///:~