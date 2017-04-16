//: GoodTechnique.java
// Your first choice when overriding components
// should be to install listeners. The code is
// much safer, more modular and maintainable.
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
    addComponentListener(new CL());
    addFocusListener(new FL());
    addKeyListener(new KL());
    addMouseListener(new ML());
    addMouseMotionListener(new MML());
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
  // Don't need to enable anything for this:
  public void processEvent(AWTEvent e) {
    display.evnt[Display.EVENT]= e.toString();
    repaint();
    super.processEvent(e);
  }
  class CL implements ComponentListener {
    public void componentMoved(ComponentEvent e){
      display.evnt[Display.COMPONENT] = 
        "Component moved";
      repaint();
    }
    public void 
    componentResized(ComponentEvent e) {
      display.evnt[Display.COMPONENT] = 
        "Component resized";
      repaint();
    }
    public void 
    componentHidden(ComponentEvent e) {
      display.evnt[Display.COMPONENT] = 
        "Component hidden";
      repaint();
    }
    public void componentShown(ComponentEvent e){
      display.evnt[Display.COMPONENT] = 
        "Component shown";
      repaint();
    }
  }
  class FL implements FocusListener {
    public void focusGained(FocusEvent e) {
      display.evnt[Display.FOCUS] = 
        "FOCUS gained";
      repaint();
    }
    public void focusLost(FocusEvent e) {
      display.evnt[Display.FOCUS] = 
        "FOCUS lost";
      repaint();
    }
  }
  class KL implements KeyListener {
    public void keyPressed(KeyEvent e) {
      display.evnt[Display.KEY] = 
        "KEY pressed: "; 
      showCode(e);
    }
    public void keyReleased(KeyEvent e) {
      display.evnt[Display.KEY] = 
        "KEY released: "; 
      showCode(e);
    }
    public void keyTyped(KeyEvent e) {
      display.evnt[Display.KEY] = 
        "KEY typed: ";
      showCode(e);
    }
    void showCode(KeyEvent e) {
      int code = e.getKeyCode();
      display.evnt[Display.KEY] += 
        KeyEvent.getKeyText(code);
      repaint();
    }
  }
  class ML implements MouseListener {
    public void mouseClicked(MouseEvent e) {
      requestFocus(); // Get FOCUS on click
      display.evnt[Display.MOUSE] = 
        "MOUSE clicked";
      showMouse(e);
    }
    public void mousePressed(MouseEvent e) {
      display.evnt[Display.MOUSE] = 
        "MOUSE pressed";
      showMouse(e);
    }
    public void mouseReleased(MouseEvent e) {
      display.evnt[Display.MOUSE] = 
        "MOUSE released";
      showMouse(e);
    }
    public void mouseEntered(MouseEvent e) { 
      display.evnt[Display.MOUSE] = 
        "MOUSE entered";
      showMouse(e);
    }
    public void mouseExited(MouseEvent e) { 
      display.evnt[Display.MOUSE] = 
        "MOUSE exited";
      showMouse(e);
    }
    void showMouse(MouseEvent e) {
      display.evnt[Display.MOUSE] += 
        ", x = " + e.getX() + 
        ", y = " + e.getY();
      repaint();
    }
  }
  class MML implements MouseMotionListener {
    public void mouseDragged(MouseEvent e) {
      display.evnt[Display.MOUSE_MOVE] = 
        "MOUSE dragged";
      showMouse(e);
    }
    public void mouseMoved(MouseEvent e) {
      display.evnt[Display.MOUSE_MOVE] = 
        "MOUSE moved";
      showMouse(e);
    }
    void showMouse(MouseEvent e) {
      display.evnt[Display.MOUSE_MOVE] += 
        ", x = " + e.getX() + 
        ", y = " + e.getY();
      repaint();
    }
  }
}

class MyButton extends Button {
  int clickCounter;
  String label = "";
  public MyButton() {
    addActionListener(new AL());
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
  class AL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      clickCounter++;
      label = "click #" + clickCounter +
        " " + e.toString();
      repaint();
    }
  }
}
  
public class GoodTechnique extends Frame {
  GoodTechnique() {
    setLayout(new GridLayout(2,2));
    add(new EnabledPanel(1, Color.cyan));
    add(new EnabledPanel(2, Color.lightGray));
    add(new EnabledPanel(3, Color.yellow));
  }
  public static void main(String[] args) {
    Frame f = new GoodTechnique();
    f.setTitle("Good Technique");
    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          System.out.println(e);
          System.out.println("Window Closing");
          System.exit(0);
        }
      });
    f.setSize(700,700);
    f.setVisible(true);
  }
} ///:~