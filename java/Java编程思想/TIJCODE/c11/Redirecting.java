//: c11:Redirecting.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates standard I/O redirection.
import java.io.*;

class Redirecting {
  // Throw exceptions to console:
  public static void main(String[] args) 
  throws IOException {
    BufferedInputStream in = 
      new BufferedInputStream(
        new FileInputStream(
          "Redirecting.java"));
    PrintStream out =
      new PrintStream(
        new BufferedOutputStream(
          new FileOutputStream("test.out")));
    System.setIn(in);
    System.setOut(out);
    System.setErr(out);

    BufferedReader br = 
      new BufferedReader(
        new InputStreamReader(System.in));
    String s;
    while((s = br.readLine()) != null)
      System.out.println(s);
    out.close(); // Remember this!
  }
} ///:~