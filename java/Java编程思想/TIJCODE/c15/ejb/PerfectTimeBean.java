//: c15:ejb:PerfectTimeBean.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Simple Stateless Session Bean 
// that returns current system time.
import java.rmi.*;
import javax.ejb.*;

public class PerfectTimeBean 
  implements SessionBean {
  private SessionContext sessionContext;
  //return current time
  public long getPerfectTime() { 
     return System.currentTimeMillis();
  }
  // EJB methods
  public void ejbCreate() 
  throws CreateException {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void 
  setSessionContext(SessionContext ctx) {
    sessionContext = ctx;
  }
}///:~