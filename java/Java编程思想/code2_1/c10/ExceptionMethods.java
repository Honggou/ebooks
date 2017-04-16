//: c10:ExceptionMethods.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrating the Exception Methods.

public class ExceptionMethods {
  public static void main(String[] args) {
    try {
      throw new Exception("Here's my Exception");
    } catch(Exception e) {
      System.out.println("Caught Exception");
      System.out.println(
        "e.getMessage(): " + e.getMessage());
      System.out.println(
        "e.getLocalizedMessage(): " +
         e.getLocalizedMessage());
      System.out.println("e.toString(): " + e);
      System.out.println("e.printStackTrace():");
      e.printStackTrace();
      e.printStackTrace(System.err);
    }
  }
} ///:~