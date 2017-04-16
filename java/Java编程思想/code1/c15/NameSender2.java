//: NameSender2.java
// An applet that sends an email address
// via a CGI GET, using Java 1.02.
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;

public class NameSender2 extends Applet {
  final String CGIProgram = "Listmgr2.exe";
  Button send = new Button(
    "Add email address to mailing list");
  TextField name = new TextField(
    "type your name here", 40),
    email = new TextField(
    "type your email address here", 40);
  String str = new String();
  Label l = new Label(), l2 = new Label();
  int vcount = 0;
  public void init() {
    setLayout(new BorderLayout());
    Panel p = new Panel();
    p.setLayout(new GridLayout(3, 1));
    p.add(name);
    p.add(email);
    p.add(send);
    add("North", p);
    Panel labels = new Panel();
    labels.setLayout(new GridLayout(2, 1));
    labels.add(l);
    labels.add(l2);
    add("Center", labels);
    l.setText("Ready to send email address");
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(send)) {
      l2.setText("");
      // Check for errors in data:
      if(name.getText().trim()
         .indexOf(' ') == -1) {
        l.setText(
          "Please give first and last name");
        l2.setText("");
        return true;
      }
      str = email.getText().trim();
      if(str.indexOf(' ') != -1) {
        l.setText(
          "Spaces not allowed in email name");
        l2.setText("");
        return true;
      }
      if(str.indexOf(',') != -1) {
        l.setText(
          "Commas not allowed in email name");
        return true;
      }
      if(str.indexOf('@') == -1) {
        l.setText("Email name must include '@'");
        l2.setText("");
        return true;
      }
      if(str.indexOf('@') == 0) {
        l.setText(
          "Name must preceed '@' in email name");
        l2.setText("");
        return true;
      }
      String end = 
        str.substring(str.indexOf('@'));
      if(end.indexOf('.') == -1) {
        l.setText("Portion after '@' must " +
          "have an extension, such as '.com'");
        l2.setText("");
        return true;
      }
      // Build and encode the email data:
      String emailData = 
        "name=" + URLEncoder.encode(
          name.getText().trim()) +
        "&email=" + URLEncoder.encode(
          email.getText().trim().toLowerCase()) +
        "&submit=Submit";
      // Send the name using CGI's GET process:
      try {
        l.setText("Sending...");
        URL u = new URL(
          getDocumentBase(), "cgi-bin/" +
          CGIProgram + "?" + emailData);
        l.setText("Sent: " + email.getText());
        send.setLabel("Re-send");
        l2.setText(
          "Waiting for reply " + ++vcount);
        DataInputStream server =
          new DataInputStream(u.openStream());
        String line;
        while((line = server.readLine()) != null)
          l2.setText(line);
      } catch(MalformedURLException e) {
        l.setText("Bad URl");
      } catch(IOException e) {
        l.setText("IO Exception");
      } 
    }
    else return super.action(evt, arg);
    return true;
  }
} ///:~