//: c15:corba:RemoteTimeServer.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
import remotetime.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;
import java.text.*;

// Server object implementation
class ExactTimeServer extends _ExactTimeImplBase {
  public String getTime(){
    return DateFormat.
        getTimeInstance(DateFormat.FULL).
          format(new Date(
              System.currentTimeMillis()));
  }
}

// Remote application implementation
public class RemoteTimeServer {
  // Throw exceptions to console:
  public static void main(String[] args) 
  throws Exception {
    // ORB creation and initialization:
    ORB orb = ORB.init(args, null);
    // Create the server object and register it:
    ExactTimeServer timeServerObjRef = 
      new ExactTimeServer();
    orb.connect(timeServerObjRef);
    // Get the root naming context:
    org.omg.CORBA.Object objRef = 
      orb.resolve_initial_references(
        "NameService");
    NamingContext ncRef = 
      NamingContextHelper.narrow(objRef);
    // Assign a string name to the 
    // object reference (binding):
    NameComponent nc = 
      new NameComponent("ExactTime", "");
    NameComponent[] path = { nc };
    ncRef.rebind(path, timeServerObjRef);
    // Wait for client requests:
    java.lang.Object sync =
      new java.lang.Object();
    synchronized(sync){
      sync.wait();
    }
  }
} ///:~