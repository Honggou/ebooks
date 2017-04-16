//: com:bruceeckel:tools:Assert.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Turning off the assertion output 
// so you can ship the program.
package com.bruceeckel.tools;

public class Assert {
  public final static void is_true(boolean exp){}
  public final static void is_false(boolean exp){}
  public final static void 
  is_true(boolean exp, String msg) {}
  public final static void 
  is_false(boolean exp, String msg) {}
} ///:~