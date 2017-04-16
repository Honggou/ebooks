//: FieldOBeasts.java
// Demonstration of complexity theory; simulates 
// herding behavior in animals. Adapted from
// a program by Larry O'Brien lobrien@msn.com
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

class Beast {
  int
    x, y,            // Screen position
    currentSpeed;    // Pixels per second
  float currentDirection;  // Radians
  Color color;      // Fill color
  FieldOBeasts field; // Where the Beast roams
  static final int GSIZE = 10; // Graphic size

  public Beast(FieldOBeasts f, int x, int y, 
      float cD, int cS, Color c) {
    field = f;
    this.x = x;
    this.y = y;
    currentDirection = cD;
    currentSpeed = cS;
    color = c;
  }
  public void step() {
    // You move based on those within your sight:
    Vector seen = field.beastListInSector(this);
    // If you're not out in front
    if(seen.size() > 0) {
      // Gather data on those you see
      int totalSpeed = 0;
      float totalBearing = 0.0f;
      float distanceToNearest = 100000.0f;
      Beast nearestBeast = 
        (Beast)seen.elementAt(0);
      Enumeration e = seen.elements();
      while(e.hasMoreElements()) {
        Beast aBeast = (Beast) e.nextElement();
        totalSpeed += aBeast.currentSpeed;
        float bearing = 
          aBeast.bearingFromPointAlongAxis(
            x, y, currentDirection);
        totalBearing += bearing;
        float distanceToBeast = 
          aBeast.distanceFromPoint(x, y);
        if(distanceToBeast < distanceToNearest) {
          nearestBeast = aBeast;
          distanceToNearest = distanceToBeast;
        }
      }
      // Rule 1: Match average speed of those 
      // in the list:
      currentSpeed = totalSpeed / seen.size();
      // Rule 2: Move towards the perceived
      // center of gravity of the herd:
      currentDirection = 
        totalBearing / seen.size();
      // Rule 3: Maintain a minimum distance 
      // from those around you:
      if(distanceToNearest <= 
         field.minimumDistance) {
        currentDirection = 
          nearestBeast.currentDirection;
        currentSpeed = nearestBeast.currentSpeed;
        if(currentSpeed > field.maxSpeed) {
          currentSpeed = field.maxSpeed;
        }
      }
    } 
    else {  // You are in front, so slow down
      currentSpeed = 
        (int)(currentSpeed * field.decayRate);
    }
    // Make the beast move:
    x += (int)(Math.cos(currentDirection) 
               * currentSpeed);
    y += (int)(Math.sin(currentDirection)
               * currentSpeed);
    x %= field.xExtent;
    y %= field.yExtent;
    if(x < 0)
      x += field.xExtent;
    if(y < 0)
      y += field.yExtent;
  }
  public float bearingFromPointAlongAxis (
      int originX, int originY, float axis) {
    // Returns bearing angle of the current Beast
    // in the world coordiante system
    try {
      double bearingInRadians = 
        Math.atan(
          (this.y - originY) / 
          (this.x - originX));
      // Inverse tan has two solutions, so you 
      // have to correct for other quarters:
      if(x < originX) {  
        if(y < originY) {
          bearingInRadians += - (float)Math.PI;
        } 
        else {
          bearingInRadians = 
            (float)Math.PI - bearingInRadians;
        }
      }
      // Just subtract the axis (in radians):
      return (float) (axis - bearingInRadians);
    } catch(ArithmeticException aE) {
      // Divide by 0 error possible on this
      if(x > originX) {
          return 0;
      } 
      else
        return (float) Math.PI;
    }
  }
  public float distanceFromPoint(int x1, int y1){
    return (float) Math.sqrt(
      Math.pow(x1 - x, 2) + 
      Math.pow(y1 - y, 2));
  }
  public Point position() { 
    return new Point(x, y);
  }
  // Beasts know how to draw themselves:
  public void draw(Graphics g) {
    g.setColor(color);
    int directionInDegrees = (int)(
      (currentDirection * 360) / (2 * Math.PI));
    int startAngle = directionInDegrees - 
      FieldOBeasts.halfFieldOfView;
    int endAngle = 90;
    g.fillArc(x, y, GSIZE, GSIZE, 
      startAngle, endAngle);
  }
}

public class FieldOBeasts extends Applet 
    implements Runnable {
  private Vector beasts;
  static float 
    fieldOfView = 
      (float) (Math.PI / 4), // In radians
    // Deceleration % per second:
    decayRate = 1.0f, 
    minimumDistance = 10f; // In pixels
  static int
    halfFieldOfView = (int)(
      (fieldOfView * 360) / (2 * Math.PI)),
    xExtent = 0,
    yExtent = 0,
    numBeasts = 50,
    maxSpeed = 20; // Pixels/second
  boolean uniqueColors = true;
  Thread thisThread;
  int delay = 25;
  public void init() {
    if (xExtent == 0 && yExtent == 0) {
      xExtent = Integer.parseInt(
        getParameter("xExtent"));
      yExtent = Integer.parseInt(
        getParameter("yExtent"));
    }
    beasts = 
      makeBeastVector(numBeasts, uniqueColors);
    // Now start the beasts a-rovin':
    thisThread = new Thread(this);
    thisThread.start();
  }
  public void run() {
    while(true) {
      for(int i = 0; i < beasts.size(); i++){
        Beast b = (Beast) beasts.elementAt(i);
        b.step();
      }
      try {
        thisThread.sleep(delay);
      } catch(InterruptedException ex){}
      repaint(); // Otherwise it won't update
    }
  }
  Vector makeBeastVector(
      int quantity, boolean uniqueColors) {
    Vector newBeasts = new Vector();
    Random generator = new Random();
    // Used only if uniqueColors is on:
    double cubeRootOfBeastNumber = 
      Math.pow((double)numBeasts, 1.0 / 3.0);
    float colorCubeStepSize = 
      (float) (1.0 / cubeRootOfBeastNumber);
    float r = 0.0f;
    float g = 0.0f;
    float b = 0.0f;
    for(int i = 0; i < quantity; i++) {
      int x = 
        (int) (generator.nextFloat() * xExtent);
      if(x > xExtent - Beast.GSIZE) 
        x -= Beast.GSIZE;
      int y = 
        (int) (generator.nextFloat() * yExtent);
      if(y > yExtent - Beast.GSIZE) 
        y -= Beast.GSIZE;
      float direction = (float)(
        generator.nextFloat() * 2 * Math.PI);
      int speed = (int)(
        generator.nextFloat() * (float)maxSpeed);
      if(uniqueColors) {
        r += colorCubeStepSize;
        if(r > 1.0) {
          r -= 1.0f;
          g += colorCubeStepSize;
          if( g > 1.0) {
            g -= 1.0f;
            b += colorCubeStepSize;
            if(b > 1.0) 
              b -= 1.0f;
          }
        }
      }
      newBeasts.addElement(
        new Beast(this, x, y, direction, speed, 
          new Color(r,g,b)));
    }
    return newBeasts;
  }
  public Vector beastListInSector(Beast viewer) {
    Vector output = new Vector();
    Enumeration e = beasts.elements();
    Beast aBeast = (Beast)beasts.elementAt(0);
    int counter = 0;
    while(e.hasMoreElements()) {
      aBeast = (Beast) e.nextElement();
      if(aBeast != viewer) {
        Point p = aBeast.position();
        Point v = viewer.position();
        float bearing = 
          aBeast.bearingFromPointAlongAxis(
            v.x, v.y, viewer.currentDirection);
        if(Math.abs(bearing) < fieldOfView / 2)
         output.addElement(aBeast);
      }
    }
    return output;
  }
  public void paint(Graphics g)  {
    Enumeration e = beasts.elements();
    while(e.hasMoreElements()) {
      ((Beast)e.nextElement()).draw(g);
    }
  }
  public static void main(String[] args)   {
    FieldOBeasts field = new FieldOBeasts();
    field.xExtent = 640;
    field.yExtent = 480;
    Frame frame = new Frame("Field 'O Beasts");
    // Optionally use a command-line argument
    // for the sleep time:
    if(args.length >= 1)
      field.delay = Integer.parseInt(args[0]);
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    frame.add(field, BorderLayout.CENTER);
    frame.setSize(640,480);
    field.init();
    field.start();
    frame.setVisible(true);
  }
} ///:~