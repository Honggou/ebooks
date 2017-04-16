//: c09:Enumerations.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Java 1.0/1.1 Vector and Enumeration.
import java.util.*;
import com.bruceeckel.util.*;

class Enumerations {
  public static void main(String[] args) {
    Vector v = new Vector();
    Collections2.fill(
      v, Collections2.countries, 100);
    Enumeration e = v.elements();
    while(e.hasMoreElements())
      System.out.println(e.nextElement());
    // Produce an Enumeration from a Collection:
    e = Collections.enumeration(new ArrayList());
  }
} ///:~