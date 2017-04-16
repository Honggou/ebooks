//: CrashJava.java
// One way to crash Java
import java.util.*;

public class CrashJava {
  public String toString() {
    return "CrashJava address: " + this + "\n";
  }
  public static void main(String[] args) {
    Vector v = new Vector();
    for(int i = 0; i < 10; i++)
      v.addElement(new CrashJava());
    System.out.println(v);
  }
} ///:~