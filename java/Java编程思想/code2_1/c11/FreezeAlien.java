//: c11:FreezeAlien.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Create a serialized output file.
import java.io.*;

public class FreezeAlien {
  public static void main(String[] args) 
      throws Exception {
    ObjectOutput out = 
      new ObjectOutputStream(
        new FileOutputStream("file.x"));
    Alien zorcon = new Alien();
    out.writeObject(zorcon); 
  }
} ///:~