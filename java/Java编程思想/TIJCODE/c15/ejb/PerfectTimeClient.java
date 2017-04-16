//: c15:ejb:PerfectTimeClient.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Client program for PerfectTimeBean

public class PerfectTimeClient {
public static void main(String[] args) 
throws Exception {
  // Get a JNDI context using 
  // the JNDI Naming service:
  javax.naming.Context context = 
    new javax.naming.InitialContext();
  // Look up the home interface in the 
  // JNDI Naming service:
  Object ref = context.lookup("perfectTime");
  // Cast the remote object to the home interface:
  PerfectTimeHome home = (PerfectTimeHome)
    javax.rmi.PortableRemoteObject.narrow(
      ref, PerfectTimeHome.class);
  // Create a remote object from the home interface:
  PerfectTime pt = home.create();
  // Invoke  getPerfectTime()
  System.out.println(
    "Perfect Time EJB invoked, time is: " + 
    pt.getPerfectTime() );
  }
} ///:~