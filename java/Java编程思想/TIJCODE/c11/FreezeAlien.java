//: c11:FreezeAlien.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Create a serialized output file.
import java.io.*;

public class FreezeAlien {
  // Throw exceptions to console:
  public static void main(String[] args) 
  throws IOException {
    ObjectOutput out = 
      new ObjectOutputStream(
        new FileOutputStream("X.file"));
    Alien zorcon = new Alien();
    out.writeObject(zorcon); 
  }
} ///:~