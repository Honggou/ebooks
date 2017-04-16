//: POSTtest.java
// An applet that sends its data via a CGI POST
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;

public class POSTtest extends Applet {
  final static int SIZE = 10;
  Button submit = new Button("Submit");
  TextField[] t = new TextField[SIZE];
  String query = "";
  Label l = new Label();
  TextArea ta = new TextArea(15, 60);
  public void init() {
    Panel p = new Panel();
    p.setLayout(new GridLayout(t.length + 2, 2));
    for(int i = 0; i < t.length; i++) {
      p.add(new Label(
        "Field " + i + "  ", Label.RIGHT));
      p.add(t[i] = new TextField(30));
    }
    p.add(l);
    p.add(submit);
    add("North", p);
    add("South", ta);
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(submit)) {
      query = "";
      ta.setText("");
      // Encode the query from the field data:
      for(int i = 0; i < t.length; i++)
         query += "Field" + i + "=" +
           URLEncoder.encode(
             t[i].getText().trim()) +
           "&";
      query += "submit=Submit";
      // Send the name using CGI's POST process:
      try {
        URL u = new URL(
          getDocumentBase(), "cgi-bin/POSTtest");
        URLConnection urlc = u.openConnection();
        urlc.setDoOutput(true);
        urlc.setDoInput(true);
        urlc.setAllowUserInteraction(false);
        DataOutputStream server = 
          new DataOutputStream(
            urlc.getOutputStream());
        // Send the data
        server.writeBytes(query);
        server.close();
        // Read and display the response. You
        // cannot use 
        // getAppletContext().showDocument(u);
        // to display the results as a Web page!
        DataInputStream in = 
          new DataInputStream(
            urlc.getInputStream());
        String s;
        while((s = in.readLine()) != null) {
          ta.appendText(s + "\n");
        }
        in.close();
      }
      catch (Exception e) {
        l.setText(e.toString());
      }
    }
    else return super.action(evt, arg);
    return true;
  }
} ///:~