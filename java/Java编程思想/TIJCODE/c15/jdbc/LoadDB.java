//: c15:jdbc:LoadDB.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Loads and tests the database.
import java.sql.*;

class TestSet {
  Object[][] data = {
    { "MEMBERS", new Integer(1),
      "dbartlett", "Bartlett", "David",
      "123 Mockingbird Lane",
      "Gettysburg", "PA", "19312",
      "123.456.7890",  "bart@you.net" },
    { "MEMBERS", new Integer(2),
      "beckel", "Eckel", "Bruce",
      "123 Over Rainbow Lane",
      "Crested Butte", "CO", "81224",
      "123.456.7890", "beckel@you.net" },
    { "MEMBERS", new Integer(3),
      "rcastaneda", "Castaneda", "Robert",
      "123 Downunder Lane",
      "Sydney", "NSW", "12345",
      "123.456.7890", "rcastaneda@you.net" },
    { "LOCATIONS", new Integer(1),
      "Center for Arts",
      "Betty Wright", "123 Elk Ave.",
      "Crested Butte", "CO", "81224",
      "123.456.7890",
      "Go this way then that." },
    { "LOCATIONS", new Integer(2),
      "Witts End Conference Center",
      "John Wittig", "123 Music Drive",
      "Zoneville", "PA", "19123",
      "123.456.7890",
      "Go that way then this." },
    { "EVENTS", new Integer(1),
      "Project Management Myths",
      "Software Development",
      new Integer(1), new Float(2.50),
      "2000-07-17 19:30:00" },
    { "EVENTS", new Integer(2),
      "Life of the Crested Dog",
      "Archeology",
      new Integer(2), new Float(0.00),
      "2000-07-19 19:00:00" },
    // Match some people with events
    {  "EVTMEMS", 
      new Integer(1),  // Dave is going to
      new Integer(1),  // the Software event.
      new Integer(0) },
    { "EVTMEMS", 
      new Integer(2),  // Bruce is going to
      new Integer(2),  // the Archeology event.
      new Integer(0) },
    { "EVTMEMS", 
      new Integer(3),  // Robert is going to
      new Integer(1),  // the Software event.
      new Integer(1) },
    { "EVTMEMS", 
      new Integer(3), // ... and 
      new Integer(2), // the Archeology event.
      new Integer(1) },
  };
  // Use the default data set:
  public TestSet() {}
  // Use a different data set:
  public TestSet(Object[][] dat) { data = dat; }
}

public class LoadDB {
  Statement statement;
  Connection connection;
  TestSet tset;
  public LoadDB(TestSet t) throws SQLException {
    tset = t;
    try {
      // Load the driver (registers itself)
      Class.forName(CIDConnect.dbDriver);
    } catch(java.lang.ClassNotFoundException e) {
      e.printStackTrace(System.err);
    }
    connection = DriverManager.getConnection(
      CIDConnect.dbURL, CIDConnect.user, 
      CIDConnect.password);
    statement = connection.createStatement();
  }
  public void cleanup() throws SQLException {
    statement.close();
    connection.close();
  }
  public void executeInsert(Object[] data) {
    String sql = "insert into " 
      + data[0] + " values(";
    for(int i = 1; i < data.length; i++) {
      if(data[i] instanceof String)
        sql += "'" + data[i] + "'";
      else
        sql += data[i];
      if(i < data.length - 1)
        sql += ", ";
    }
    sql += ')';
    System.out.println(sql);
    try {
      statement.executeUpdate(sql);
    } catch(SQLException sqlEx) {
      System.err.println("Insert failed.");
      while (sqlEx != null) {
        System.err.println(sqlEx.toString());
        sqlEx = sqlEx.getNextException();
      }
    } 
  }
  public void load() {
    for(int i = 0; i< tset.data.length; i++)
      executeInsert(tset.data[i]);
  }
  // Throw exceptions out to console:
  public static void main(String[] args) 
  throws SQLException {
    LoadDB db = new LoadDB(new TestSet());
    db.load();
    try {
      // Get a ResultSet from the loaded database:
      ResultSet rs = db.statement.executeQuery(
        "select " +
        "e.EVT_TITLE, m.MEM_LNAME, m.MEM_FNAME "+
        "from EVENTS e, MEMBERS m, EVTMEMS em " +
        "where em.EVT_ID = 2 " +
        "and e.EVT_ID = em.EVT_ID " +
        "and m.MEM_ID = em.MEM_ID");
      while (rs.next())
        System.out.println(
          rs.getString(1) + "  " + 
          rs.getString(2) + ", " +
          rs.getString(3));
    } finally {
      db.cleanup();
    }
  }
} ///:~