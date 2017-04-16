//: VLookup.java
// GUI version of Lookup.java
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class VLookup extends Applet {
  String dbUrl = "jdbc:odbc:people";
  String user = "";
  String password = "";
  Statement s;
  TextField searchFor = new TextField(20);
  Label completion = 
    new Label("                        ");
  TextArea results = new TextArea(40, 20);
  public void init() {
    searchFor.addTextListener(new SearchForL());
    Panel p = new Panel();
    p.add(new Label("Last name to search for:"));
    p.add(searchFor);
    p.add(completion);
    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(results, BorderLayout.CENTER);
    try {
      // Load the driver (registers itself)
      Class.forName(
        "sun.jdbc.odbc.JdbcOdbcDriver");
      Connection c = DriverManager.getConnection(
        dbUrl, user, password);
      s = c.createStatement();
    } catch(Exception e) {
      results.setText(e.getMessage());
    }
  }
  class SearchForL implements TextListener {
    public void textValueChanged(TextEvent te) {
      ResultSet r;
      if(searchFor.getText().length() == 0) {
        completion.setText("");
        results.setText("");
        return;
      }
      try {
        // Name completion:
        r = s.executeQuery(
          "SELECT LAST FROM people.csv people " +
          "WHERE (LAST Like '" +
          searchFor.getText()  + 
          "%') ORDER BY LAST");
        if(r.next()) 
          completion.setText(
            r.getString("last"));
        r = s.executeQuery(
          "SELECT FIRST, LAST, EMAIL " +
          "FROM people.csv people " +
          "WHERE (LAST='" + 
          completion.getText() +
          "') AND (EMAIL Is Not Null) " +
          "ORDER BY FIRST");
      } catch(Exception e) {
        results.setText(
          searchFor.getText() + "\n");
        results.append(e.getMessage());
        return; 
      }
      results.setText("");
      try {
        while(r.next()) {
          results.append(
            r.getString("Last") + ", " 
            + r.getString("fIRST") + 
            ": " + r.getString("EMAIL") + "\n");
        }
      } catch(Exception e) {
        results.setText(e.getMessage());
      }
    }
  }
  public static void main(String[] args) {
    VLookup applet = new VLookup();
    Frame aFrame = new Frame("Email lookup");
    aFrame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    aFrame.add(applet, BorderLayout.CENTER);
    aFrame.setSize(500,200);
    applet.init();
    applet.start();
    aFrame.setVisible(true);
  }
} ///:~