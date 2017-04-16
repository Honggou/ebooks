//: c15:jdbc:CIDConnect.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Database connection information for
// the community interests database (CID).

public class CIDConnect {
  // All the information specific to CloudScape:
  public static String dbDriver = 
    "COM.cloudscape.core.JDBCDriver";
  public static String dbURL =
    "jdbc:cloudscape:d:/docs/_work/JSapienDB";
  public static String user = "";
  public static String password = "";
} ///:~