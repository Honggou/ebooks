//: c11:ChangeSystemOut.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Turn System.out into a PrintWriter.
import java.io.*;

public class ChangeSystemOut {
  public static void main(String[] args) {
    PrintWriter out = 
      new PrintWriter(System.out, true);
    out.println("Hello, world");
  }
} ///:~