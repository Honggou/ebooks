//: c11:GZIPcompress.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Uses GZIP compression to compress a file 
// whose name is passed on the command line.
import java.io.*;
import java.util.zip.*;

public class GZIPcompress {
  // Throw exceptions to console:
  public static void main(String[] args) 
  throws IOException {
    BufferedReader in =
      new BufferedReader(
        new FileReader(args[0]));
    BufferedOutputStream out =
      new BufferedOutputStream(
        new GZIPOutputStream(
          new FileOutputStream("test.gz")));
    System.out.println("Writing file");
    int c;
    while((c = in.read()) != -1)
      out.write(c);
    in.close();
    out.close();
    System.out.println("Reading file");
    BufferedReader in2 =
      new BufferedReader(
        new InputStreamReader(
          new GZIPInputStream(
            new FileInputStream("test.gz"))));
    String s;
    while((s = in2.readLine()) != null)
      System.out.println(s);
  }
} ///:~