//: c15:jdbc:CIDCreateTables.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Creates database tables for the
// community interests database.
import java.sql.*;

public class CIDCreateTables {
  public static void main(String[] args) 
  throws SQLException, ClassNotFoundException,
  IllegalAccessException {
    // Load the driver (registers itself)
    Class.forName(CIDConnect.dbDriver);
    Connection c = DriverManager.getConnection(
      CIDConnect.dbURL, CIDConnect.user, 
      CIDConnect.password);
    Statement s = c.createStatement();
    for(int i = 0; i < CIDSQL.sql.length; i++) {
      System.out.println(CIDSQL.sql[i]);
      try {
        s.executeUpdate(CIDSQL.sql[i]);
      } catch(SQLException sqlEx) {
        System.err.println(
          "Probably a 'drop table' failed");
      }
    }
    s.close();
    c.close();
  }
} ///:~