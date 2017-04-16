//: c15:servlets:SessionPeek.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Using the HttpSession class.
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionPeek extends HttpServlet { 
  public void service(HttpServletRequest req, 
  HttpServletResponse res)
  throws ServletException, IOException {
    // Retrieve Session Object before any
    // output is sent to the client.
    HttpSession session = req.getSession();
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.println("<HEAD><TITLE> SessionPeek ");
    out.println(" </TITLE></HEAD><BODY>");
    out.println("<h1> SessionPeek </h1>");
    // A simple hit counter for this session.
    Integer ival = (Integer) 
      session.getAttribute("sesspeek.cntr");
    if(ival==null) 
      ival = new Integer(1);
    else 
      ival = new Integer(ival.intValue() + 1);
    session.setAttribute("sesspeek.cntr", ival);
    out.println("You have hit this page <b>"
      + ival + "</b> times.<p>");
    out.println("<h2>");
    out.println("Saved Session Data </h2>");
    // Loop through all data in the session:
    Enumeration sesNames = 
      session.getAttributeNames();
    while(sesNames.hasMoreElements()) {
      String name = 
        sesNames.nextElement().toString();
      Object value = session.getAttribute(name);
      out.println(name + " = " + value + "<br>");
    }
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