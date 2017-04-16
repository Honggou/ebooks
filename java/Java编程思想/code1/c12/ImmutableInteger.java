//: ImmutableInteger.java
// The Integer class cannot be changed
import java.util.*;

public class ImmutableInteger {
  public static void main(String[] args) {
    Vector v = new Vector();
    for(int i = 0; i < 10; i++) 
      v.addElement(new Integer(i));
    // But how do you change the int
    // inside the Integer?
  }
} ///:~