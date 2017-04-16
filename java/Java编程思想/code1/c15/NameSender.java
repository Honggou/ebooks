//: NameSender.java
// An applet that sends an email address
// as a datagram, using Java 1.02.
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;

public class NameSender extends Applet 
    implements Runnable {
  private Thread pl = null;
  private Button send = new Button(
    "Add email address to mailing list");
  private TextField t = new TextField(
    "type your email address here", 40);
  private String str = new String();
  private Label 
    l = new Label(), l2 = new Label();
  private DatagramSocket s; 
  private InetAddress hostAddress;
  private byte[] buf = 
    new byte[NameCollector.BUFFER_SIZE];
  private DatagramPacket dp =
    new DatagramPacket(buf, buf.length);
  private int vcount = 0;
  public void init() {
    setLayout(new BorderLayout());
    Panel p = new Panel();
    p.setLayout(new GridLayout(2, 1));
    p.add(t);
    p.add(send);
    add("North", p);
    Panel labels = new Panel();
    labels.setLayout(new GridLayout(2, 1));
    labels.add(l);
    labels.add(l2);
    add("Center", labels);
    try {
      // Auto-assign port number:
      s = new DatagramSocket();
      hostAddress = InetAddress.getByName(
        getCodeBase().getHost());
    } catch(UnknownHostException e) {
      l.setText("Cannot find host");
    } catch(SocketException e) {
      l.setText("Can't open socket");
    } 
    l.setText("Ready to send your email address");
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(send)) {
      if(pl != null) {
        // pl.stop(); Deprecated in Java 1.2
        Thread remove = pl;
        pl = null;
        remove.interrupt();
      }
      l2.setText("");
      // Check for errors in email name:
      str = t.getText().toLowerCase().trim();
      if(str.indexOf(' ') != -1) {
        l.setText("Spaces not allowed in name");
        return true;
      }
      if(str.indexOf(',') != -1) {
        l.setText("Commas not allowed in name");
        return true;
      }
      if(str.indexOf('@') == -1) {
        l.setText("Name must include '@'");
        l2.setText("");
        return true;
      }
      if(str.indexOf('@') == 0) {
        l.setText("Name must preceed '@'");
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
      // Everything's OK, so send the name. Get a
      // fresh buffer, so it's zeroed. For some 
      // reason you must use a fixed size rather
      // than calculating the size dynamically:
      byte[] sbuf = 
        new byte[NameCollector.BUFFER_SIZE];
      str.getBytes(0, str.length(), sbuf, 0);
      DatagramPacket toSend =
        new DatagramPacket(
          sbuf, 100, hostAddress,
          NameCollector.COLLECTOR_PORT);
      try {
        s.send(toSend);
      } catch(Exception e) {
        l.setText("Couldn't send datagram");
        return true;
      }
      l.setText("Sent: " + str);
      send.setLabel("Re-send");
      pl = new Thread(this);
      pl.start();
      l2.setText(
        "Waiting for verification " + ++vcount);
    }
    else return super.action(evt, arg);
    return true;
  }
  // The thread portion of the applet watches for
  // the reply to come back from the server:
  public void run() {
    try {
      s.receive(dp);
    } catch(Exception e) {
      l2.setText("Couldn't receive datagram");
      return;
    }
    l2.setText(new String(dp.getData(),
      0, 0, dp.getLength()));
  }
} ///:~