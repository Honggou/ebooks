//: Label1.java
// Using labels
import java.awt.*;
import java.applet.*;

public class Label1 extends Applet {
  TextField t1 = new TextField("t1", 10);
  Label labl1 = new Label("TextField t1");
  Label labl2 = new Label("                   ");
  Label labl3 = new Label("                    ",
    Label.RIGHT);
  Button b1 = new Button("Test 1");
  Button b2 = new Button("Test 2");
  public void init() {
    add(labl1); add(t1);
    add(b1); add(labl2);
    add(b2); add(labl3);
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(b1))
      labl2.setText("Text set into Label");
    else if(evt.target.equals(b2)) {
      if(labl3.getText().trim().length() == 0)
        labl3.setText("labl3");
      if(labl3.getAlignment() == Label.LEFT)
        labl3.setAlignment(Label.CENTER);
      else if(labl3.getAlignment()==Label.CENTER)
        labl3.setAlignment(Label.RIGHT);
      else if(labl3.getAlignment() == Label.RIGHT)
        labl3.setAlignment(Label.LEFT);
    }
    else 
      return super.action(evt, arg);
    return true;
  }
} ///:~