//: c11:xfiles:ThawAlien.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Try to recover a serialized file without the 
// class of object that's stored in that file.
import java.io.*;

public class ThawAlien {
  public static void main(String[] args) 
  throws IOException, ClassNotFoundException {
    ObjectInputStream in =
      new ObjectInputStream(
        new FileInputStream("X.file"));
    Object mystery = in.readObject();
    System.out.println(mystery.getClass());
  }
} ///:~