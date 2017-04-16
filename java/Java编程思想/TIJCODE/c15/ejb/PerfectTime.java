//: c15:ejb:PerfectTime.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
//# You must install the J2EE Java Enterprise 
//# Edition from java.sun.com and add j2ee.jar
//# to your CLASSPATH in order to compile
//# this file. See details at java.sun.com.
// Remote Interface of PerfectTimeBean
import java.rmi.*;
import javax.ejb.*;

public interface PerfectTime extends EJBObject {
  public long getPerfectTime() 
    throws RemoteException;
} ///:~