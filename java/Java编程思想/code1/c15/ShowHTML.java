//: ShowHTML.java
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;

public class ShowHTML extends Applet {
  static final String CGIProgram = "MyCGIProgram";
  Button send = new Button("Go");
  Label l = new Label();
  public void init() {
    add(send);
    add(l);
  }
  public boolean action (Event evt, Object arg) {
    if(evt.target.equals(send)) {
      try {
        // This could be an HTML page instead of
        // a CGI program. Notice that this CGI 
        // program doesn't use arguments, but 
        // you can add them in the usual way.
        URL u = new URL(
          getDocumentBase(), 
          "cgi-bin/" + CGIProgram);
        // Display the output of the URL using
        // the Web browser, as an ordinary page:
        getAppletContext().showDocument(u);
      } catch(Exception e) {
        l.setText(e.toString());
      } 
    }
    else return super.action(evt, arg);
    return true;
  }
} ///:~