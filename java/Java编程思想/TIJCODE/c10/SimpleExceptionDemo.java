//: c10:SimpleExceptionDemo.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Inheriting your own exceptions.
class SimpleException extends Exception {} 

public class SimpleExceptionDemo {
  public void f() throws SimpleException {
    System.out.println(
      "Throwing SimpleException from f()");
    throw new SimpleException ();
  }
  public static void main(String[] args) {
    SimpleExceptionDemo sed = 
      new SimpleExceptionDemo();
    try {
      sed.f();
    } catch(SimpleException e) {
      System.err.println("Caught it!");
    }
  }
} ///:~