//: AutoEvent.java
// Alternatives to action()
import java.awt.*;
import java.applet.*;
import java.util.*;

class MyButton extends Canvas {
  AutoEvent parent;
  Color color;
  String label;
  MyButton(AutoEvent parent, 
           Color color, String label) {
    this.label = label;
    this.parent = parent;
    this.color = color;
  }
  public void paint(Graphics  g) {
    g.setColor(color);
    int rnd = 30;
    g.fillRoundRect(0, 0, size().width, 
                    size().height, rnd, rnd);
    g.setColor(Color.black);
    g.drawRoundRect(0, 0, size().width, 
                    size().height, rnd, rnd);
    FontMetrics fm = g.getFontMetrics();
    int width = fm.stringWidth(label);
    int height = fm.getHeight();
    int ascent = fm.getAscent();
    int leading = fm.getLeading();
    int horizMargin = (size().width - width)/2;
    int verMargin = (size().height - height)/2;
    g.setColor(Color.white);
    g.drawString(label, horizMargin, 
                 verMargin + ascent + leading);
  }
  public boolean keyDown(Event evt, int key) {
    TextField t = 
      (TextField)parent.h.get("keyDown");
    t.setText(evt.toString());
    return true;
  }
  public boolean keyUp(Event evt, int key) {
    TextField t = 
      (TextField)parent.h.get("keyUp");
    t.setText(evt.toString());
    return true;
  }
  public boolean lostFocus(Event evt, Object w) {
    TextField t = 
      (TextField)parent.h.get("lostFocus");
    t.setText(evt.toString());
    return true;
  }
  public boolean gotFocus(Event evt, Object w) {
    TextField t = 
      (TextField)parent.h.get("gotFocus");
    t.setText(evt.toString());
    return true;
  }
  public boolean 
  mouseDown(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseDown");
    t.setText(evt.toString());
    return true;
  }
  public boolean 
  mouseDrag(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseDrag");
    t.setText(evt.toString());
    return true;
  }
  public boolean 
  mouseEnter(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseEnter");
    t.setText(evt.toString());
    return true;
  }
  public boolean 
  mouseExit(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseExit");
    t.setText(evt.toString());
    return true;
  }
  public boolean 
  mouseMove(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseMove");
    t.setText(evt.toString());
    return true;
  }
  public boolean mouseUp(Event evt,int x,int y) {
    TextField t = 
      (TextField)parent.h.get("mouseUp");
    t.setText(evt.toString());
    return true;
  }
}

public class AutoEvent extends Applet {
  Hashtable h = new Hashtable();
  String[] event = {
    "keyDown", "keyUp", "lostFocus", 
    "gotFocus", "mouseDown", "mouseUp", 
    "mouseMove", "mouseDrag", "mouseEnter", 
    "mouseExit"
  };
  MyButton 
    b1 = new MyButton(this, Color.blue, "test1"),
    b2 = new MyButton(this, Color.red, "test2");
  public void init() {
    setLayout(new GridLayout(event.length+1,2));
    for(int i = 0; i < event.length; i++) {
      TextField t = new TextField();
      t.setEditable(false);
      add(new Label(event[i], Label.CENTER)); 
      add(t);
      h.put(event[i], t);
    }
    add(b1);
    add(b2);
  }
} ///:~