//: c10:ThrowOut.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
public class ThrowOut {
  public static void
  main(String[] args) throws Throwable {
    try {
      throw new Throwable(); 
    } catch(Exception e) {
      System.err.println("Caught in main()");
    }
  }
} ///:~