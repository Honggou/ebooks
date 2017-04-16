//: PrintDemo.java
// Printing with Java 1.1
import java.awt.*;
import java.awt.event.*;

public class PrintDemo extends Frame {
  Button 
    printText = new Button("Print Text"),
    printGraphics = new Button("Print Graphics");
  TextField ringNum = new TextField(3);
  Choice faces = new Choice();
  Graphics g = null;
  Plot plot = new Plot3(); // Try different plots
  Toolkit tk = Toolkit.getDefaultToolkit();
  public PrintDemo() {
    ringNum.setText("3");
    ringNum.addTextListener(new RingL());
    Panel p = new Panel();
    p.setLayout(new FlowLayout());
    printText.addActionListener(new TBL());
    p.add(printText);
    p.add(new Label("Font:"));
    p.add(faces);
    printGraphics.addActionListener(new GBL());
    p.add(printGraphics);
    p.add(new Label("Rings:"));
    p.add(ringNum);
    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(plot, BorderLayout.CENTER);
    String[] fontList = tk.getFontList();
    for(int i = 0; i < fontList.length; i++)
      faces.add(fontList[i]);
    faces.select("Serif");
  }
  class PrintData {
    public PrintJob pj;
    public int pageWidth, pageHeight;
    PrintData(String jobName) {
      pj = getToolkit().getPrintJob(
        PrintDemo.this, jobName, null);
      if(pj != null) {
        pageWidth = pj.getPageDimension().width;
        pageHeight= pj.getPageDimension().height;
        g = pj.getGraphics();
      }
    }
    void end() { pj.end(); }
  }
  class ChangeFont {
    private int stringHeight;
    ChangeFont(String face, int style,int point){
      if(g != null) {
        g.setFont(new Font(face, style, point));
        stringHeight = 
          g.getFontMetrics().getHeight();
      }
    }
    int stringWidth(String s) {
      return g.getFontMetrics().stringWidth(s);
    }
    int stringHeight() { return stringHeight; }
  }
  class TBL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      PrintData pd = 
        new PrintData("Print Text Test");
      // Null means print job canceled:
      if(pd == null) return;
      String s = "PrintDemo";
      ChangeFont cf = new ChangeFont(
        faces.getSelectedItem(), Font.ITALIC,72);
      g.drawString(s, 
        (pd.pageWidth - cf.stringWidth(s)) / 2,
        (pd.pageHeight - cf.stringHeight()) / 3);

      s = "A smaller point size";
      cf = new ChangeFont(
        faces.getSelectedItem(), Font.BOLD, 48);
      g.drawString(s, 
        (pd.pageWidth - cf.stringWidth(s)) / 2,
        (int)((pd.pageHeight - 
           cf.stringHeight())/1.5));
      g.dispose();
      pd.end();
    }
  }
  class GBL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      PrintData pd = 
        new PrintData("Print Graphics Test");
      if(pd == null) return;
      plot.print(g);
      g.dispose();
      pd.end();
    }
  }
  class RingL implements TextListener {
    public void textValueChanged(TextEvent e) {
      int i = 1;
      try {
        i = Integer.parseInt(ringNum.getText());
      } catch(NumberFormatException ex) {
        i = 1;
      }
      plot.rings = i;
      plot.repaint();
    }
  }
  public static void main(String[] args) {
    Frame pdemo = new PrintDemo();
    pdemo.setTitle("Print Demo");
    pdemo.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    pdemo.setSize(500, 500);
    pdemo.setVisible(true);
  }
}

class Plot extends Canvas {
  public int rings = 3;
}

class Plot1 extends Plot {
  // Default print() calls paint():
  public void paint(Graphics g) {
    int w = getSize().width;
    int h = getSize().height;
    int xc = w / 2;
    int yc = w / 2;
    int x = 0, y = 0;
    for(int i = 0; i < rings; i++) {
      if(x < xc && y < yc) {
        g.drawOval(x, y, w, h);
        x += 10; y += 10;
        w -= 20; h -= 20;
      }
    }
  }
} 

class Plot2 extends Plot {
  // To fit the picture to the page, you must
  // know whether you're printing or painting:
  public void paint(Graphics g) {
    int w, h;
    if(g instanceof PrintGraphics) {
      PrintJob pj = 
        ((PrintGraphics)g).getPrintJob();
      w = pj.getPageDimension().width;
      h = pj.getPageDimension().height;
    } 
    else {
      w = getSize().width;
      h = getSize().height;
    }
    int xc = w / 2;
    int yc = w / 2;
    int x = 0, y = 0;
    for(int i = 0; i < rings; i++) {
      if(x < xc && y < yc) {
        g.drawOval(x, y, w, h);
        x += 10; y += 10;
        w -= 20; h -= 20;
      }
    }
  }
} 

class Plot3 extends Plot {
  // Somewhat better. Separate 
  // printing from painting:
  public void print(Graphics g) {
    // Assume it's a PrintGraphics object:
    PrintJob pj = 
      ((PrintGraphics)g).getPrintJob();
    int w = pj.getPageDimension().width;
    int h = pj.getPageDimension().height;
    doGraphics(g, w, h);
  }
  public void paint(Graphics g) {
    int w = getSize().width;
    int h = getSize().height;
    doGraphics(g, w, h);
  }
  private void doGraphics(
      Graphics g, int w, int h) {
    int xc = w / 2;
    int yc = w / 2;
    int x = 0, y = 0;
    for(int i = 0; i < rings; i++) {
      if(x < xc && y < yc) {
        g.drawOval(x, y, w, h);
        x += 10; y += 10;
        w -= 20; h -= 20;
      }
    }
  }
} ///:~