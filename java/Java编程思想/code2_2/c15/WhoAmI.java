//: c15:WhoAmI.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Finds out your network address when
// you're connected to the Internet.
import java.net.*;

public class WhoAmI {
  public static void main(String[] args) 
      throws Exception {
    if(args.length != 1) {
      System.err.println(
        "Usage: WhoAmI MachineName");
      System.exit(1);
    }
    InetAddress a = 
      InetAddress.getByName(args[0]);
    System.out.println(a);
  }
} ///:~