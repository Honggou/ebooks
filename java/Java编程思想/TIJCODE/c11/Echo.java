//: c11:Echo.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// How to read from standard input.
import java.io.*;

public class Echo {
  public static void main(String[] args)
  throws IOException {
    BufferedReader in =
        new BufferedReader(
          new InputStreamReader(System.in));
    String s;
    while((s = in.readLine()).length() != 0)
      System.out.println(s);
    // An empty line terminates the program
  }
} ///:~