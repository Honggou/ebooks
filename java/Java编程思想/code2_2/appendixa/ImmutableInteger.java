//: appendixa:ImmutableInteger.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// The Integer class cannot be changed.
import java.util.*;

public class ImmutableInteger {
  public static void main(String[] args) {
    ArrayList v = new ArrayList();
    for(int i = 0; i < 10; i++) 
      v.add(new Integer(i));
    // But how do you change the int
    // inside the Integer?
  }
} ///:~