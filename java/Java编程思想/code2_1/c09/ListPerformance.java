//: c09:ListPerformance.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Demonstrates performance differences in Lists.
import java.util.*;
import com.bruceeckel.util.*;

public class ListPerformance {
  private abstract static class Tester {
    String name;
    int size; // Test quantity
    Tester(String name, int size) { 
      this.name = name;
      this.size = size;
    }
    abstract void test(List a, int reps);
  }
  private static Tester[] tests = {
    new Tester("get", 300) { 
      void test(List a, int reps) {
        for(int i = 0; i < reps; i++) {
          for(int j = 0; j < a.size(); j++)
            a.get(j);
        }
      }
    },
    new Tester("iteration", 300) { 
      void test(List a, int reps) {
        for(int i = 0; i < reps; i++) {
          Iterator it = a.iterator();
          while(it.hasNext())
            it.next();
        }
      }
    },
    new Tester("insert", 5000) { 
      void test(List a, int reps) {
        int half = a.size()/2;
        String s = "test";
        ListIterator it = a.listIterator(half);
        for(int i = 0; i < size * 10; i++)
          it.add(s);
      }
    },
    new Tester("remove", 5000) { 
      void test(List a, int reps) {
        ListIterator it = a.listIterator(3);
        while(it.hasNext()) {
          it.next();
          it.remove();
        }
      }
    },
  };
  public static void test(List a, int reps) {
    // A trick to print out the class name:
    System.out.println("Testing " + 
      a.getClass().getName());
    for(int i = 0; i < tests.length; i++) {
      Collections2.fill(a, 
        Collections2.countries.reset(),
        tests[i].size);
      System.out.print(tests[i].name);
      long t1 = System.currentTimeMillis();
      tests[i].test(a, reps);
      long t2 = System.currentTimeMillis();
      System.out.println(": " + (t2 - t1));
    }
  }
  public static void testArray(int reps) {
    System.out.println("Testing array as List");
    // Can only do first two tests on an array:
    for(int i = 0; i < 2; i++) {
      String[] sa = new String[tests[i].size];
      Arrays2.fill(sa, 
        Collections2.countries.reset());
      List a = Arrays.asList(sa);
      System.out.print(tests[i].name);
      long t1 = System.currentTimeMillis();
      tests[i].test(a, reps);
      long t2 = System.currentTimeMillis();
      System.out.println(": " + (t2 - t1));
    }
  }
  public static void main(String[] args) {
    int reps = 50000;
    // Or, choose the number of repetitions
    // via the command line:
    if(args.length > 0)
      reps = Integer.parseInt(args[0]);
    System.out.println(reps + " repetitions");
    testArray(reps);
    test(new ArrayList(), reps);
    test(new LinkedList(), reps);
    test(new Vector(), reps);
  }
} ///:~