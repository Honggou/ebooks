//: c15:jdbc:CIDSQL.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// SQL strings to create the tables for the CID.

public class CIDSQL {
  public static String[] sql = {
    // Create the MEMBERS table:
    "drop table MEMBERS",
    "create table MEMBERS " +
    "(MEM_ID INTEGER primary key, " +
    "MEM_UNAME VARCHAR(12) not null unique, "+
    "MEM_LNAME VARCHAR(40), " +
    "MEM_FNAME VARCHAR(20), " +
    "ADDRESS VARCHAR(40), " +
    "CITY VARCHAR(20), " +
    "STATE CHAR(4), " +
    "ZIP CHAR(5), " +
    "PHONE CHAR(12), " +
    "EMAIL VARCHAR(30))",
    "create unique index " +
    "LNAME_IDX on MEMBERS(MEM_LNAME)",
    // Create the EVENTS table
    "drop table EVENTS",
    "create table EVENTS " +
    "(EVT_ID INTEGER primary key, " +
    "EVT_TITLE VARCHAR(30) not null, " +
    "EVT_TYPE VARCHAR(20), " +
    "LOC_ID INTEGER, " +
    "PRICE DECIMAL, " +
    "DATETIME TIMESTAMP)",
    "create unique index " +
    "TITLE_IDX on EVENTS(EVT_TITLE)",
    // Create the EVTMEMS table
    "drop table EVTMEMS",
    "create table EVTMEMS " +
    "(MEM_ID INTEGER not null, " +
    "EVT_ID INTEGER not null, " +
    "MEM_ORD INTEGER)",
    "create unique index " +
    "EVTMEM_IDX on EVTMEMS(MEM_ID, EVT_ID)",
    // Create the LOCATIONS table
    "drop table LOCATIONS",
    "create table LOCATIONS " +
    "(LOC_ID INTEGER primary key, " +
    "LOC_NAME VARCHAR(30) not null, " +
    "CONTACT VARCHAR(50), " +
    "ADDRESS VARCHAR(40), " +
    "CITY VARCHAR(20), " +
    "STATE VARCHAR(4), " +
    "ZIP VARCHAR(5), " +
    "PHONE CHAR(12), " +
    "DIRECTIONS VARCHAR(4096))",
    "create unique index " +
    "NAME_IDX on LOCATIONS(LOC_NAME)",
  };
} ///:~