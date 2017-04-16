//: c15:rmi:DisplayPerfectTime.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Uses remote object PerfectTime.
package c15.rmi;
import java.rmi.*;
import java.rmi.registry.*;

public class DisplayPerfectTime {
  public static void main(String[] args) 
  throws Exception {
    System.setSecurityManager(
      new RMISecurityManager());
    PerfectTimeI t = 
      (PerfectTimeI)Naming.lookup(
        "//peppy:2005/PerfectTime");
    for(int i = 0; i < 10; i++)
      System.out.println("Perfect time = " +
        t.getPerfectTime());
  }
} ///:~