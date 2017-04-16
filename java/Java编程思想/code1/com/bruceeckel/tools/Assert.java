//: Assert.java
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