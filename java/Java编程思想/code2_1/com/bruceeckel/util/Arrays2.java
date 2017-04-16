//: com:bruceeckel:util:Arrays2.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// A supplement to java.util.Arrays, to provide
// additional useful functionality when working
// with arrays. Allows any array to be printed,
// and to be filled via a user-defined 
// "generator" object.
package com.bruceeckel.util;
import java.util.*;

public class Arrays2 {
  private static void
  start(int from, int to, int length) {
    if(from != 0 || to != length)
      System.out.print("["+ from +":"+ to +"] ");
    System.out.print("(");
  }
  private static void end() {
    System.out.println(")");
  }
  public static void print(Object[] a) {
    print(a, 0, a.length);
  }
  public static void 
  print(String msg, Object[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(Object[] a, int from, int to){
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to -1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(boolean[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, boolean[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(boolean[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to -1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(byte[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, byte[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(byte[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to -1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(char[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, char[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(char[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to -1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(short[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, short[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(short[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to - 1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(int[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, int[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(int[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to - 1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(long[] a) {
    print(a, 0, a.length);
  }
  public static void 
  print(String msg, long[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(long[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to - 1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(float[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, float[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(float[] a, int from, int to) {
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to - 1)
        System.out.print(", ");
    }
    end();
  }
  public static void print(double[] a) {
      print(a, 0, a.length);
  }
  public static void 
  print(String msg, double[] a) {
    System.out.print(msg + " ");
    print(a, 0, a.length);
  }
  public static void 
  print(double[] a, int from, int to){
    start(from, to, a.length);
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if(i < to - 1)
        System.out.print(", ");
    }
    end();
  }
  // Fill an array using a generator:
  public static void 
  fill(Object[] a, Generator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(Object[] a, int from, int to, 
       Generator gen){
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(boolean[] a, BooleanGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(boolean[] a, int from, int to,
       BooleanGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(byte[] a, ByteGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(byte[] a, int from, int to, 
       ByteGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(char[] a, CharGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(char[] a, int from, int to, 
       CharGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(short[] a, ShortGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(short[] a, int from, int to, 
       ShortGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(int[] a, IntGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(int[] a, int from, int to, 
       IntGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(long[] a, LongGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(long[] a, int from, int to, 
       LongGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(float[] a, FloatGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(float[] a, int from, int to, 
       FloatGenerator gen) {
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  public static void 
  fill(double[] a, DoubleGenerator gen) {
      fill(a, 0, a.length, gen);
  }
  public static void 
  fill(double[] a, int from, int to,
       DoubleGenerator gen){
    for(int i = from; i < to; i++)
      a[i] = gen.next();
  }
  private static Random r = new Random();
  public static class RandBooleanGenerator 
  implements BooleanGenerator {
    public boolean next() { 
      return r.nextBoolean();
    }
  }
  public static class RandByteGenerator 
  implements ByteGenerator {
    public byte next() { 
      return (byte)r.nextInt();
    }
  }
  static String ssource = 
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
    "abcdefghijklmnopqrstuvwxyz";
  static char[] src = ssource.toCharArray();
  public static class RandCharGenerator 
  implements CharGenerator {
    public char next() {
      int pos = Math.abs(r.nextInt());
      return src[pos % src.length];
    }
  }
  public static class RandStringGenerator
  implements Generator {
    private int len;
    private RandCharGenerator cg = 
      new RandCharGenerator();
    public RandStringGenerator(int length) {
      len = length;
    }
    public Object next() {
      char[] buf = new char[len];
      for(int i = 0; i < len; i++)
        buf[i] = cg.next();
      return new String(buf);
    }
  }
  public static class RandShortGenerator 
  implements ShortGenerator {
    public short next() { 
      return (short)r.nextInt();
    }
  }
  public static class RandIntGenerator 
  implements IntGenerator {
    private int mod = 10000;
    public RandIntGenerator() {}
    public RandIntGenerator(int modulo) {
      mod = modulo;
    }
    public int next() { 
      return r.nextInt() % mod; 
    }
  }
  public static class RandLongGenerator 
  implements LongGenerator {
    public long next() { return r.nextLong(); }
  }
  public static class RandFloatGenerator 
  implements FloatGenerator {
    public float next() { return r.nextFloat(); }
  }
  public static class RandDoubleGenerator 
  implements DoubleGenerator {
    public double next() {return r.nextDouble();}
  }
} ///:~