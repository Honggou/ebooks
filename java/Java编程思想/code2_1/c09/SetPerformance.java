//: c09:SetPerformance.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
import java.util.*;
import com.bruceeckel.util.*;

public class SetPerformance {
  private abstract static class Tester {
    String name;
    Tester(String name) { this.name = name; }
    abstract void test(Set s, int size, int reps);
  }
  private static Tester[] tests = {
    new Tester("add") { 
      void test(Set s, int size, int reps) {
        for(int i = 0; i < reps; i++) {
          s.clear();
          Collections2.fill(s, 
            Collections2.countries.reset(),size);
        }
      }
    },
    new Tester("contains") { 
      void test(Set s, int size, int reps) {
        for(int i = 0; i < reps; i++)
          for(int j = 0; j < size; j++)
            s.contains(Integer.toString(j));
      }
    },
    new Tester("iteration") { 
      void test(Set s, int size, int reps) {
        for(int i = 0; i < reps * 10; i++) {
          Iterator it = s.iterator();
          while(it.hasNext())
            it.next();
        }
      }
    },
  };
  public static void 
  test(Set s, int size, int reps) {
    System.out.println("Testing " + 
      s.getClass().getName() + " size " + size);
    Collections2.fill(s, 
      Collections2.countries.reset(), size);
    for(int i = 0; i < tests.length; i++) {
      System.out.print(tests[i].name);
      long t1 = System.currentTimeMillis();
      tests[i].test(s, size, reps);
      long t2 = System.currentTimeMillis();
      System.out.println(": " + 
        ((double)(t2 - t1)/(double)size));
    }
  }
  public static void main(String[] args) {
    int reps = 50000;
    // Or, choose the number of repetitions
    // via the command line:
    if(args.length > 0)
      reps = Integer.parseInt(args[0]);
    // Small:
    test(new TreeSet(), 10, reps);
    test(new HashSet(), 10, reps);
    // Medium:
    test(new TreeSet(), 100, reps);
    test(new HashSet(), 100, reps);
    // Large:
    test(new TreeSet(), 1000, reps);
    test(new HashSet(), 1000, reps);
  }
} ///:~