//: c09:Mouse.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Overriding toString().
public class Mouse {
  private int mouseNumber;
  Mouse(int i) { mouseNumber = i; }
  // Override Object.toString():
  public String toString() {
    return "This is Mouse #" + mouseNumber;
  }
  public int getNumber() {
    return mouseNumber;
  }
} ///:~