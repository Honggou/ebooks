//: c15:servlets:SessionPeek.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using the HttpSession class.
import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionPeek extends HttpServlet { 
  public void doGet(HttpServletRequest req, 
  HttpServletResponse res)
  throws ServletException, IOException {
    // Retrieve Seesion Object before any
    // output is sent to the client.
    HttpSession session = req.getSession();
    // Get the output stream
    ServletOutputStream out =
      res.getOutputStream();
    res.setContentType("text/html");
    out.println("<HEAD><TITLE> SessionPeek ");
    out.println(" </TITLE></HEAD><BODY>");
    out.println("<h1> SessionPeek </h1>");
    // A simple hit counter for this session.
    Integer ival = (Integer) 
      session.getValue("sesspeek.cntr");
    if(ival==null) 
      ival = new Integer(1);
    else 
      ival = new Integer(ival.intValue() + 1);
    session.putValue("sesspeek.cntr", ival);
    out.println("You have hit this page <b>"
      + ival + "</b> times.<p>");
    // Session Data
    out.println("<h2>");
    out.println(" Saved Session Data </h2>");
    // loop through all data in the session
    // and spit is out.
    String[] sesNames = session.getValueNames();
    for(int i = 0; i < sesNames.length; i++) {
      String name = sesNames[i]; 
      String value = 
        session.getValue(name).toString();
      out.println(name + " = " + value + "<br>");
    }
    // Session Statistics    
    out.println("<h3> Session Statistics </h3>");
    out.println("Session ID: " 
      + session.getId() + "<br>");
    out.println("New Session: " + session.isNew()
      + "<br>");
    out.println("Creation Time: "
      + session.getCreationTime());
    out.println("<I>(" + 
      new Date(session.getCreationTime())
      + ")</I><br>");
    out.println("Last Accessed Time: " +
      session.getLastAccessedTime());
    out.println("<I>(" +
      new Date(session.getLastAccessedTime())
      + ")</I><br>");
    out.println("Session Inactive Interval: "
      + session.getMaxInactiveInterval());
    out.println("Session ID in Request: "
      + req.getRequestedSessionId() + "<br>");
    out.println("Is session id from Cookie: "
      + req.isRequestedSessionIdFromCookie()
      + "<br>");
    out.println("Is session id from URL: "
      + req.isRequestedSessionIdFromURL()
      + "<br>");
    out.println("Is session id valid: "
      + req.isRequestedSessionIdValid()
      + "<br>");
    out.println("</BODY>");
    out.close();
  }
  public String getServletInfo() {
    return "A session tracking servlet";
  }
} ///:~