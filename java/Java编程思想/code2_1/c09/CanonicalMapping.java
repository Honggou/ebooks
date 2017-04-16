//: c09:CanonicalMapping.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates WeakHashMap.
import java.util.*;
import java.lang.ref.*;

class Key {
  String ident;
  public Key(String id) { ident = id; }
  public String toString() { return ident; }
  public int hashCode() { 
    return ident.hashCode();
  }
  public boolean equals(Object r) {
    return (r instanceof Key)
      && ident.equals(((Key)r).ident);
  }
  public void finalize() {
    System.out.println("Finalizing Key "+ ident);
  }
}

class Value {
  String ident;
  public Value(String id) { ident = id; }
  public String toString() { return ident; }
  public void finalize() {
    System.out.println("Finalizing Value "+ident);
  }
}

public class CanonicalMapping {
  public static void main(String[] args) {
    int size = 1000;
    // Or, choose size via the command line:
    if(args.length > 0)
      size = Integer.parseInt(args[0]);    
    Key[] keys = new Key[size];
    WeakHashMap whm = new WeakHashMap();
    for(int i = 0; i < size; i++) {
      Key k = new Key(Integer.toString(i));
      Value v = new Value(Integer.toString(i));
      if(i % 3 == 0)
        keys[i] = k; // Save as "real" references
      whm.put(k, v);
    }
    System.gc();
  }
} ///:~