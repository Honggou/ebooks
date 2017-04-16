//: BadTechnique.java
// It's possible to override components this way,
// but the listener approach is much better, so
// why would you?
import java.awt.*;
import java.awt.event.*;

class Display {
  public static final int
    EVENT = 0, COMPONENT = 1,
    MOUSE = 2, MOUSE_MOVE = 3,
    FOCUS = 4, KEY = 5, ACTION = 6,
    LAST = 7;
  public String[] evnt;
  Display() {
    evnt = new String[LAST];
    for(int i = 0; i < LAST; i++)
      evnt[i] = new String();
  }
  public void show(Graphics g) {
    for(int i = 0; i < LAST; i++)
      g.drawString(evnt[i], 0, 10 * i + 10);
  }
}

class EnabledPanel extends Panel {
  Color c;
  int id;
  Display display = new Display();
  public EnabledPanel(int i, Color mc) {
    id = i;
    c = mc;
    setLayout(new BorderLayout());
    add(new MyButton(), BorderLayout.SOUTH);
    // Type checking is lost. You can enable and
    // process events that the component doesn't
    // capture:
    enableEvents(
      // Panel doesn't handle these:
      AWTEvent.ACTION_EVENT_MASK |
      AWTEvent.ADJUSTMENT_EVENT_MASK |
      AWTEvent.ITEM_EVENT_MASK |
      AWTEvent.TEXT_EVENT_MASK |
      AWTEvent.WINDOW_EVENT_MASK |
      // Panel can handle these:
      AWTEvent.COMPONENT_EVENT_MASK |
      AWTEvent.FOCUS_EVENT_MASK |
      AWTEvent.KEY_EVENT_MASK |
      AWTEvent.MOUSE_EVENT_MASK |
      AWTEvent.MOUSE_MOTION_EVENT_MASK |
      AWTEvent.CONTAINER_EVENT_MASK);
      // You can enable an event without
      // overriding its process method.
  }
  // To eliminate flicker:
  public void update(Graphics g) {
    paint(g);
  }
  public void paint(Graphics  g) {
    g.setColor(c);
    Dimension s = getSize();
    g.fillRect(0, 0, s.width, s.height);
    g.setColor(Color.black);
    display.show(g);
  }
  public void processEvent(AWTEvent e) {
    display.evnt[Display.EVENT]= e.toString();
    repaint();
    super.processEvent(e);
  }
  public void
  processComponentEvent(ComponentEvent e) {
    switch(e.getID()) {
      case ComponentEvent.COMPONENT_MOVED:
        display.evnt[Display.COMPONENT] = 
          "Component moved";
        break;
      case ComponentEvent.COMPONENT_RESIZED:
        display.evnt[Display.COMPONENT] = 
          "Component resized";
        break;
      case ComponentEvent.COMPONENT_HIDDEN:
        display.evnt[Display.COMPONENT] = 
          "Component hidden";
        break;
      case ComponentEvent.COMPONENT_SHOWN:
        display.evnt[Display.COMPONENT] = 
          "Component shown";
        break;
      default:
    }
    repaint();
    // Must always remember to call the "super"
    // version of whatever you override:
    super.processComponentEvent(e);
  }
  public void processFocusEvent(FocusEvent e) {
    switch(e.getID()) {
      case FocusEvent.FOCUS_GAINED:
        display.evnt[Display.FOCUS] = 
          "FOCUS gained";
        break;
      case FocusEvent.FOCUS_LOST:
        display.evnt[Display.FOCUS] = 
          "FOCUS lost";
        break;
      default:
    }
    repaint();
    super.processFocusEvent(e);
  }
  public void processKeyEvent(KeyEvent e) {
    switch(e.getID()) {
      case KeyEvent.KEY_PRESSED:
        display.evnt[Display.KEY] = 
          "KEY pressed: "; 
        break;
      case KeyEvent.KEY_RELEASED:
        display.evnt[Display.KEY] = 
          "KEY released: "; 
        break;
      case KeyEvent.KEY_TYPED:
        display.evnt[Display.KEY] = 
          "KEY typed: ";
        break;
      default:
    }
    int code = e.getKeyCode();
    display.evnt[Display.KEY] += 
      KeyEvent.getKeyText(code);
    repaint();
    super.processKeyEvent(e);
  }
  public void processMouseEvent(MouseEvent e) {
    switch(e.getID()) {
      case MouseEvent.MOUSE_CLICKED:
        requestFocus(); // Get FOCUS on click
        display.evnt[Display.MOUSE] = 
          "MOUSE clicked";
        break;
      case MouseEvent.MOUSE_PRESSED:
        display.evnt[Display.MOUSE] = 
          "MOUSE pressed";
        break;
      case MouseEvent.MOUSE_RELEASED:
        display.evnt[Display.MOUSE] = 
          "MOUSE released";
        break;
      case MouseEvent.MOUSE_ENTERED: 
        display.evnt[Display.MOUSE] = 
          "MOUSE entered";
        break;
      case MouseEvent.MOUSE_EXITED: 
        display.evnt[Display.MOUSE] = 
          "MOUSE exited";
        break;
      default:
    }
    display.evnt[Display.MOUSE] += 
      ", x = " + e.getX() + 
      ", y = " + e.getY();
    repaint();
    super.processMouseEvent(e);
  }
  public void
  processMouseMotionEvent(MouseEvent e) {
    switch(e.getID()) {
      case MouseEvent.MOUSE_DRAGGED:
        display.evnt[Display.MOUSE_MOVE] = 
          "MOUSE dragged";
        break;
      case MouseEvent.MOUSE_MOVED:
        display.evnt[Display.MOUSE_MOVE] = 
          "MOUSE moved";
        break;
      default:
    }
    display.evnt[Display.MOUSE_MOVE] += 
      ", x = " + e.getX() + 
      ", y = " + e.getY();
    repaint();
    super.processMouseMotionEvent(e);
  }
}

class MyButton extends Button {
  int clickCounter;
  String label = "";
  public MyButton() {
    enableEvents(AWTEvent.ACTION_EVENT_MASK);
  }
  public void paint(Graphics g) {
    g.setColor(Color.green);
    Dimension s = getSize();
    g.fillRect(0, 0, s.width, s.height);
    g.setColor(Color.black);
    g.drawRect(0, 0, s.width - 1, s.height - 1);
    drawLabel(g);
  }
  private void drawLabel(Graphics g) {
    FontMetrics fm = g.getFontMetrics();
    int width = fm.stringWidth(label);
    int height = fm.getHeight();
    int ascent = fm.getAscent();
    int leading = fm.getLeading();
    int horizMargin = 
      (getSize().width - width)/2;
    int verMargin = 
      (getSize().height - height)/2;
    g.setColor(Color.red);
    g.drawString(label, horizMargin, 
                 verMargin + ascent + leading);
  }
  public void processActionEvent(ActionEvent e) {
    clickCounter++;
    label = "click #" + clickCounter +
      " " + e.toString();
    repaint();
    super.processActionEvent(e);
  }
}
  
public class BadTechnique extends Frame {
  BadTechnique() {
    setLayout(new GridLayout(2,2));
    add(new EnabledPanel(1, Color.cyan));
    add(new EnabledPanel(2, Color.lightGray));
    add(new EnabledPanel(3, Color.yellow));
    // You can also do it for Windows:
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
  }
  public void processWindowEvent(WindowEvent e) {
    System.out.println(e);
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.out.println("Window Closing");
      System.exit(0);
    }
  }
  public static void main(String[] args) {
    Frame f = new BadTechnique();
    f.setTitle("Bad Technique");
    f.setSize(700,700);
    f.setVisible(true);
  }
} ///:~