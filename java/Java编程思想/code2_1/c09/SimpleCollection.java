//: c09:SimpleCollection.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// A simple example using Java 2 Collections.
import java.util.*;

public class SimpleCollection {
  public static void main(String[] args) {
    // Upcast because we just want to
    // work with Collection features
    Collection c = new ArrayList();

    for(int i = 0; i < 10; i++)
      c.add(Integer.toString(i));
    Iterator it = c.iterator();
    while(it.hasNext())
      System.out.println(it.next());
  }
} ///:~