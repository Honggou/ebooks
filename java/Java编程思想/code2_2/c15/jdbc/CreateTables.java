//: c15:jdbc:CreateTables.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Creates database tables for 
// community interests database
import java.sql.*;

public class CreateTables {

  public static void main(String[] args) {
    DBStuff db = new DBStuff();
    try {
      // Load the driver (registers itself)
      Class.forName(db.getDriver());
    } catch(java.lang.ClassNotFoundException e) {
      System.err.print("ClassNotFoundException: ");
      e.printStackTrace();
    }
    try {
      Connection c = DriverManager.getConnection(
        db.getDbURL(),
        db.getUser(),
        db.getPassword());
      Statement s = c.createStatement();
      // SQL code:
      // Create the MEMBERS table
      try {
        s.executeUpdate( db.dropMemTbl );
      } catch(SQLException sqlEx) {
        String msg;
        msg = "Table MEMBERS not present. " +
              "Drop failed.";
        System.out.println(msg);
      } 
      s.executeUpdate( db.createMemTbl );
      s.executeUpdate( db.createMemIdx );
      
      // Create the EVENTS table
      try {
        s.executeUpdate( db.dropEvtTbl );
      } catch(SQLException sqlEx) {
        String msg;
        msg = "Table EVENTS not present. " +
              "Drop failed.";
        System.out.println(msg);
      } 
      s.executeUpdate( db.createEvtTbl );
      s.executeUpdate( db.createEvtIdx );
      
      // Create the EVTMEMS table
      try {
        s.executeUpdate( db.dropEMTbl );
      } catch(SQLException sqlEx) {
        String msg;
        msg = "Table EVTMEMS not present. " +
              "Drop failed.";
        System.out.println(msg);
      } 
      s.executeUpdate( db.createEMTbl );
      
      // Create the LOCATIONS table
      try {
        s.executeUpdate( db.dropLocTbl );
      } catch(SQLException sqlEx) {
        String msg;
        msg = "Table LOCATIONS not present. " +
              "Drop failed.";
        System.out.println(msg);
      } 
      s.executeUpdate( db.createLocTbl );
      s.executeUpdate( db.createLocIdx );
      
      s.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
} ///:~