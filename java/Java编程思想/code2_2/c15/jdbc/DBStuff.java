//: c15:jdbc:DBStuff.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// A class to hold all our
// database specific code
// for the community
// interests database
import java.sql.*;

public class DBStuff {
  // All the database stuff
  // Specific for CloudScape.
  String dbDriver = 
      "COM.cloudscape.core.JDBCDriver";
  String dbURL =
      "jdbc:cloudscape:d:/docs/_work/JSapienDB";
  String user = "";
  String password = "";
  public String dropMemTbl = "drop table MEMBERS";
  public String createMemTbl = 
       "create table MEMBERS " +
       "(MEM_ID INTEGER primary key, " +
       "MEM_UNAME VARCHAR(12) not null unique, " +
       "MEM_LNAME VARCHAR(40), " +
       "MEM_FNAME VARCHAR(20), " +
       "ADDRESS VARCHAR(40), " +
       "CITY VARCHAR(20), " +
       "STATE CHAR(4), " +
       "ZIP CHAR(5), " +
       "PHONE CHAR(12), " +
       "EMAIL VARCHAR(30))";
  public String createMemIdx = 
       "create unique index " +
       "LNAME_IDX on MEMBERS(MEM_LNAME)";

  public String dropEvtTbl = "drop table EVENTS";
  public String createEvtTbl = 
       "create table EVENTS " +
       "(EVT_ID INTEGER primary key, " +
       "EVT_TITLE VARCHAR(30) not null, " +
       "EVT_TYPE VARCHAR(20), " +
       "LOC_ID INTEGER, " +
       "PRICE DECIMAL, " +
       "DATETIME TIMESTAMP)";
  public String createEvtIdx = 
       "create unique index " +
       "TITLE_IDX on EVENTS(EVT_TITLE)";

  public String dropEMTbl = "drop table EVTMEMS";
  public String createEMTbl = 
       "create table EVTMEMS " +
       "(MEM_ID INTEGER not null, " +
       "EVT_ID INTEGER not null, " +
       "MEM_ORD INTEGER)";
  public String createEMIdx = 
       "create unique index " +
       "EVTMEM_IDX on EVTMEMS(MEM_ID, EVT_ID)";

  public String dropLocTbl = "drop table LOCATIONS";
  public String createLocTbl = 
       "create table LOCATIONS " +
       "(LOC_ID INTEGER primary key, " +
       "LOC_NAME VARCHAR(30) not null, " +
       "CONTACT VARCHAR(50), " +
       "ADDRESS VARCHAR(40), " +
       "CITY VARCHAR(20), " +
       "STATE VARCHAR(4), " +
       "ZIP VARCHAR(5), " +
       "PHONE CHAR(12), " +
       "DIRECTIONS VARCHAR(4096))";
  public String createLocIdx = 
       "create unique index " +
       "NAME_IDX on LOCATIONS(LOC_NAME)";

  public String getDriver() {
    return dbDriver;
  }
  public String getDbURL() {
    return dbURL;
  }
  public String getUser() {
    return user;
  }
  public String getPassword() {
    return password;
  }
} ///:~